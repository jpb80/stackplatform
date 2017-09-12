package com.stack.platform.services;

import com.stack.platform.resource.CompanyResource;

public interface ICompanyService {

	public Iterable<CompanyResource> findAll();
}
