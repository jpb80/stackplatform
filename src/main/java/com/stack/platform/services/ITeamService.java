package com.stack.platform.services;

import com.stack.platform.resource.TeamResource;

public interface ITeamService {
	
	public Iterable<TeamResource> findAll();
	
	public TeamResource save(TeamResource resource);
}
