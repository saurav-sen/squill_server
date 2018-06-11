package com.pack.pack.client.internal;

import static com.pack.pack.client.api.APIConstants.AUTHORIZATION_HEADER;

import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.pack.pack.client.api.APIConstants;
import com.pack.pack.client.api.COMMAND;
import com.pack.pack.client.api.MultipartRequestProgressListener;
import com.pack.pack.common.util.JSONUtil;
import com.pack.pack.model.web.Pagination;
import com.squill.feed.web.model.JRssFeed;
import com.squill.feed.web.model.JRssFeedType;
import com.squill.feed.web.model.JRssFeeds;

public class NewsFeedsApi extends BaseAPI {

	NewsFeedsApi(String baseUrl) {
		super(baseUrl);
	}

	private Invoker invoker = new Invoker();

	@Override
	protected ApiInvoker getInvoker() {
		return invoker;
	}

	@SuppressWarnings("unchecked")
	private Pagination<JRssFeed> getAllFeeds(String userId, String pageLink,
			String oAuthToken, String source) throws Exception {
		DefaultHttpClient client = new DefaultHttpClient();
		String url = getBaseUrl() + "news/usr/" + userId + "/page/" + pageLink
				+ "?source=" + source;
		HttpGet GET = new HttpGet(url);
		GET.addHeader(AUTHORIZATION_HEADER, oAuthToken);
		HttpResponse response = client.execute(GET);
		Pagination<JRssFeed> page = JSONUtil
				.deserialize(EntityUtils.toString(GZipUtil.decompress(response
						.getEntity())), Pagination.class);
		if (page == null)
			return page;
		List<JRssFeed> feeds = page.getResult();
		String json = "{\"feeds\": " + JSONUtil.serialize(feeds, false) + "}";
		JRssFeeds container = JSONUtil.deserialize(json, JRssFeeds.class);
		feeds = container.getFeeds();
		page.setResult(feeds);
		return page;
	}
	
	private class Invoker implements ApiInvoker {

		private COMMAND action;

		private Map<String, Object> params;

		private String oAuthToken;

		@Override
		public void setConfiguration(Configuration configuration) {
			action = configuration.getAction();
			params = configuration.getApiParams();
			oAuthToken = configuration.getOAuthToken();
		}

		@Override
		public Object invoke() throws Exception {
			if (COMMAND.GET_ALL_NEWS_FEEDS.equals(action)) {
				String userId = (String) params.get(APIConstants.User.ID);
				String source = JRssFeedType.NEWS.name();
				String pageLink = (String) params
						.get(APIConstants.PageInfo.PAGE_LINK);
				if (pageLink == null || pageLink.trim().equals("")) {
					pageLink = "FIRSTPAGE_1";
				}
				return getAllFeeds(userId, pageLink, oAuthToken, source);
			} else if (COMMAND.GET_ALL_SPORTS_NEWS_FEEDS.equals(action)) {
				String userId = (String) params.get(APIConstants.User.ID);
				String source = JRssFeedType.NEWS_SPORTS.name();
				String pageLink = (String) params
						.get(APIConstants.PageInfo.PAGE_LINK);
				if (pageLink == null || pageLink.trim().equals("")) {
					pageLink = "FIRSTPAGE_1";
				}
				return getAllFeeds(userId, pageLink, oAuthToken, source);
			} else if (COMMAND.GET_ALL_SCIENCE_AND_TECHNOLOGY_NEWS_FEEDS
					.equals(action)) {
				String userId = (String) params.get(APIConstants.User.ID);
				String source = JRssFeedType.NEWS_SCIENCE_TECHNOLOGY.name();
				String pageLink = (String) params
						.get(APIConstants.PageInfo.PAGE_LINK);
				if (pageLink == null || pageLink.trim().equals("")) {
					pageLink = "FIRSTPAGE_1";
				}
				return getAllFeeds(userId, pageLink, oAuthToken, source);
			} else if (COMMAND.GET_ALL_ARTICLES_FEEDS.equals(action)) {
				String userId = (String) params.get(APIConstants.User.ID);
				String source = JRssFeedType.ARTICLE.name();
				String pageLink = (String) params
						.get(APIConstants.PageInfo.PAGE_LINK);
				if (pageLink == null || pageLink.trim().equals("")) {
					pageLink = "FIRSTPAGE_1";
				}
				return getAllFeeds(userId, pageLink, oAuthToken, source);
			}
			return null;
		}

		@Override
		public Object invoke(MultipartRequestProgressListener listener)
				throws Exception {
			throw new UnsupportedOperationException(
					"Progress track is not supported by Home API");
		}
	}
}
