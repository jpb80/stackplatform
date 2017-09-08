package com.stack.platform.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class BaseEntity {

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", updatable=false)
	private Date created;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified")
	private Date modified;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deleted")
	private Date deleted;
	
	@Version
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "version", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date version;

}
