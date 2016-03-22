package com.pack.pack.client.api;

/**
 * 
 * @author Saurav
 *
 */
public interface APIConstants {
	
	public static final String BASE_URL = "http://192.168.35.12:8080/packpack/";
	public static final String AUTHORIZATION_HEADER = "Authorization";
	public static final String CONTENT_TYPE_HEADER = "Content-Type";
	public static final String APPLICATION_JSON = "application/json";
	
	public static interface Login {
		public static final String CLIENT_KEY = "clientKey";
		public static final String CLIENT_SECRET = "clientSecret";
		public static final String USERNAME = "username";
		public static final String PASSWORD = "password";
	}
	
	public static interface User {
		public static final String ID = "userId";
		public static final String USERNAME = "username";
		public static final String SEARCH_USER_BY_NAME = "namePattern";
		
		public static interface Register {
			public static final String NAME = "name";
			public static final String EMAIL = "email";
			public static final String PASSWORD = "password";
			public static final String CITY = "city";
			public static final String COUNTRY = "country";
			public static final String STATE = "state";
			public static final String LOCALITY = "locality";
			public static final String DOB = "dob";
			public static final String PROFILE_PICTURE = "profilePicture";
		}
	}
	
	public static interface PageInfo {
		public static final String PAGE_LINK = "pageLink";
	}
	
	public static interface Topic {
		public static final String ID = "topicId";
		public static final String OWNER_ID = "ownerId";
		public static final String OWNER_NAME = "ownerName";
		public static final String NAME = "name";
		public static final String DESCRIPTION = "description";
		public static final String CATEGORY = "category";
	}

	public static interface Pack {
		public static final String ID = "packId";
	}
	
	public static interface ForwardPack {
		public static final String FROM_USER_ID = "fromUserId";
		public static final String TO_USER_ID = "toUserId";
		public static final String TO_USER_EMAIL = "toUserEmail";
	}
	
	public static interface Comment {
		public static final String FROM_USER_ID = "fromUserId";
		public static final String FROM_USER_NAME = "fromUserName";
		public static final String COMMENT = "comment";
	}
}