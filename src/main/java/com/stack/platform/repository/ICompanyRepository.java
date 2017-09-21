package com.stack.platform.repository;

import java.util.Set;

import org.springframework.data.repository.Repository;

import com.stack.platform.entity.CompanyEntity;

public interface ICompanyRepository extends Repository<CompanyEntity, Long> {

	public CompanyEntity save(CompanyEntity entity);
	
	public Set<CompanyEntity> findAll();
	
	public CompanyEntity findOne(Long id);
}
