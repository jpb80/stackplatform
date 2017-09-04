package com.stack.platform.services.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.stack.platform.entity.Role;
import com.stack.platform.exceptions.InvalidArgumentException;
import com.stack.platform.repository.IRoleRepository;
import com.stack.platform.resource.RoleResource;
import com.stack.platform.services.IRoleService;

@Service(value = "RoleService")
public class RoleService implements IRoleService {

	@Autowired
	IRoleRepository roleRepo;
	
	@Override
	public RoleResource addRole(RoleResource roleResource) throws InvalidArgumentException {
		
		if (roleResource == null) {
			throw new InvalidArgumentException(HttpStatus.BAD_REQUEST);
		}
		
		String name = roleResource.getName();
		if (name == null || name.isEmpty()) {
			throw new InvalidArgumentException(HttpStatus.BAD_REQUEST);
		}

		Role role = new Role();
		role.setName(name);
		role.setCreated(new Date());
		
		return convertToResource(roleRepo.save(role));
	}
	
	private RoleResource convertToResource(Role role) {
		RoleResource roleResouce = new RoleResource();
		roleResouce.setId(role.getId());
		roleResouce.setName(role.getName());
		return roleResouce;
	}
}
