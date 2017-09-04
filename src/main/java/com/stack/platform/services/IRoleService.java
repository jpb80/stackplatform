package com.stack.platform.services;

import com.stack.platform.exceptions.InvalidArgumentException;
import com.stack.platform.resource.RoleResource;

public interface IRoleService {

	public RoleResource addRole(RoleResource roleResource) throws InvalidArgumentException;
}
