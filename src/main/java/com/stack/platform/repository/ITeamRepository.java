package com.stack.platform.repository;

import java.util.Set;

import org.springframework.data.repository.Repository;

import com.stack.platform.entity.TeamEntity;

public interface ITeamRepository extends Repository<TeamEntity, Long> {

	TeamEntity findOne(Long id);
	
	Set<TeamEntity> findAll();
	
	<S extends TeamEntity> S save(TeamEntity entity);
}
