package com.stack.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.stack.platform.resource.TechStackResource;
import com.stack.platform.services.ITechStackService;

import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceList;

@Component
public class TechStackResourceController extends ResourceRepositoryBase<TechStackResource, Long> {

	@Autowired
	ITechStackService techStackService;
	
	public TechStackResourceController() {
		super(TechStackResource.class);
	}

	@Override
	@HystrixCommand(groupKey="TechStackFindOne", commandKey="TechStack",  threadPoolKey="TechStack")
	public TechStackResource findOne(Long id, QuerySpec querySpec) {
		return techStackService.findOne(id);
	}

	@Override
	@HystrixCommand(groupKey="TechStackFindAll", commandKey="TechStack",  threadPoolKey="TechStack")
	public ResourceList<TechStackResource> findAll(QuerySpec querySpec) {
		return querySpec.apply(techStackService.findAll());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@HystrixCommand(groupKey="TechStackSave", commandKey="TechStack",  threadPoolKey="TechStack")
	public <S extends TechStackResource> S save(S resource) {
		return (S) techStackService.save(resource);
	}
	
	@Override
	@HystrixCommand(groupKey="TechStackDelete", commandKey="TechStack",  threadPoolKey="TechStack")
	public void delete(Long id) {
		techStackService.delete(id);
	}
}
