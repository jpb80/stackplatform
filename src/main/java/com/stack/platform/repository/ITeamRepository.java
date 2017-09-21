package com.stack.platform.repository;

import java.util.Set;

import org.springframework.data.repository.Repository;

import com.stack.platform.entity.TeamEntity;

public interface ITeamRepository extends Repository<TeamEntity, Long> {

	public TeamEntity findOne(Long id);
	
	public Set<TeamEntity> findAll();
	
	public <S extends TeamEntity> S save(TeamEntity entity);
}
