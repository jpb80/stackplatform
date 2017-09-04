package com.stack.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.stack.platform.exceptions.InvalidArgumentException;
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
	@HystrixCommand(groupKey="RoleGroup", commandKey="FindAllRoles",  threadPoolKey="RoleThreadPool")
	public ResourceList<RoleResource> findAll(QuerySpec querySpec) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@HystrixCommand(groupKey="RoleGroup", commandKey="SaveRoleCommand",  threadPoolKey="RoleThreadPool")
	public <S extends RoleResource> S save(S resource) {
		
		RoleResource resourceResp = null;
		try {
			resourceResp =  roleService.addRole(resource);
		} catch (InvalidArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (S) resourceResp;
	}

}
