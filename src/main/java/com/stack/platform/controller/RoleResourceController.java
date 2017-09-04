package com.stack.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stack.platform.resource.RoleResource;
import com.stack.platform.services.IRoleService;

import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceList;

@Component
public class RoleResourceController extends ResourceRepositoryBase<RoleResource, Long> {

	@Autowired
	IRoleService roleService;
	
	protected RoleResourceController() {
		super(RoleResource.class);
	}

	@Override
	public ResourceList<RoleResource> findAll(QuerySpec querySpec) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public <S extends RoleResource> S save(S resource) {
		
		return roleService.addRole(resource);
	}

}
