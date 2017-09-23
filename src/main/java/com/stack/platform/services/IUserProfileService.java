package com.stack.platform.services;

import com.stack.platform.resource.UserProfileResource;

public interface IUserProfileService {

	public Iterable<UserProfileResource> findAll();
	
	public UserProfileResource  save(UserProfileResource resource);
	
	//public UserProfileResource findOne(Long id);
}
