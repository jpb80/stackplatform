package com.stack.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.stack.platform.resource.TeamResource;
import com.stack.platform.services.ITeamService;

import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceList;

public class TeamResourceController extends ResourceRepositoryBase<TeamResource, Long> {

	@Autowired
	ITeamService teamService;
	
	protected TeamResourceController() {
		super(TeamResource.class);
	}

	@Override
	public ResourceList<TeamResource> findAll(QuerySpec querySpec) {
		return querySpec.apply(teamService.findAll());
	}

}
