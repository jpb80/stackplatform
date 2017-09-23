package com.stack.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stack.platform.resource.UserProfileResource;
import com.stack.platform.services.IUserProfileService;

import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceList;

@Component
public class UserProfileResourceController extends ResourceRepositoryBase<UserProfileResource, Long> {

	@Autowired
	IUserProfileService service;
	
	public UserProfileResourceController() {
		super(UserProfileResource.class);
	}

	@Override
	public ResourceList<UserProfileResource> findAll(QuerySpec querySpec) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public <S extends UserProfileResource> S save(S resource) {
		return service.save(resource);
	}
	
	
}
