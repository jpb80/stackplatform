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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "techstack")
@Getter @Setter
@ToString @EqualsAndHashCode(callSuper=true)
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

}
