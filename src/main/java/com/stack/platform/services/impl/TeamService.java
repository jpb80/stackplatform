package com.stack.platform.services.impl;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stack.platform.entity.CompanyEntity;
import com.stack.platform.entity.TeamEntity;
import com.stack.platform.exceptions.InvalidArgumentException;
import com.stack.platform.repository.ICompanyRepository;
import com.stack.platform.repository.ITeamRepository;
import com.stack.platform.resource.TeamResource;
import com.stack.platform.services.ITeamService;

@Service
public class TeamService implements ITeamService {

	@Autowired
	ITeamRepository teamRepo;
	
	@Autowired
	ICompanyRepository companyRepo;
	
	@Override
	@Transactional
	public TeamResource save(TeamResource resource) {
		
		Long resourceId = resource.getId();
		TeamEntity teamEntity = teamRepo.findById(resourceId);
		
		if (resourceId == null) {
			if (teamEntity != null) {
				teamEntity.setDeleted(null);
				teamEntity.setModified(new Date());
			} else {
				teamEntity = new TeamEntity();
				teamEntity.setCreated(new Date());
			}
		} else {
			if (teamEntity == null) {
				throw new InvalidArgumentException("Unable to process request");
			}
			
			Date deleted = teamEntity.getDeleted();
			if (deleted != null) {
				throw new InvalidArgumentException("Unable to process request");
			}
			teamEntity.setModified(new Date());
		}
		
		teamEntity = setEntityFromResource(teamEntity, resource);
		return convertToResource(teamRepo.save(teamEntity));
	}
	
	public Iterable<TeamResource> findAll() {
		
		Iterable<TeamEntity> teams = teamRepo.findAll();
		Collection<TeamResource> teamResources = new HashSet<TeamResource>(); 
		for (TeamEntity team : teams) {
			TeamResource teamResource = convertToResource(team);
			teamResources.add(teamResource);
		}
		return teamResources;
	}

	private TeamResource convertToResource(TeamEntity entity) {
		return new TeamResource(entity);
	}
	
	private TeamEntity setEntityFromResource(TeamEntity entity, TeamResource resource) {
		
		Long companyId = resource.getCompanyid();
		if (companyId == null) {
			throw new InvalidArgumentException("Unable to process request");
		}
		CompanyEntity company = companyRepo.findOne(companyId);
		if (company == null) {
			throw new InvalidArgumentException("Unable to process request");
		}
		entity.setCompany(company);
		entity.setCompanyid(companyId);
		entity.setCompanyid(resource.getCompanyid());
		entity.setTechstackid(resource.getTechstackid());
		entity.setDeleted(resource.getDeleted());
		return entity;
	}


}
