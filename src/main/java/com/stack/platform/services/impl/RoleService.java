package com.stack.platform.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.stack.platform.entity.Role;
import com.stack.platform.exceptions.InvalidArgumentException;
import com.stack.platform.repository.IRoleRepository;
import com.stack.platform.resource.RoleResource;
import com.stack.platform.services.IRoleService;

public class RoleService implements IRoleService {

	@Autowired
	IRoleRepository roleRepo;
	
	public Role addRole(RoleResource roleResource) throws InvalidArgumentException {
		
		if (roleResource == null) {
			throw new InvalidArgumentException();
		}
		
		String name = roleResource.getName();
		if (name == null || name.isEmpty()) {
			throw new InvalidArgumentException();
		}
		
		Role role = new Role();
		role.setName(name);
		
		return roleRepo.save(role);
	}
}
