package com.stack.platform.services;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.stack.platform.entity.CompanyEntity;
import com.stack.platform.entity.TeamEntity;
import com.stack.platform.repository.ICompanyRepository;
import com.stack.platform.repository.ITeamRepository;
import com.stack.platform.resource.TeamResource;
import com.stack.platform.services.impl.TeamService;

public class TeamServiceTests extends BaseServiceTests {

	@InjectMocks
	ITeamService underTest = new TeamService();
	
	@Mock
	ITeamRepository teamRepoMock;
	
	@Mock
	TeamEntity teamMock;
	
	@Mock
	CompanyEntity companyMock;

	@Mock
	ICompanyRepository companyRepoMock;
	
	@Test
	public void testFindAll() {
		
		Set<TeamEntity> entities = new HashSet<TeamEntity>();
		TeamEntity teamEntity = Mockito.mock(TeamEntity.class);
		entities.add(teamEntity);
				
		Mockito.when(teamRepoMock.findAll()).thenReturn(entities);
		
		Collection<TeamResource> results = (Collection<TeamResource>) underTest.findAll();
		Assert.assertNotNull(results);
		Mockito.verify(teamRepoMock, times(1)).findAll();	
	}
	
	@Test
	public void testSave_NullIdButDeleted_ShouldUndeleteTeam() {
		
		TeamResource resource = new TeamResource();
		resource.setCompanyid(123L);
		
		Mockito.when(teamRepoMock.findOne(Mockito.anyLong())).thenReturn(teamMock);
		Mockito.when(companyRepoMock.findOne(Mockito.anyLong())).thenReturn(companyMock);
		Mockito.when(teamRepoMock.save(Mockito.any(TeamEntity.class))).thenReturn(teamMock);
		
		TeamResource result = underTest.save(resource);
		Assert.assertNotNull(result);
		Mockito.verify(teamMock, times(2)).setDeleted(null);
		Mockito.verify(teamMock, never()).setCreated(Mockito.any(Date.class));
		Mockito.verify(teamRepoMock, times(1)).save(Mockito.any(TeamEntity.class));

	}
	
	@Test
	public void testSave_NonNullIdNotDeleted_ShouldUpdateTeam() {
		
		TeamResource resource = new TeamResource();
		resource.setId(1L);
		resource.setCompanyid(123L);
		
		Mockito.when(teamRepoMock.findOne(Mockito.anyLong())).thenReturn(teamMock);
		Mockito.when(companyRepoMock.findOne(Mockito.anyLong())).thenReturn(companyMock);
		Mockito.when(teamRepoMock.save(Mockito.any(TeamEntity.class))).thenReturn(teamMock);
		
		TeamResource result = underTest.save(resource);
		Assert.assertNotNull(result);
		Mockito.verify(teamMock, times(1)).setDeleted(null);
		Mockito.verify(teamMock, times(1)).setModified(Mockito.any(Date.class));
		Mockito.verify(teamMock, never()).setCreated(Mockito.any(Date.class));
		Mockito.verify(teamRepoMock, times(1)).save(Mockito.any(TeamEntity.class));

	}

}
