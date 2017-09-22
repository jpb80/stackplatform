package com.stack.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.stack.platform.resource.TeamResource;
import com.stack.platform.services.ITeamService;

import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceList;

@Component
public class TeamResourceController extends ResourceRepositoryBase<TeamResource, Long> {

	@Autowired
	ITeamService teamService;
	
	protected TeamResourceController() {
		super(TeamResource.class);
	}
	

	@Override
	@HystrixCommand(groupKey="Team", commandKey="FindAllTeams",  threadPoolKey="FindAllTeams")
	public ResourceList<TeamResource> findAll(QuerySpec querySpec) {
		return querySpec.apply(teamService.findAll());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@HystrixCommand(groupKey="Team", commandKey="SaveTeam",  threadPoolKey="SaveTeam")
	public <S extends TeamResource> S save(S resource) {
		return (S) teamService.save(resource);
	}

}
