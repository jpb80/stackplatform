package com.stack.platform.services.impl;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stack.platform.entity.Role;
import com.stack.platform.entity.TeamEntity;
import com.stack.platform.entity.UserProfileEntity;
import com.stack.platform.exceptions.InvalidArgumentException;
import com.stack.platform.repository.IRoleRepository;
import com.stack.platform.repository.ITeamRepository;
import com.stack.platform.repository.IUserProfileRepository;
import com.stack.platform.resource.UserProfileResource;
import com.stack.platform.services.IUserProfileService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserProfileService implements IUserProfileService {

	@Autowired
	IUserProfileRepository userRepo;
	
	@Autowired
	ITeamRepository teamRepo;
	
	@Autowired
	IRoleRepository roleRepo;
	
	@Override
	public Iterable<UserProfileResource> findAll() {
		
		Iterable<UserProfileEntity> users = userRepo.findAll();
		Collection<UserProfileResource> userResources = new HashSet<UserProfileResource>(); 
		for (UserProfileEntity user : users) {
			UserProfileResource resource = convertToResource(user);
			userResources.add(resource);
		}
		return userResources;
	}
	
	@Override
	public UserProfileResource findOne(Long id) {
		
		if (id == null) {
			log.error("UserProfileResource id cannot be null");
			throw new InvalidArgumentException("Unable to process request");
		}
		return convertToResource(userRepo.findOne(id));
	}

	@Override
	@Transactional
	public UserProfileResource save(UserProfileResource resource) {
		
		Long resourceId = resource.getId();
		UserProfileEntity entity = null;
		
		if (resourceId == null) {
			log.debug("Creating a new UserProfileEntity");
			entity = new UserProfileEntity();
			entity.setCreated(new Date());
			entity = fromResourceToEntity(entity, resource);				
		} else {			
			entity = userRepo.findById(resourceId);
			if (entity == null) {
				log.error("UserProfileEntity does not exist for id={}", resourceId);
				throw new InvalidArgumentException("Unable to process request");
			}
			
			if (entity.getDeleted() != null) {
				log.debug("Undeleting existing UserProfileEntity");
				entity.setDeleted(null);
				entity.setModified(new Date());
			}  else {
				log.debug("Updating existing UserProfileEntity");
				entity.setModified(new Date());	
			}			

			entity = fromResourceToEntity(entity, resource);				
		}
		
		return convertToResource(userRepo.save(entity));
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		
		if (id == null || id >= Long.MAX_VALUE || id < 0L) {
			log.error("Invalid id");
			throw new InvalidArgumentException("Unable to process request");
		}
		
		UserProfileEntity entity = userRepo.findOne(id);
		if (entity == null) {
			log.error("UserProfile does not exist for id={}", id);
			throw new InvalidArgumentException("Unable to process request");
		}		
		entity.setDeleted(new Date());
		userRepo.save(entity);
	}
	
	private UserProfileResource convertToResource(UserProfileEntity entity) {
		return new UserProfileResource(entity);
	}
	
	private UserProfileEntity fromResourceToEntity(UserProfileEntity entity, UserProfileResource resource) {
		
		Long roleId = resource.getRoleid();
		validateId(roleId, "Role");			
		Long teamId = resource.getTeamid();
		validateId(teamId, "Team");
		
		Role role = roleRepo.findById(roleId);
		if (role == null) {
			log.error("Role does not exist for id={}", roleId);
			throw new InvalidArgumentException("Unable to process request");
		}
		entity.setRole(role);
		
		TeamEntity team = teamRepo.findById(teamId);
		if (team == null) {
			log.error("TeamEntity does not exist for id={}", teamId);
			throw new InvalidArgumentException("Unable to process request");
		}
		entity.setTeam(team);
		
		return entity;
	}
	
	private void validateId(Long id, String entityType) {
		log.debug("Validating id={}, entityType={}", id, entityType);
		if (id == null) {
			log.error(entityType + " does not exist with id={}", id);
			throw new InvalidArgumentException("Unable to process request");
		}
	}
}
