package com.stack.platform.services.impl;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stack.platform.entity.TechStackEntity;
import com.stack.platform.entity.TechnologyEntity;
import com.stack.platform.exceptions.InvalidArgumentException;
import com.stack.platform.repository.ITechStackRepository;
import com.stack.platform.resource.TechStackResource;
import com.stack.platform.resource.TechnologyResource;
import com.stack.platform.services.ITechStackService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TechStackService implements ITechStackService {

	@Autowired
	ITechStackRepository techstackRepo;
	
	@Override
	@Transactional
	public TechStackResource findOne(Long id) {
		
		if (id == null || id >= Long.MAX_VALUE || id < 0L) {
			log.error("Invalid id");
			throw new InvalidArgumentException("Unable to process request");
		}
		
		TechStackEntity entity = techstackRepo.findOne(id);
		if (entity == null) {
			log.error("Company does not exist for id={}", id);
			throw new InvalidArgumentException("Unable to process request");
		}
		
		return convertToResource(entity);
	}

	@Override
	@Transactional
	public Iterable<TechStackResource> findAll() {
		
		Iterable<TechStackEntity> techstacks = techstackRepo.findAll();
		Collection<TechStackResource> techstackResources = new HashSet<TechStackResource>();
		for (TechStackEntity entity : techstacks) {
			TechStackResource techStackResource = convertToResource(entity);
			techstackResources.add(techStackResource);
		}
		return techstackResources;
	}
	
	@Override
	@Transactional
	public TechStackResource save(TechStackResource resource) {
		
		if (resource == null) {
			log.error("Invalid resource");
			throw new InvalidArgumentException("Unable to process request.");
		}
		
		TechStackEntity entity = null;
		Long resourceId = resource.getId();
		if (resourceId == null) {
			log.debug("Creating a new TechStackEntity");
			entity = new TechStackEntity();
			entity.setCreated(new Date());
		} else {
			log.debug("Updating existing TechStackEntity");
			entity = techstackRepo.findOne(resourceId);
			if (entity == null) {
				log.error("TechStackEntity does not exist for id={}", resourceId);
				throw new InvalidArgumentException("Unable to process request");
			}
			entity.setModified(new Date());
		}
		return convertToResource(techstackRepo.save(entity));
	}
	
	
	@Override
	@Transactional
	public void delete(Long id) {
		
		if (id == null || id >= Long.MAX_VALUE || id < 0L) {
			log.error("Invalid id");
			throw new InvalidArgumentException("Unable to process request");
		}
		
		TechStackEntity entity = techstackRepo.findOne(id);
		if (entity == null) {
			log.error("TechStack does not exist for id={}", id);
			throw new InvalidArgumentException("Unable to process request");
		}		
		entity.setDeleted(new Date());
		techstackRepo.save(entity);
	}
	
	private TechStackResource convertToResource(@NotNull TechStackEntity entity) {
		
		TechStackResource techStackResource = new TechStackResource();
		techStackResource.setCreated(entity.getCreated());
		techStackResource.setDeleted(entity.getDeleted());
		techStackResource.setId(entity.getId());
		techStackResource.setModified(entity.getModified());
		techStackResource.setVersion(entity.getVersion());

		Set<TechnologyEntity> technologyEntities = entity.getTechnologies();		
		Set<TechnologyResource> technologyResources = new HashSet<TechnologyResource>();
		for (TechnologyEntity techEntity : technologyEntities) {
			TechnologyResource technologyResource = new TechnologyResource();
			technologyResource.setId(techEntity.getId());
			technologyResource.setCreated(techEntity.getCreated());
			technologyResource.setDeleted(techEntity.getDeleted());
			technologyResource.setModified(techEntity.getModified());
			technologyResource.setName(techEntity.getName());
			technologyResource.setType(techEntity.getType());
		}
		techStackResource.setTechnologies(technologyResources);
		return techStackResource;
	}
	
}
