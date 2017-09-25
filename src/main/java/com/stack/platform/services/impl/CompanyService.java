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

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CompanyService  implements ICompanyService {

	@Autowired
	ICompanyRepository companyRepo;
	
	@Override
	@Transactional
	public CompanyResource save(CompanyResource resource) {
		
		if (resource == null) {
			log.error("Invalid resource");
			throw new InvalidArgumentException("Unable to process request.");
		}
		
		CompanyEntity entity = null;
		Long resourceId = resource.getId();
		if (resourceId == null) {
			log.debug("Creating a new CompanyEntity");
			entity = new CompanyEntity();
			entity.setCreated(new Date());
		} else {
			log.debug("Updating existing CompanyEntity");
			entity = companyRepo.findOne(resourceId);
			if (entity == null) {
				log.error("Company does not exist for id={}", resourceId);
				throw new InvalidArgumentException("Unable to process request");
			}
			entity.setModified(new Date());
		}

		entity.setName(resource.getName());			
		return convertToResource(companyRepo.save(entity));
	}
	
	@Override
	@Transactional
	public CompanyResource findOne(Long id) {
		
		if (id == null || id >= Long.MAX_VALUE || id < 0L) {
			log.error("Invalid id");
			throw new InvalidArgumentException("Unable to process request");
		}
		
		CompanyEntity entity = companyRepo.findOne(id);
		if (entity == null) {
			log.error("Company does not exist for id={}", id);
			throw new InvalidArgumentException("Unable to process request");
		}
		
		return convertToResource(entity);
	}

	@Override
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
	
	@Override
	@Transactional
	public void delete(Long id) {
		
		if (id == null || id >= Long.MAX_VALUE || id < 0L) {
			log.error("Invalid id");
			throw new InvalidArgumentException("Unable to process request");
		}
		
		CompanyEntity entity = companyRepo.findOne(id);
		if (entity == null) {
			log.error("Company does not exist for id={}", id);
			throw new InvalidArgumentException("Unable to process request");
		}		
		entity.setDeleted(new Date());
		companyRepo.save(entity);
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
