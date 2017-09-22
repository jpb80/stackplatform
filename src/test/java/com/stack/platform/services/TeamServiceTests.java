package com.stack.platform.services;

import static org.mockito.Mockito.times;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.stack.platform.entity.TeamEntity;
import com.stack.platform.repository.ITeamRepository;
import com.stack.platform.resource.TeamResource;
import com.stack.platform.services.impl.TeamService;

public class TeamServiceTests extends BaseServiceTests {

	@InjectMocks
	ITeamService underTest = new TeamService();
	
	@Autowired
	ITeamRepository teamRepoMock;
	
	@Test
	public void testFindAll() {
		
		Set<TeamEntity> entities = new HashSet<TeamEntity>();
		TeamEntity teamEntity = new TeamEntity();
		teamEntity.setCompanyid(12L);
		teamEntity.setId(1L);
		
		TeamEntity teamEntity2 = new TeamEntity();
		teamEntity2.setCompanyid(12L);
		teamEntity2.setId(2L);
		
		entities.add(teamEntity);
		entities.add(teamEntity2);
		
		Mockito.when(teamRepoMock.findAll()).thenReturn(entities);
		
		Collection<TeamResource> results = (Collection<TeamResource>) underTest.findAll();
		Assert.assertNotNull(results);
		Assert.assertTrue(results.size() == 2);
		Mockito.verify(teamRepoMock, times(1)).findAll();
	}
}
