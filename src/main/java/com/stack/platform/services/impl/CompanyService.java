package com.stack.platform.services.impl;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stack.platform.entity.CompanyEntity;
import com.stack.platform.entity.TeamEntity;
import com.stack.platform.exceptions.InvalidArgumentException;
import com.stack.platform.repository.ICompanyRepository;
import com.stack.platform.resource.CompanyResource;
import com.stack.platform.resource.TeamResource;
import com.stack.platform.services.ICompanyService;

@Service
public class CompanyService  implements ICompanyService {

	@Autowired
	ICompanyRepository companyRepo;
	
	@Transactional
	public CompanyResource save(CompanyResource resource) {
		
		if (resource == null) {
			throw new InvalidArgumentException("Unable to process request.");
		}
		
		CompanyEntity entity = null;
		Long resourceId = resource.getId();
		if (resourceId == null) {
			entity = new CompanyEntity();
			entity.setCreated(new Date());
			entity.setDeleted(resource.getDeleted());
			entity.setModified(resource.getModified());
			entity.setName(resource.getName());			
		} else {
			entity = companyRepo.findOne(resourceId);
			if (entity == null) {
				throw new InvalidArgumentException("Unable to process request");
			}
			entity.setDeleted(resource.getDeleted());
			entity.setModified(new Date());
			entity.setName(resource.getName());	
		}
		
		return convertToResource(companyRepo.save(entity));
	}

	@Transactional
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
		companyResource.setName(entity.getName());
		Set<TeamEntity> team = entity.getCompanyTeams();
		Set<TeamResource> teamResources = new HashSet<TeamResource>();
		for (TeamEntity teamEntity : team) {
			TeamResource teamResource = new TeamResource();
			teamResource.setId(teamEntity.getId());
			teamResource.setCompanyid(teamEntity.getCompanyid());
			teamResources.add(teamResource);
		}
		companyResource.setCompanyTeams(teamResources);
		return companyResource;
	}
}
