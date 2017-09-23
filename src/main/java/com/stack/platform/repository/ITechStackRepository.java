package com.stack.platform.repository;

import org.springframework.data.repository.Repository;

import com.stack.platform.entity.TechStackEntity;

public interface ITechStackRepository extends Repository<TechStackEntity, Long> {
	
	<S extends TechStackEntity> S save(S roleEntity);
	
	TechStackEntity findById(Long id);
}
