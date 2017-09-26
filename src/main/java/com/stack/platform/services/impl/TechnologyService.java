package com.stack.platform.services.impl;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stack.platform.entity.TeamEntity;
import com.stack.platform.entity.TechnologyEntity;
import com.stack.platform.exceptions.InvalidArgumentException;
import com.stack.platform.repository.ITechnologyRepository;
import com.stack.platform.resource.TeamResource;
import com.stack.platform.resource.TechnologyResource;
import com.stack.platform.services.ITechnologyService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TechnologyService implements ITechnologyService {

	@Autowired
	ITechnologyRepository techRepo;
	
	@Override
	@Transactional
	public TechnologyResource findOne(Long id) {
		
		if (id == null || id >= Long.MAX_VALUE || id < 0L) {
			log.error("Invalid id");
			throw new InvalidArgumentException("Unable to process request");
		}
		
		TechnologyEntity entity = techRepo.findOne(id);
		if (entity == null) {
			log.error("TechnologyEntity does not exist for id={}", id);
			throw new InvalidArgumentException("Unable to process request");
		}
		
		return convertToResource(entity);
	}
	
	@Override
	@Transactional
	public Iterable<TechnologyResource> findAll() {
		
		Iterable<TechnologyEntity> entities = techRepo.findAll();
		Collection<TechnologyResource> entityResources = new HashSet<TechnologyResource>();
		for (TechnologyEntity entity : entities) {
			TechnologyResource resource = convertToResource(entity);
			entityResources.add(resource);
		}
		
		return entityResources;
	}
	

	@Override
	@Transactional
	public TechnologyResource save(TechnologyResource resource) {
		
		TechnologyEntity entity = null;
		
		Long resourceId = resource.getId();
		if (resourceId == null) {
			entity = new TechnologyEntity();
			entity.setCreated(new Date());
			entity.setName(resource.getName());
			entity.setType(resource.getType());
			//entity.setTechstack(resource.getTechstack());
		} else {
			entity = techRepo.findById(resourceId);
			entity.setModified(new Date());
		}
		return convertToResource(entity);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		
		if (id == null || id >= Long.MAX_VALUE || id < 0L) {
			log.error("Invalid id");
			throw new InvalidArgumentException("Unable to process request");
		}
		
		TechnologyEntity entity = techRepo.findOne(id);
		if (entity == null) {
			log.error("Company does not exist for id={}", id);
			throw new InvalidArgumentException("Unable to process request");
		}		
		entity.setDeleted(new Date());
		techRepo.save(entity);
	}
	
	private TechnologyResource convertToResource(TechnologyEntity entity) {
		return new TechnologyResource(entity);
	}
}
