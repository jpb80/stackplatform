package com.stack.platform.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import lombok.Data;

@Data
@MappedSuperclass
public abstract class BaseEntity {

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable=false, updatable=false)
	private Date created;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified")
	private Date modified;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deleted")
	private Date deleted;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "version")
	@Version
	private Date version;

}
