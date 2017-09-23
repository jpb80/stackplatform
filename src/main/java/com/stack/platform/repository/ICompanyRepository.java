package com.stack.platform.repository;

import java.util.Set;

import org.springframework.data.repository.Repository;

import com.stack.platform.entity.CompanyEntity;

public interface ICompanyRepository extends Repository<CompanyEntity, Long> {

	CompanyEntity save(CompanyEntity entity);
	
	Set<CompanyEntity> findAll();
	
	CompanyEntity findOne(Long id);
}
