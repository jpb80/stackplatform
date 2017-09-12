package com.stack.platform.repository;

import org.springframework.data.repository.Repository;

import com.stack.platform.entity.TechnologyEntity;

public interface ITechnologyRepository extends Repository<TechnologyEntity, Long>{

	public <S extends TechnologyEntity> S save(S technologyEntity);
	
	public Iterable<TechnologyEntity> findAll();
	
	public TechnologyEntity findById(Long id);
}
