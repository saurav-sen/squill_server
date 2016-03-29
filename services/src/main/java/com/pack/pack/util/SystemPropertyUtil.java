package com.pack.pack.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Saurav
 *
 */
public class SystemPropertyUtil {

	private static Properties properties;

	private static Logger logger = LoggerFactory
			.getLogger(SystemPropertyUtil.class);

	private static final String APP_HOME = "app.home";

	private static final String IMAGE_HOME = "image.home";
	private static final String VIDEO_HOME = "video.home";
	private static final String THUMBNAIL_HOME = "thumbnail.home";
	private static final String BASE_URL = "base.url";
	private static final String EGIFT_IMAGE_HOME = "egift.home";

	private static final String PROFILE_PICTURE_HOME = "profile.picture.home";

	public static final String URL_SEPARATOR = "/";
	public static final String BROADCAST_API_PREFIX = "broadcast";
	public static final String ATTACHMENT = "attachment";
	public static final String IMAGE = "image";
	public static final String VIDEO = "video";
	public static final String PROFILE = "profile";
	private static final String ATTACHMENT_URL_SUFFIX = ATTACHMENT
			+ URL_SEPARATOR;
	public static final String IMAGE_ATTACHMENT_URL_SUFFIX = ATTACHMENT_URL_SUFFIX
			+ IMAGE + URL_SEPARATOR;
	public static final String VIDEO_ATTACHMENT_URL_SUFFIX = ATTACHMENT_URL_SUFFIX
			+ VIDEO + URL_SEPARATOR;
	public static final String PROFILE_IMAGE_URL_SUFFIX = ATTACHMENT_URL_SUFFIX
			+ PROFILE + URL_SEPARATOR + IMAGE + URL_SEPARATOR;

	private static final String CONFIG_FILE = "../conf/system_internal.properties";

	private static final String DEFAULT_TOPIC_ID_VALUE = "home.topic";

	public static final String ES_BASE_URL = "esUrl";

	private static final String ES_DEFAULT_DOCUMENT_NAME = "esDocName";
	
	public static final String ES_USER_DOC_TYPE = "user";
	
	public static final String CONTENT_TYPE_HEADER_NAME = "Content-Type";

	public static void init() {
		try {
			properties = new Properties();
			properties.load(new FileInputStream(new File(CONFIG_FILE)));
		} catch (FileNotFoundException e) {
			logger.info(e.getMessage(), e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			logger.info(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	private static String getPropertyValue(String key) {
		return properties.getProperty(key);
	}

	public static String getElasticSearchDefaultDocumentName() {
		String docName = getPropertyValue(ES_DEFAULT_DOCUMENT_NAME);
		if(docName == null || docName.trim().equals("")) {
			docName = "packpack";
		}
		return docName;
	}

	public static String getElasticSearchBaseUrl() {
		return getPropertyValue(ES_BASE_URL);
	}

	public static String getAppHome() {
		return getPropertyValue(APP_HOME);
	}

	public static String getEGiftImageHome() {
		return getPropertyValue(EGIFT_IMAGE_HOME);
	}

	public static String getImageHome() {
		return getPropertyValue(IMAGE_HOME);
	}

	public static String getVideoHome() {
		return getPropertyValue(VIDEO_HOME);
	}

	public static String getThumbnailHome() {
		return getPropertyValue(THUMBNAIL_HOME);
	}

	public static String getProfilePictureHome() {
		return getPropertyValue(PROFILE_PICTURE_HOME);
	}

	public static String getBaseURL() {
		return getPropertyValue(BASE_URL);
	}

	public static String getImageAttachmentBaseURL() {
		String baseURL = getBaseURL();
		if (!baseURL.endsWith(URL_SEPARATOR)) {
			baseURL = baseURL + URL_SEPARATOR;
		}
		return baseURL + IMAGE_ATTACHMENT_URL_SUFFIX;
	}

	public static String getVideoAttachmentBaseURL() {
		String baseURL = getBaseURL();
		if (!baseURL.endsWith(URL_SEPARATOR)) {
			baseURL = baseURL + URL_SEPARATOR;
		}
		return baseURL + VIDEO_ATTACHMENT_URL_SUFFIX;
	}

	public static String getProfilePictureBaseURL() {
		String baseURL = getBaseURL();
		if (!baseURL.endsWith(URL_SEPARATOR)) {
			baseURL = baseURL + URL_SEPARATOR;
		}
		return baseURL + PROFILE_IMAGE_URL_SUFFIX;
	}

	public static String getDefaultSystemTopicId() {
		return DEFAULT_TOPIC_ID_VALUE;
	}
}