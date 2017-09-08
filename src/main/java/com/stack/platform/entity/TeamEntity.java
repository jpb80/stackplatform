package com.stack.platform.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "team")
@Getter @Setter
@ToString @EqualsAndHashCode(callSuper=true)
public class TeamEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@NotNull
	@Column(name = "techstackid")
	private Long techstackid;
	
	@NotNull
	@Column(name = "companyid")
	private Long companyid;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id", nullable = false, insertable = false, updatable = false)
	private CompanyEntity company;
	
	@OneToMany
	@JoinColumn(name = "id", insertable = false, updatable = false, 
				referencedColumnName = "techstackid", nullable = false)
	private Set<TechStackEntity> teamTechStacks = new HashSet<TechStackEntity>();
	
	@OneToMany(mappedBy="team")
	private Set<UserProfileEntity> usersOnTeam = new HashSet<UserProfileEntity>();

}
