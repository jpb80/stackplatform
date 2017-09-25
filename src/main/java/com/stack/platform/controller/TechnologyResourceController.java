package com.stack.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.stack.platform.resource.TechnologyResource;
import com.stack.platform.services.ITechnologyService;

import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceList;

@Component
public class TechnologyResourceController extends ResourceRepositoryBase<TechnologyResource, Long> {

	@Autowired
	ITechnologyService techService;
	
	public TechnologyResourceController() {
		super(TechnologyResource.class);
	}

	@Override
	@HystrixCommand(groupKey="Technology", commandKey="FindAllTech",  threadPoolKey="FindAllTech")
	public ResourceList<TechnologyResource> findAll(QuerySpec querySpec) {
		return querySpec.apply(techService.findAll());
	}
	
	@Override
	@HystrixCommand(groupKey="Technology", commandKey="FindAllTech",  threadPoolKey="FindAllTech")
	public <S extends TechnologyResource> S save(S resource) {
		return techService.save(resource);
	}

}
