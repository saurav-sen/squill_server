package com.pack.pack.util;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pack.pack.model.RSSFeed;
import com.pack.pack.model.RssFeedType;
import com.pack.pack.rss.IRssFeedService;
import com.pack.pack.services.exception.PackPackException;
import com.pack.pack.services.rabbitmq.MessagePublisher;
import com.pack.pack.services.registry.ServiceRegistry;
import com.squill.feed.web.model.JRssFeed;
import com.squill.feed.web.model.JRssFeedType;
import com.squill.feed.web.model.JRssFeeds;
import com.squill.feed.web.model.TTL;

/**
 * 
 * @author Saurav
 *
 */
public class RssFeedUtil {
	
	private static final Logger LOG = LoggerFactory.getLogger(RssFeedUtil.class);

	public static void uploadNewFeeds(JRssFeeds feeds, TTL ttl, boolean sendNotification) {
		LOG.info("Classification done. Uploading feeds to DB");
		try {
			List<JRssFeed> list = new LinkedList<JRssFeed>();
			List<JRssFeed> newFeeds = feeds.getFeeds();
			if (newFeeds != null && !newFeeds.isEmpty()) {
				//TTL ttl = new TTL();
				ttl.setTime((short) 2);
				ttl.setUnit(TimeUnit.DAYS);
				IRssFeedService service = ServiceRegistry.INSTANCE
						.findCompositeService(IRssFeedService.class);
				for (JRssFeed feed : newFeeds) {
					boolean f = service.upload(feed, ttl);
					LOG.info("Upleaded News Feed = " + f);
					if(f) {
						list.add(feed);
					}
				}
				/*JRssFeeds result = new JRssFeeds();
				result.setFeeds(list);*/
				MessagePublisher messagePublisher = ServiceRegistry.INSTANCE
						.findService(MessagePublisher.class);
				if(!sendNotification)
					return;
				final int size = list.size();
				LOG.info("Sending Notifications = " + list.size());
				if(size > 0) {
					Thread t0 = new Thread(new Runnable() {
						
						@Override
						public void run() {
							try {
								messagePublisher.broadcastNewRSSFeedUploadSummary("You have " + size + " news and recent happenings");
							} catch (Exception e) {
								LOG.error(e.getMessage(), e);
							}
						}
					});
					t0.start();
				}
			}
			LOG.info("Successfully uploaded feeds in DB");
		} catch (PackPackException e) {
			LOG.error(e.getErrorCode() + "::" + e.getMessage(), e);
		}
	}
	
	private static final String resolvePrefix(RSSFeed feed) {
		return resolvePrefix(feed.getFeedType());
	}
	
	private static final String resolvePrefix(JRssFeed feed) {
		return resolvePrefix(feed.getFeedType());
	}
	
	public static final String resolvePrefix(String feedType) {
		if(feedType == null) {
			return "Feeds_";
		} else if(RssFeedType.REFRESHMENT.name().equalsIgnoreCase(feedType)) {
			return "Feeds_";
		} else if(RssFeedType.NEWS.name().equalsIgnoreCase(feedType)) {
			return JRssFeedType.NEWS.name() + "_";
		} else if(RssFeedType.NEWS_SCIENCE_TECHNOLOGY.name().equalsIgnoreCase(feedType)) {
			return JRssFeedType.NEWS_SCIENCE_TECHNOLOGY.name() + "_";
		} else if(RssFeedType.NEWS_SPORTS.name().equalsIgnoreCase(feedType)) {
			return JRssFeedType.NEWS_SPORTS.name() + "_";
		} else if(RssFeedType.ARTICLE.name().equalsIgnoreCase(feedType)) {
			return JRssFeedType.ARTICLE.name() + "_";
		}
		return "Feeds_";
	}
	
	public static final String generateUploadKey(RSSFeed feed) {
		//return "Feeds_" + String.valueOf(feed.getOgUrl().hashCode());
		String key = feed.getOgImage();
		if(key == null) {
			key = feed.getOgUrl() != null ? feed.getOgUrl() : (feed.getHrefSource() != null ? feed.getHrefSource() : feed.getOgTitle());
		}
		return resolvePrefix(feed) + String.valueOf(key.hashCode());
	}
	
	public static final String generateUploadKey(JRssFeed feed) {
		String key = feed.getOgImage();
		if(key == null) {
			key = feed.getOgUrl() != null ? feed.getOgUrl() : (feed.getHrefSource() != null ? feed.getHrefSource() : feed.getOgTitle());
		}
		return resolvePrefix(feed) + String.valueOf(key.hashCode());
	}
}