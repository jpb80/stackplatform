package com.stack.platform.repository;

import org.springframework.data.repository.Repository;

import com.stack.platform.entity.TechnologyEntity;

public interface ITechnologyRepository extends Repository<TechnologyEntity, Long>{

	<S extends TechnologyEntity> S save(S technologyEntity);
	
	Iterable<TechnologyEntity> findAll();
	
	TechnologyEntity findById(Long id);
	
	TechnologyEntity findOne(Long id);
}
