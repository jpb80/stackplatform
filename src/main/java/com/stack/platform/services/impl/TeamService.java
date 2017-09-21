package com.stack.platform.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.stack.platform.entity.TeamEntity;
import com.stack.platform.repository.ITeamRepository;
import com.stack.platform.resource.TeamResource;

public class TeamService {

	@Autowired
	ITeamRepository teamRepo;
	
	public Iterable<TeamResource> findAll() {
		Iterable<TeamEntity> teams = teamRepo.findAll();
		return teams;
	}
}
