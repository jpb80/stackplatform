package com.stack.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.stack.platform.exceptions.InvalidArgumentException;
import com.stack.platform.resource.RoleResource;
import com.stack.platform.services.IRoleService;

import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	
	@SuppressWarnings("unchecked")
	@Override
	@HystrixCommand(groupKey="RoleGroup", commandKey="SaveRoleCommand",  threadPoolKey="RoleThreadPool")
	public <S extends RoleResource> S save(S resource) {
		
		RoleResource resourceResp = null;
		try {
			Long roleId = resource.getId();
			if (roleId == null) {
				resourceResp =  roleService.addRole(resource);
			} else {
				resourceResp = roleService.updateRole(resource);
			}
		} catch (InvalidArgumentException e) {
			log.error("saving a role has failed");
		} catch (NullPointerException npe) {
			log.error(npe.getMessage());
			throw new InvalidArgumentException(HttpStatus.BAD_REQUEST);
		}
		return (S) resourceResp;
	}

}
