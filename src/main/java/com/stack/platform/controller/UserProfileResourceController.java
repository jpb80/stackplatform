package com.stack.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.stack.platform.resource.UserProfileResource;
import com.stack.platform.services.IUserProfileService;

import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceList;

@Component
public class UserProfileResourceController extends ResourceRepositoryBase<UserProfileResource, Long> {

	@Autowired
	IUserProfileService userService;
	
	public UserProfileResourceController() {
		super(UserProfileResource.class);
	}

	@Override
	@HystrixCommand(groupKey="UserProfileFindAll", commandKey="UserProfile",  threadPoolKey="UserProfile")
	public ResourceList<UserProfileResource> findAll(QuerySpec querySpec) {
		return querySpec.apply(userService.findAll());
	}
	
	@Override
	@HystrixCommand(groupKey="UserProfileFindOne", commandKey="UserProfile",  threadPoolKey="UserProfile")
	public UserProfileResource findOne(Long id, QuerySpec querySpec) {
		return userService.findOne(id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@HystrixCommand(groupKey="UserProfileSave", commandKey="UserProfile",  threadPoolKey="UserProfile")
	public <S extends UserProfileResource> S save(S resource) {
		return (S) userService.save(resource);
	}
	
	@Override
	@HystrixCommand(groupKey="UserProfileDelete", commandKey="UserProfile",  threadPoolKey="UserProfile")
	public void delete(Long id) {
		userService.delete(id);
	}	
}
