package com.stack.platform.services;

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
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

import com.stack.platform.entity.Role;
import com.stack.platform.entity.TeamEntity;
import com.stack.platform.entity.UserProfileEntity;
import com.stack.platform.repository.IRoleRepository;
import com.stack.platform.repository.ITeamRepository;
import com.stack.platform.repository.IUserProfileRepository;
import com.stack.platform.resource.UserProfileResource;
import com.stack.platform.services.impl.UserProfileService;

@PrepareForTest({UserProfileService.class})
public class UserProfileServiceTests extends BaseServiceTests {
 
	@InjectMocks
	IUserProfileService underTest = new UserProfileService();
	
	@Mock
	IUserProfileRepository userRepoMock;
	
	@Mock
	IRoleRepository roleRepo;
	
	@Mock
	ITeamRepository teamRepo;
	
	@Mock
	UserProfileEntity userMock;
	
	@Mock
	UserProfileResource userResourceMock;
	
	@Mock
	Role roleMock;
	
	@Mock
	TeamEntity teamMock;
	
	@Test
	public void testSave() throws Exception {
		
		PowerMockito.whenNew(UserProfileEntity.class).withNoArguments().thenReturn(userMock);
		Mockito.when(roleRepo.findById(Mockito.anyLong())).thenReturn(roleMock);
		Mockito.when(teamRepo.findById(Mockito.anyLong())).thenReturn(teamMock);
		Mockito.when(userRepoMock.save(Mockito.any(UserProfileEntity.class))).thenReturn(userMock);
		Mockito.when(userMock.getTeam()).thenReturn(new TeamEntity());
		Mockito.when(userMock.getRole()).thenReturn(new Role());
		
		UserProfileResource resource = new UserProfileResource();
		resource.setTeamid(123L);
		resource.setRoleid(1L);
		UserProfileResource result = underTest.save(resource);
		
		Assert.assertNotNull(result);
		Mockito.verify(userMock, times(1)).setCreated(Mockito.any(Date.class));
		Mockito.verify(userRepoMock,times(1)).save(Mockito.any(UserProfileEntity.class));
	}
	
	@Test
	public void testFindAll() {
		
		Set<UserProfileEntity> entities = new HashSet<UserProfileEntity>();
		userMock.setTeam(new TeamEntity());
		userMock.setRole(new Role());
		entities.add(userMock);
				
		Mockito.when(userRepoMock.findAll()).thenReturn(entities);
		Mockito.when(userMock.getTeam()).thenReturn(new TeamEntity());
		Mockito.when(userMock.getRole()).thenReturn(new Role());
		
		Collection<UserProfileResource> results = (Collection<UserProfileResource>) underTest.findAll();
		Assert.assertNotNull(results);
		Mockito.verify(userRepoMock, times(1)).findAll();	
	}
	
	@Test
	public void testDelete() {
		
		Mockito.when(userRepoMock.findOne(123L)).thenReturn(userMock);
		Mockito.when(userRepoMock.save(Mockito.any(UserProfileEntity.class))).thenReturn(userMock);	
		
		underTest.delete(123L);		
		Mockito.verify(userRepoMock, times(1)).findOne(Mockito.anyLong());
		Mockito.verify(userMock, times(1)).setDeleted(Mockito.any(Date.class));
		Mockito.verify(userRepoMock, times(1)).save(Mockito.any(UserProfileEntity.class));
	}
}
