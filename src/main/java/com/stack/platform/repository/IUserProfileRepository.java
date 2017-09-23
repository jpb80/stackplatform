package com.stack.platform.repository;

import org.springframework.data.repository.Repository;

import com.stack.platform.entity.UserProfileEntity;

public interface IUserProfileRepository extends Repository<UserProfileEntity, Long> {

	<S extends UserProfileEntity> S save(S roleEntity);
	
	UserProfileEntity findById(Long id);
}
