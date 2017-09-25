package com.stack.platform.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Where(clause="deleted is null")
@Table(name = "company")
@Getter @Setter
@ToString(exclude={"companyTeams"}, doNotUseGetters = true)
@EqualsAndHashCode(exclude={"companyTeams"}, callSuper=true)
public class CompanyEntity extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@NotNull
	@Column(name = "name")
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id", insertable = false, updatable = false, nullable = false)
	private Set<TeamEntity> companyTeams = new HashSet<TeamEntity>();

}
