package com.stack.platform.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "techstack")
public class TechStackEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@NotNull
	@Column(name = "techid")
	private Long techid;
	
	@OneToMany(mappedBy="techstack")
	private Set<TechnologyEntity> technologies = new HashSet<TechnologyEntity>();

	public Long getTechid() {
		return techid;
	}

	public void setTechid(Long techid) {
		this.techid = techid;
	}

	public Long getId() {
		return id;
	}

	public Set<TechnologyEntity> getTechnologies() {
		return technologies;
	}
}
