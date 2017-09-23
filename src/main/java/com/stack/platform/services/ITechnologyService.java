package com.stack.platform.services;

import com.stack.platform.resource.TechnologyResource;

public interface ITechnologyService {
	
	public Iterable<TechnologyResource> findAll();
}
