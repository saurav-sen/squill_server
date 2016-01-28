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
	
	private static Logger logger = LoggerFactory.getLogger(SystemPropertyUtil.class);
	
	private static final String IMAGE_HOME = "image.home";
	private static final String VIDEO_HOME = "video.home";
	private static final String THUMBNAIL_HOME = "thumbnail.home";
	private static final String BASE_URL = "base.url";
	
	private static final String URL_SEPARATOR = "/";
	public static final String BROADCAST_API_PREFIX = "broadcast";
	public static final String ATTACHMENT = "attachment";
	public static final String IMAGE = "image";
	public static final String VIDEO = "video";
	private static final String ATTACHMENT_URL_SUFFIX = ATTACHMENT + URL_SEPARATOR;
	public static final String IMAGE_ATTACHMENT_URL_SUFFIX = ATTACHMENT_URL_SUFFIX + IMAGE + URL_SEPARATOR;
	public static final String VIDEO_ATTACHMENT_URL_SUFFIX = ATTACHMENT_URL_SUFFIX + VIDEO + URL_SEPARATOR;
	
	private static final String CONFIG_FILE = "../conf/system_internal.properties";

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
	
	public static String getImageHome() {
		return properties.getProperty(IMAGE_HOME);
	}
	
	public static String getVideoHome() {
		return properties.getProperty(VIDEO_HOME);
	}
	
	public static String getThumbnailHome() {
		return properties.getProperty(THUMBNAIL_HOME);
	}
	
	public static String getBaseURL() {
		return properties.getProperty(BASE_URL);
	}
	
	public static String getImageAttachmentBaseURL() {
		String baseURL = getBaseURL();
		if(!baseURL.endsWith(URL_SEPARATOR)) {
			baseURL = baseURL + URL_SEPARATOR;
		}
		return baseURL + IMAGE_ATTACHMENT_URL_SUFFIX;
	}
	
	public static String getVideoAttachmentBaseURL() {
		String baseURL = getBaseURL();
		if(!baseURL.endsWith(URL_SEPARATOR)) {
			baseURL = baseURL + URL_SEPARATOR;
		}
		return baseURL + VIDEO_ATTACHMENT_URL_SUFFIX;
	}
}