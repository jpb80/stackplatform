package com.stack.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stack.platform.resource.CompanyResource;
import com.stack.platform.services.ICompanyService;

import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceList;

@Component
public class CompanyResourceController extends ResourceRepositoryBase<CompanyResource, Long> {

	@Autowired
	ICompanyService companyService;
	
	public CompanyResourceController() {
		super(CompanyResource.class);
	}

	@Override
	public ResourceList<CompanyResource> findAll(QuerySpec querySpec) {
		return querySpec.apply(companyService.findAll());
	}
	
}
