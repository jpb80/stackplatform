package com.stack.platform.services;

import com.stack.platform.resource.TechStackResource;

public interface ITechStackService {

	Iterable<TechStackResource> findAll();

	TechStackResource findOne(Long id);

	TechStackResource save(TechStackResource resource);
	
	void delete(Long id);

}
