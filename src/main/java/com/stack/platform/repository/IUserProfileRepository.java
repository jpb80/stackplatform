package com.stack.platform.repository;

import java.util.Set;

import org.springframework.data.repository.Repository;

import com.stack.platform.entity.UserProfileEntity;

public interface IUserProfileRepository extends Repository<UserProfileEntity, Long> {

	<S extends UserProfileEntity> S save(S roleEntity);
	
	UserProfileEntity findById(Long id);
	
	Set<UserProfileEntity> findAll();
	
	UserProfileEntity findOne(Long id);
}
