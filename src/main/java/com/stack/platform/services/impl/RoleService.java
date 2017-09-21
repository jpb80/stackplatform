package com.stack.platform.services.impl;

import java.util.Date;

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

@Slf4j
@Service(value = "RoleService")
public class RoleService implements IRoleService {

	@Autowired
	IRoleRepository roleRepo;
	
	@Override
	@Transactional(rollbackOn=InvalidArgumentException.class)
	public RoleResource addRole(@NotNull RoleResource roleResource) throws InvalidArgumentException {
				
		if (roleResource == null) {
			log.error("roleResource is null");
			throw new InvalidArgumentException("Unable to process request");
		}
		
		log.debug("roleResource={}", roleResource);
		
		String name = roleResource.getName();
		if (name == null || name.isEmpty()) {
			log.error("The roleResource.getName() is null or empty");
			throw new InvalidArgumentException("Unable to process request");
		}

		Role role = new Role();
		role.setName(name);
		role.setCreated(new Date());
		
		return convertToResource(roleRepo.save(role));
	}
	
	@Override
	@Transactional
	public RoleResource updateRole(@NotNull RoleResource roleResource)  throws InvalidArgumentException {
		
		Long roleId = roleResource.getId();	
		log.debug("roleId={}", roleId);
		
		Role role = roleRepo.findById(roleId);
		log.debug("role={}", role);
		
		String roleName = roleResource.getName();
		if (roleName == null || roleName.isEmpty()) {
			log.error("roleName cannot be null or empty");
			throw new InvalidArgumentException("Unable to process request");
		}
		
		role.setName(roleName);
		role.setModified(new Date());

		return convertToResource(roleRepo.save(role));
		
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
