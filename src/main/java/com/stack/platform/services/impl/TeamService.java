package com.stack.platform.services.impl;

import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.stack.platform.entity.TeamEntity;
import com.stack.platform.repository.ITeamRepository;
import com.stack.platform.resource.TeamResource;
import com.stack.platform.services.ITeamService;

public class TeamService implements ITeamService {

	@Autowired
	ITeamRepository teamRepo;
	
	@Override
	public TeamResource save(TeamResource resource) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Iterable<TeamResource> findAll() {
		
		Iterable<TeamEntity> teams = teamRepo.findAll();
		Collection<TeamResource> teamResources = new HashSet<TeamResource>(); 
		for (TeamEntity team : teams) {
			TeamResource teamResource = convertToResource(team);
			teamResources.add(teamResource);
		}
		return teamResources;
	}
	
	private TeamResource convertToResource(TeamEntity entity) {
		TeamResource resource = new TeamResource();
		resource.setCompanyid(entity.getCompanyid());
		resource.setCreated(entity.getCreated());
		resource.setDeleted(entity.getDeleted());
		resource.setId(entity.getId());
		resource.setModified(entity.getModified());
		resource.setTechstackid(entity.getTechstackid());
		resource.setVersion(entity.getVersion());
		return resource;
	}


}
