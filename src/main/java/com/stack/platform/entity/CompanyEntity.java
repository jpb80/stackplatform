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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "company")
@Getter @Setter
@ToString @EqualsAndHashCode(callSuper=true)
public class CompanyEntity extends BaseEntity{
	
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

}
