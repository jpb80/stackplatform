package com.stack.platform.resource;

import java.util.Date;

public class BaseResource {

	private Date created;
	private Date deleted;
	private Date modified;
	private Date version;
	
	public Date getCreated() {
		return created;
	}
	public Date getDeleted() {
		return deleted;
	}
	public Date getModified() {
		return modified;
	}
	public Date getVersion() {
		return version;
	}
}
