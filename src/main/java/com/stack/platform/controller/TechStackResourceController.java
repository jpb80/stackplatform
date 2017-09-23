package com.stack.platform.controller;

import org.springframework.stereotype.Component;

import com.stack.platform.resource.TechStackResource;

import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceList;

@Component
public class TechStackResourceController extends ResourceRepositoryBase<TechStackResource, Long> {

	public TechStackResourceController() {
		super(TechStackResource.class);
	}

	@Override
	public ResourceList<TechStackResource> findAll(QuerySpec querySpec) {
		// TODO Auto-generated method stub
		return null;
	}
}
