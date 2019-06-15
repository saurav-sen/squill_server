package com.pack.pack.util;

import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pack.pack.model.RSSFeed;
import com.pack.pack.model.RssFeedType;
import com.pack.pack.services.exception.PackPackException;
import com.pack.pack.services.redis.INewsFeedService;
import com.pack.pack.services.redis.IRefreshmentFeedService;
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
	
	public static void main(String[] args) {
		DateTime dt = new DateTime(1536508598427L);
		System.out.println(dt);
	}

	private static final Logger LOG = LoggerFactory
			.getLogger(RssFeedUtil.class);
	
	public static void storeRecentFeedIds(Set<String> recentFeedIds)
			throws PackPackException {
		INewsFeedService service = ServiceRegistry.INSTANCE
				.findCompositeService(INewsFeedService.class);
		service.storeRecentFeedIds(recentFeedIds);
	}

	public static void uploadRefreshmentFeeds(JRssFeeds feeds, TTL ttl,
			long batchId, boolean sendNotification) {
		LOG.info("Classification done. Uploading Refreshment feeds to DB");
		try {
			List<JRssFeed> list = new LinkedList<JRssFeed>();
			int size = 0;
			List<JRssFeed> newFeeds = feeds.getFeeds();
			if (newFeeds != null && !newFeeds.isEmpty()) {
				size = newFeeds.size();
				// TTL ttl = new TTL();
				if(ttl == null) {
					ttl = new TTL();
				}
				ttl.setTime((short) 2);
				ttl.setUnit(TimeUnit.DAYS);
				IRefreshmentFeedService service = ServiceRegistry.INSTANCE
						.findCompositeService(IRefreshmentFeedService.class);
				for (JRssFeed feed : newFeeds) {
					boolean f = service.upload(feed, ttl, batchId);
					LOG.info("Uploaded Refreshment Feed = " + f);
					if (f) {
						list.add(feed);
					}
				}
			}
			LOG.info("Successfully uploaded Refreshment " + list.size() + " out of " + size + " feeds in DB");
		} catch (PackPackException e) {
			LOG.error(e.getErrorCode() + "::" + e.getMessage(), e);
		}
	}
	
	public static void markAsProvisionedByFeedIds(List<String> ids, JRssFeedType newType) throws PackPackException {
		if(ids == null || ids.isEmpty())
			return;
		INewsFeedService service = ServiceRegistry.INSTANCE
				.findCompositeService(INewsFeedService.class);
		for(String id : ids) {
			service.markAsProvisionedByFeedId(id, newType);
		}
	}
	
	public static Set<String> uploadNewsFeeds(JRssFeeds feeds, TTL ttl, long batchId,
			boolean sendNotification) {
		return uploadNewsFeeds(feeds, ttl, batchId, sendNotification, true, false);
	}

	public static Set<String> uploadNewsFeeds(JRssFeeds feeds, TTL ttl, long batchId,
			boolean sendNotification, boolean hasShareableUrl, boolean liveUrl) {
		Set<String> f = new HashSet<String>();
		LOG.info("Classification done. Uploading News feeds to DB");
		try {
			int size = 0;
			List<JRssFeed> list = new LinkedList<JRssFeed>();
			List<JRssFeed> newFeeds = feeds.getFeeds();
			if (newFeeds != null && !newFeeds.isEmpty()) {
				// TTL ttl = new TTL();
				size = newFeeds.size();
				if(ttl == null) {
					ttl = new TTL();
				}
				ttl.setTime((short) 1);
				ttl.setUnit(TimeUnit.DAYS);
				INewsFeedService service = ServiceRegistry.INSTANCE
						.findCompositeService(INewsFeedService.class);
				f = service.upload(feeds.getFeeds(), ttl, batchId, hasShareableUrl, liveUrl);
				LOG.info("Uploaded News Feed = " + f);
				if (f != null && !f.isEmpty()) {
					list.addAll(feeds.getFeeds());
				}
			}
			LOG.info("Successfully uploaded News " + list.size() + " out of " + size + " feeds in DB");
			if(list.isEmpty()) {
				f.clear();
			}
		} catch (PackPackException e) {
			LOG.error(e.getErrorCode() + "::" + e.getMessage(), e);
		}
		return f;
	}
	
	public static Set<String> uploadOpinionFeed(JRssFeed feed, TTL ttl, long batchId,
			boolean sendNotification) {
		return uploadOpinionFeed(feed, ttl, batchId, sendNotification, true, false);
	}
	
	public static Set<String> uploadOpinionFeed(JRssFeed feed, TTL ttl, long batchId,
			boolean sendNotification, boolean hasShareableUrl, boolean liveUrl) {
		Set<String> f = null;
		LOG.info("Trying to upload Opinion");
		try {
			if (feed != null) {
				if(ttl == null) {
					ttl = new TTL();
					ttl.setTime((short) 7);
					ttl.setUnit(TimeUnit.DAYS);
				}
				List<JRssFeed> list = new LinkedList<JRssFeed>();
				list.add(feed);
				INewsFeedService service = ServiceRegistry.INSTANCE
						.findCompositeService(INewsFeedService.class);
				f = service.upload(list, ttl, batchId, hasShareableUrl, liveUrl);
				if (f != null && !f.isEmpty()) {
					LOG.info("Successfully uploaded Opinion " + feed.getOgTitle());
				} else {
					LOG.info("Failed to upload Opinion " + feed.getOgTitle());
				}
			}			
		} catch (PackPackException e) {
			LOG.error(e.getErrorCode() + "::" + e.getMessage(), e);
		}
		return f;
	}

	public static final String resolvePrefix(RSSFeed feed) {
		return resolvePrefix(feed.getFeedType());
	}

	public static final String resolvePrefix(JRssFeed feed) {
		return resolvePrefix(feed.getFeedType());
	}

	public static final String resolvePrefix(String feedType) {
		if (feedType == null) {
			return "Feeds_";
		} else if (RssFeedType.REFRESHMENT.name().equalsIgnoreCase(feedType)) {
			return "Feeds_";
		} else if (RssFeedType.NEWS.name().equalsIgnoreCase(feedType)) {
			return JRssFeedType.NEWS.name() + "_";
		} else if (RssFeedType.NEWS_SCIENCE_TECHNOLOGY.name().equalsIgnoreCase(
				feedType)) {
			return JRssFeedType.NEWS_SCIENCE_TECHNOLOGY.name() + "_";
		} else if (RssFeedType.NEWS_SPORTS.name().equalsIgnoreCase(feedType)) {
			return JRssFeedType.NEWS_SPORTS.name() + "_";
		} else if (RssFeedType.ARTICLE.name().equalsIgnoreCase(feedType)) {
			return JRssFeedType.ARTICLE.name() + "_";
		} else if (RssFeedType.OPINION.name().equalsIgnoreCase(feedType)) {
			return JRssFeedType.OPINION.name() + "_";
		} else if (RssFeedType.UNPROVISIONED.name().equalsIgnoreCase(feedType)) {
			return JRssFeedType.UNPROVISIONED.name() + "_";
		}
		return "Feeds_";
	}
	
	public static final String generateUploadKey(RSSFeed feed)
			throws NoSuchAlgorithmException {
		String key = feed.getOgImage();
		if (key == null) {
			key = feed.getOgUrl() != null ? feed.getOgUrl() : (feed
					.getHrefSource() != null ? feed.getHrefSource() : feed
					.getOgTitle());
		}
		return resolvePrefix(feed)
				+ EncryptionUtil.generateSH1HashKey(key, false, true);
	}

	public static final String generateUploadKey(JRssFeed feed)
			throws NoSuchAlgorithmException {
		String key = feed.getOgImage();
		if (key == null) {
			key = feed.getOgUrl() != null ? feed.getOgUrl() : (feed
					.getHrefSource() != null ? feed.getHrefSource() : feed
					.getOgTitle());
		}
		return resolvePrefix(feed)
				+ EncryptionUtil.generateSH1HashKey(key, false, true);
	}
}