package com.squill.og.crawler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.squill.og.crawler.hooks.IHtmlContentHandler;
import com.squill.og.crawler.hooks.IWebLinkTrackerService;
import com.squill.og.crawler.internal.utils.CoreConstants;
import com.squill.og.crawler.internal.utils.HttpRequestExecutor;
import com.squill.og.crawler.internal.utils.WebSpiderUtils;
import com.squill.og.crawler.model.WebSpiderTracker;

/**
 * 
 * @author Saurav
 *
 */
public class WebSiteSpider implements Runnable {
	
	private IWebSite webSite;
	private IWebLinkTrackerService tracker;
	
	private static Logger LOG = LoggerFactory.getLogger(WebSiteSpider.class);
	
	private int index = 0;
	
	private List<? extends ILink> links;
	
	public WebSiteSpider(IWebSite domain, IWebLinkTrackerService tracker) {
		this.webSite = domain;
		this.tracker = tracker;
	}

	@Override
	public void run() {
		IHtmlContentHandler contentHandler = webSite.getContentHandler();
		try {
			if (links == null && links.isEmpty()) {
				links = WebSpiderUtils.parseCrawlableURLs(webSite);
				index = 0;
			}
			IRobotScope robotScope = webSite.getRobotScope();
			doCrawl(links, robotScope, contentHandler);
			links = robotScope.getAnyLeftOverLinks();
			doCrawl(links, robotScope, contentHandler);
		} catch (Throwable e) {
			LOG.error(e.getMessage(), e);
		} finally {
			links.clear();
			index = 0;
			contentHandler.postComplete();
		}
	}
	
	private void doCrawl(List<? extends ILink> links, IRobotScope robotScope,
			IHtmlContentHandler contentHandler) throws Exception {
		if(links != null && !links.isEmpty()) {
			int count = 0;
			int max = 0;
			int i = index;
			int len = links.size(); 
			for(; i<len; i++) {
				ILink link = links.get(i);
				if(contentHandler.getThresholdFrequency() > 0 
						&& max >= contentHandler.getThresholdFrequency()) {
					contentHandler.flush();
					LOG.info("Threshold value reached... crawler will hung up for next scheduled time.");
					return;
				}
				if(count >= contentHandler.getFlushFrequency()) {
					contentHandler.flush();
					count = 0;
				}
				WebSpiderTracker info = null;
				
				
				if(link == null || link.getUrl() == null || "".equals(link.getUrl().trim()))
					continue;
				if(robotScope.isScoped(link.getUrl())) {
					LOG.info("Visiting " + link.getUrl());
					contentHandler.preProcess(link);
					if(webSite.needToTrackCrawlingHistory()) {
						info = tracker.getTrackedInfo(link.getUrl());
						if(info == null) {
							info = new WebSpiderTracker();
						}
						info.setLastCrawled(System.currentTimeMillis());
						info.setLink(link.getUrl());
					}
					String html = doCrawl(link.getUrl(), info);
					if (webSite.needToTrackCrawlingHistory()) {
						long ttlSeconds = 10 * 24 * 60 * 60;
						tracker.addCrawledInfo(link.getUrl(), info, ttlSeconds);
					}
					if(CoreConstants.SKIP.equalsIgnoreCase(html)) {
						continue;
					}
					try {
						contentHandler.postProcess(html, link);
					} catch (Exception e1) {
						LOG.info(e1.getMessage());
						return;
					}
					/*if(info != null) {
						info.setLastCrawled(new Date(System.currentTimeMillis()));
						info.setVisited(true);
						tracker.trackLink(info);
					}*/
					try {
						//Default crawl delay
						int delay = robotScope != null ? robotScope.getDefaultCrawlDelay() : 2000;
						if(delay < 2000) {
							delay = 2000;
						}
						Thread.sleep(2000);
					} catch (Exception e) {
						LOG.error(e.getMessage(), e);
					}
					count++;
					max++;
				}
			}
		}
	}
	
	public String doCrawl(String link, WebSpiderTracker info) throws Exception {
		/*DefaultHttpClient client = new DefaultHttpClient();
		HttpParams params = client.getParams();
		HttpConnectionParams.setConnectionTimeout(params, 200000);
		HttpConnectionParams.setSoTimeout(params, 200000);
		HttpGet GET = new HttpGet(link);
		HttpContext HTTP_CONTEXT = new BasicHttpContext();
		HTTP_CONTEXT.setAttribute(CoreProtocolPNames.USER_AGENT, 
				CoreConstants.TROVE_ROBOT_USER_AGENT_STRING);
		HttpResponse response = client.execute(GET, HTTP_CONTEXT);
		if(response.getStatusLine().getStatusCode() == 200) {
			return EntityUtils.toString(response.getEntity());
		}*/
		return new HttpRequestExecutor().GET(link, info);
	}
}