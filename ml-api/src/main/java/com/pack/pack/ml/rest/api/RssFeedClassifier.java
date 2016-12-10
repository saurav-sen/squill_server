package com.pack.pack.ml.rest.api;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import com.pack.pack.IRssFeedService;
import com.pack.pack.common.util.JSONUtil;
import com.pack.pack.ml.rest.api.context.ClassificationEngine;
import com.pack.pack.ml.rest.api.context.FeedStatusListener;
import com.pack.pack.ml.rest.api.reader.CompressRead;
import com.pack.pack.ml.rest.api.writer.CompressWrite;
import com.pack.pack.model.web.JRssFeed;
import com.pack.pack.model.web.JRssFeeds;
import com.pack.pack.model.web.JStatus;
import com.pack.pack.model.web.StatusType;
import com.pack.pack.model.web.TTL;
import com.pack.pack.services.exception.PackPackException;
import com.pack.pack.services.registry.ServiceRegistry;

/**
 * 
 * @author Saurav
 *
 */
@Singleton
@Provider
@Path("/feeds")
public class RssFeedClassifier {

	/**
	 * 
	 * Should be protected by API key
	 * 
	 * @param bulk
	 * @return
	 * @throws PackPackException
	 */
	@PUT
	@CompressRead
	@CompressWrite
	@Path("classify")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JStatus bulkUpload(String json) throws PackPackException {
		JRssFeeds bulk = JSONUtil.deserialize(json, JRssFeeds.class);
		ClassificationEngine.INSTANCE.submitFeeds(bulk,
				new FeedStatusListenerImpl());
		JStatus status = new JStatus();
		status.setStatus(StatusType.OK);
		status.setInfo("Successfully Submitted Feeds for batch upload");
		return status;
	}

	private class FeedStatusListenerImpl implements FeedStatusListener {

		@Override
		public void completed(JRssFeeds feeds) {
			try {
				List<JRssFeed> list = new LinkedList<JRssFeed>();
				List<JRssFeed> newFeeds = feeds.getFeeds();
				if (newFeeds != null && !newFeeds.isEmpty()) {
					TTL ttl = new TTL();
					ttl.setTime((short) 1);
					ttl.setUnit(TimeUnit.DAYS);
					IRssFeedService service = ServiceRegistry.INSTANCE
							.findCompositeService(IRssFeedService.class);
					for (JRssFeed feed : newFeeds) {
						JRssFeed f = service.upload(feed, ttl);
						list.add(f);
					}
					JRssFeeds result = new JRssFeeds();
					result.setFeeds(list);
				}
			} catch (PackPackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void failed(JRssFeeds feeds) {
			// TODO Auto-generated method stub

		}

	}
}