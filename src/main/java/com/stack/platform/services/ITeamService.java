package com.stack.platform.services;

import com.stack.platform.resource.TeamResource;

public interface ITeamService {
	
	Iterable<TeamResource> findAll();
	
	TeamResource save(TeamResource resource);

	void delete(Long id);

	TeamResource findOne(Long id);
}
