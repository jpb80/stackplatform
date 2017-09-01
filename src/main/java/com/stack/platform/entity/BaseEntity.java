package com.stack.platform.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;


public class BaseEntity {

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created")
	private Date created;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified")
	private Date modified;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deleted")
	private Date deleted;
	
	@Version
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "version")
	private Date version;
}
