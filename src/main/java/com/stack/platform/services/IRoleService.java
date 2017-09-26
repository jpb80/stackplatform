package com.stack.platform.services;

import com.stack.platform.exceptions.InvalidArgumentException;
import com.stack.platform.resource.RoleResource;

public interface IRoleService {

//	RoleResource addRole(RoleResource roleResource) throws InvalidArgumentException;
//	
//	RoleResource updateRole(RoleResource roleResource) throws InvalidArgumentException;
	
	void delete(Long id);

	RoleResource findOne(Long id);
	
	Iterable<RoleResource> findAll();

	RoleResource save(RoleResource resource);
}
