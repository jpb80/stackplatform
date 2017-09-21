package com.stack.platform.services;

import com.stack.platform.resource.CompanyResource;

public interface ICompanyService {
	
	public CompanyResource save(CompanyResource resource);

	public Iterable<CompanyResource> findAll();
}
