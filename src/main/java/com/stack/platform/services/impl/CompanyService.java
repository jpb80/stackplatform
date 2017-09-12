package com.stack.platform.services.impl;

import java.util.Collection;
import java.util.HashSet;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stack.platform.entity.CompanyEntity;
import com.stack.platform.repository.ICompanyRepository;
import com.stack.platform.resource.CompanyResource;
import com.stack.platform.services.ICompanyService;

@Service
public class CompanyService  implements ICompanyService {

	@Autowired
	ICompanyRepository companyRepo;
	
	public Iterable<CompanyResource> findAll() {
		
		Iterable<CompanyEntity> companies = companyRepo.findAll();
		Collection<CompanyResource> companyResources = new HashSet<CompanyResource>();
		for (CompanyEntity entity : companies) {
			CompanyResource companyResource = convertToResource(entity);
			companyResources.add(companyResource);
		}
		return companyResources;
	}
	
	private CompanyResource convertToResource(@NotNull CompanyEntity entity) {
		
		CompanyResource companyResource = new CompanyResource();
		companyResource.setCreated(entity.getCreated());
		companyResource.setDeleted(entity.getDeleted());
		companyResource.setId(entity.getId());
		companyResource.setModified(entity.getModified());
		return companyResource;
	}
}
