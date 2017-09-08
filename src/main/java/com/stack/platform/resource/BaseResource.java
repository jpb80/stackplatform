package com.stack.platform.resource;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BaseResource {

	private Date created;
	private Date deleted;
	private Date modified;
	private Date version;
}
