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
	@HystrixCommand(groupKey="TechnologyFindALl", commandKey="Technology",  threadPoolKey="Technology")
	public ResourceList<TechnologyResource> findAll(QuerySpec querySpec) {
		return querySpec.apply(techService.findAll());
	}
		
	@Override
	@HystrixCommand(groupKey="TechnologyFindOne", commandKey="Technology",  threadPoolKey="Technology")
	public TechnologyResource findOne(Long id, QuerySpec querySpec) {
		return techService.findOne(id);
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	@HystrixCommand(groupKey="TechnologySave", commandKey="Technology",  threadPoolKey="Technology")
	public <S extends TechnologyResource> S save(S resource) {
		return (S) techService.save(resource);
	}
	
	@Override
	@HystrixCommand(groupKey="TechnologyDelete", commandKey="Technology",  threadPoolKey="Technology")
	public void delete(Long id) {
		techService.delete(id);
	}
}
