package com.stack.platform.services.impl;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stack.platform.entity.Role;
import com.stack.platform.exceptions.InvalidArgumentException;
import com.stack.platform.repository.IRoleRepository;
import com.stack.platform.resource.RoleResource;
import com.stack.platform.services.IRoleService;

import lombok.extern.slf4j.Slf4j;

@Service(value = "RoleService")
@Slf4j
public class RoleService implements IRoleService {

	@Autowired
	IRoleRepository roleRepo;
	
//	@Override
//	@Transactional
//	public RoleResource addRole(RoleResource roleResource) throws InvalidArgumentException {
//				
//		if (roleResource == null) {
//			log.error("roleResource is null");
//			throw new InvalidArgumentException("Unable to process request");
//		}
//		
//		log.debug("roleResource={}", roleResource);
//		
//		String name = roleResource.getName();
//		if (name == null || name.isEmpty()) {
//			log.error("The roleResource.getName() is null or empty");
//			throw new InvalidArgumentException("Unable to process request");
//		}
//
//		Role role = new Role();
//		role.setName(name);
//		role.setCreated(new Date());
//		
//		return convertToResource(roleRepo.save(role));
//	}
//	
//	@Override
//	@Transactional
//	public RoleResource updateRole(RoleResource roleResource)  throws InvalidArgumentException {
//		
//		Long roleId = roleResource.getId();	
//		log.debug("roleId={}", roleId);
//		
//		Role role = roleRepo.findById(roleId);
//		log.debug("role={}", role);
//		
//		String roleName = roleResource.getName();
//		if (roleName == null || roleName.isEmpty()) {
//			log.error("roleName cannot be null or empty");
//			throw new InvalidArgumentException("Unable to process request");
//		}
//		
//		role.setName(roleName);
//		role.setModified(new Date());
//
//		return convertToResource(roleRepo.save(role));
//		
//	}
	
	@Override
	@Transactional
	public RoleResource save(RoleResource resource) {
		
		if (resource == null) {
			log.error("roleResource is null");
			throw new InvalidArgumentException("Unable to process request");
		}
		
		Role entity = null;
		Long resourceId = resource.getId();
		if (resourceId == null) {
			log.debug("Creating a new Role");
			entity = new Role();
			entity.setCreated(new Date());
		} else {
			log.debug("Updating existing Role");
			entity = roleRepo.findOne(resourceId);
			if (entity == null) {
				log.error("Role does not exist for id={}", resourceId);
				throw new InvalidArgumentException("Unable to process request");
			}
			entity.setModified(new Date());
		}

		entity.setName(resource.getName());			
		return convertToResource(roleRepo.save(entity));
	}
	
	@Override
	@Transactional
	public Iterable<RoleResource> findAll() {
		
		Iterable<Role> roles = roleRepo.findAll();
		Collection<RoleResource> roleResources = new HashSet<RoleResource>();
		for (Role entity : roles) {
			RoleResource roleResource = convertToResource(entity);
			roleResources.add(roleResource);
		}
		return roleResources;
	}
	
	@Override
	@Transactional
	public RoleResource findOne(Long id) {
		
		if (id == null || id >= Long.MAX_VALUE || id < 0L) {
			log.error("Invalid id");
			throw new InvalidArgumentException("Unable to process request");
		}
		
		Role entity = roleRepo.findOne(id);
		if (entity == null) {
			log.error("Company does not exist for id={}", id);
			throw new InvalidArgumentException("Unable to process request");
		}
		
		return convertToResource(entity);
	}

	
	@Override
	@Transactional
	public void delete(Long id) {
		
		if (id == null || id >= Long.MAX_VALUE || id < 0L) {
			log.error("Invalid id");
			throw new InvalidArgumentException("Unable to process request");
		}
		
		Role entity = roleRepo.findOne(id);
		if (entity == null) {
			log.error("Role does not exist for id={}", id);
			throw new InvalidArgumentException("Unable to process request");
		}		
		entity.setDeleted(new Date());
		roleRepo.save(entity);
	}
	
	private RoleResource convertToResource(@NotNull Role role) {
		RoleResource roleResouce = new RoleResource();
		roleResouce.setId(role.getId());
		roleResouce.setName(role.getName());
		roleResouce.setCreated(role.getCreated());
		roleResouce.setDeleted(role.getDeleted());
		roleResouce.setModified(role.getModified());
		return roleResouce;
	}

}
