package com.stack.platform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Entity
@Table(name = "technology")
public class TechnologyEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@NotNull
	@Column(name = "name")
	private String name;
	
	@Null
	@Column(name = "type")
	private String type;
	
	@NotNull
	@Column(name = "techstackid")
	private Long techstackid;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id", nullable = false, insertable = false, updatable = false)
	private TechStackEntity techstack;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getTechstackid() {
		return techstackid;
	}

	public void setTechstackid(Long techstackid) {
		this.techstackid = techstackid;
	}

	public TechStackEntity getTechstack() {
		return techstack;
	}

	public void setTechstack(TechStackEntity techstack) {
		this.techstack = techstack;
	}

	public Long getId() {
		return id;
	}
}
