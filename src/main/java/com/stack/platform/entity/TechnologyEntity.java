//package com.stack.platform.entity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Null;
//
//@Entity
//@Table(name = "technology")
//public class TechnologyEntity extends BaseEntity {
//
//	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	@Column(name = "id")
//	private Long id;
//	
//	@NotNull
//	@Column(name = "name")
//	private String name;
//	
//	@Null
//	@Column(name = "type")
//	private String type;
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "id", nullable = false)
//	private TechStackEntity techstack;
//}
