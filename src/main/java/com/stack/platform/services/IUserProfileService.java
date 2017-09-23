package com.stack.platform.services;

import com.stack.platform.resource.UserProfileResource;

public interface IUserProfileService {

	public Iterable<UserProfileResource> findAll();
	
	public <S extends UserProfileResource> S save(S resource);
}
