package com.pack.pack.model.web;

import java.util.LinkedList;
import java.util.List;


/**
 * 
 * @author Saurav
 *
 */
public class JComment {

	private String fromUserId;
	
	private String fromUserName;
	
	private String comment;
	
	private long dateTime;
	
	private List<JComment> replies;

	public String getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public long getDateTime() {
		return dateTime;
	}

	public void setDateTime(long dateTime) {
		this.dateTime = dateTime;
	}

	public List<JComment> getReplies() {
		if(replies == null) {
			replies = new LinkedList<JComment>();
		}
		return replies;
	}

	public void setReplies(List<JComment> replies) {
		this.replies = replies;
	}
}