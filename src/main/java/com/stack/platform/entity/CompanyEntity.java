package com.stack.platform.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "company")
public class CompanyEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@NotNull
	@Column(name = "teamid")
	private long teamid;
	
	@OneToMany
	@JoinColumn(name = "id", insertable = false, updatable = false, referencedColumnName = "teamid", nullable = false)
	private Set<TeamEntity> companyTeams = new HashSet<TeamEntity>();

	public long getTeamid() {
		return teamid;
	}

	public void setTeamid(long teamid) {
		this.teamid = teamid;
	}

	public Long getId() {
		return id;
	}

	public Set<TeamEntity> getCompanyTeams() {
		return companyTeams;
	}
}
