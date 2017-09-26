package com.stack.platform.services;

import static org.mockito.Mockito.times;

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
import com.stack.platform.repository.IRoleRepository;
import com.stack.platform.resource.RoleResource;
import com.stack.platform.services.impl.RoleService;

@PrepareForTest({RoleService.class})
public class RoleServiceTests extends BaseServiceTests {

	@InjectMocks
	IRoleService underTest = new RoleService();
	
	@Mock
	IRoleRepository roleRepoMock;
	
	@Mock
	Role roleMock;
	
	@Test
	public void testFindAll() {
				
		Set<Role> companyEntities = new HashSet<Role>();
		companyEntities.add(roleMock);
		
		Mockito.when(roleRepoMock.findAll()).thenReturn(companyEntities);
		
		Iterable<RoleResource> response = underTest.findAll();
		
		Assert.assertNotNull(response);
		Mockito.verify(roleRepoMock, times(1)).findAll();
	}
	
	@Test
	public void testSave_NullId_ShouldCreateNewCompany() throws Exception {
		
		PowerMockito.whenNew(Role.class).withNoArguments().thenReturn(roleMock);
		Mockito.when(roleRepoMock.save(Mockito.any(Role.class))).thenReturn(roleMock);
		
		RoleResource resource = new RoleResource();
		RoleResource result = underTest.save(resource);
		
		Assert.assertNotNull(result);
		Mockito.verify(roleMock, times(1)).setCreated(Mockito.any(Date.class));
		Mockito.verify(roleRepoMock,times(1)).save(Mockito.any(Role.class));
	}
	
	@Test
	public void testDelete() {
		
		Mockito.when(roleRepoMock.findOne(123L)).thenReturn(roleMock);
		Mockito.when(roleRepoMock.save(Mockito.any(Role.class))).thenReturn(roleMock);	
		
		underTest.delete(123L);		
		Mockito.verify(roleRepoMock, times(1)).findOne(Mockito.anyLong());
		Mockito.verify(roleMock, times(1)).setDeleted(Mockito.any(Date.class));
		Mockito.verify(roleRepoMock, times(1)).save(Mockito.any(Role.class));
	}
	
	@Test
	public void testFindOne() {
		
		Mockito.when(roleRepoMock.findOne(Mockito.anyLong())).thenReturn(roleMock);
		RoleResource result = underTest.findOne(1L);
		Mockito.verify(roleRepoMock, times(1)).findOne(Mockito.anyLong());
	}

}
