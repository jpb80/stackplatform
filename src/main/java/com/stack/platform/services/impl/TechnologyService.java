package com.stack.platform.services.impl;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stack.platform.entity.TechnologyEntity;
import com.stack.platform.repository.ITechnologyRepository;
import com.stack.platform.resource.TechnologyResource;
import com.stack.platform.services.ITechnologyService;

@Service
public class TechnologyService implements ITechnologyService {

	@Autowired
	ITechnologyRepository techRepo;
	
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
			entity.setTechstack(resource.getTechstack());
		} else {
			entity = techRepo.findById(resourceId);
			entity.setModified(new Date());
		}
		return convertToResource(entity);
	}
	
	private TechnologyResource convertToResource(TechnologyEntity entity) {
		return new TechnologyResource(entity);
	}
}
