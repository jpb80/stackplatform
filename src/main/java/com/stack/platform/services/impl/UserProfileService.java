package com.stack.platform.services.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stack.platform.entity.UserProfileEntity;
import com.stack.platform.repository.IUserProfileRepository;
import com.stack.platform.resource.UserProfileResource;
import com.stack.platform.services.IUserProfileService;

@Service
public class UserProfileService implements IUserProfileService {

	@Autowired
	IUserProfileRepository userRepo;
	
	@Override
	public Iterable<UserProfileResource> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends UserProfileResource> S save(S resource) {
		
		Long resourceId = resource.getId();
		UserProfileEntity entity = null;
		
		if (resourceId == null) {
			entity = new UserProfileEntity();
			entity.setCreated(new Date());
		} else {
			entity = userRepo.findById(resourceId);
		}
		
		userRepo.save(entity);
		return null;
	}
	
	private UserProfileEntity setEntityFromResource(UserProfileEntity entity, UserProfileResource resource) {
	}

}
