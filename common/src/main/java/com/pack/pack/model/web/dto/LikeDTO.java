package com.pack.pack.model.web.dto;

/**
 * 
 * @author Saurav
 *
 */
public class LikeDTO {

	private String userId;
	
	private String entityId;
	
	private String entityType;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
}