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
	@HystrixCommand(groupKey="TeamFindOne", commandKey="Team",  threadPoolKey="Team")
	public TeamResource findOne(Long id, QuerySpec querySpec) {
		return teamService.findOne(id);
	}

	@Override
	@HystrixCommand(groupKey="TeamFindAll", commandKey="Team",  threadPoolKey="Team")
	public ResourceList<TeamResource> findAll(QuerySpec querySpec) {
		return querySpec.apply(teamService.findAll());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@HystrixCommand(groupKey="TeamSave", commandKey="Team",  threadPoolKey="Team")
	public <S extends TeamResource> S save(S resource) {
		return (S) teamService.save(resource);
	}
	
	@Override
	@HystrixCommand(groupKey="TeamDelete", commandKey="Team",  threadPoolKey="Team")
	public void delete(Long id) {
		teamService.delete(id);
	}

}
