package com.stack.platform.services;

import static org.mockito.Mockito.times;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.stack.platform.entity.CompanyEntity;
import com.stack.platform.repository.ICompanyRepository;
import com.stack.platform.resource.CompanyResource;
import com.stack.platform.resource.TeamResource;
import com.stack.platform.services.impl.CompanyService;

public class CompanyServiceTests extends BaseServiceTests {

	@InjectMocks
	ICompanyService underTest = new CompanyService();
	
	@Mock
	ICompanyRepository companyRepoMock;
	
	@Mock
	CompanyEntity companyMock;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testFindAll() {
				
		Set<CompanyEntity> companyEntities = new HashSet<CompanyEntity>();
		CompanyEntity company = new CompanyEntity();
		company.setName("test");
		companyEntities.add(company);
		
		Mockito.when(companyRepoMock.findAll()).thenReturn(companyEntities);
		
		Iterable<CompanyResource> response = underTest.findAll();
		
		Assert.assertNotNull(response);
		Mockito.verify(companyRepoMock, times(1)).findAll();
		String name = null;
		for (CompanyResource resource : response) {
			name = resource.getName();
		}
		Assert.assertTrue(name.equals("test"));
	}
	
	@Test
	public void testSave_NullId_ShouldCreateNewCompany() {
		
		CompanyEntity entity = new CompanyEntity();
		entity.setName("New Company");
		
		Mockito.when(companyRepoMock.save(Mockito.any(CompanyEntity.class))).thenReturn(entity);
		CompanyResource resource = new CompanyResource();
		resource.setName("New Company");
		CompanyResource result = underTest.save(resource);
		
		Assert.assertNotNull(result);
		Assert.assertTrue(result.getName().equals("New Company"));
		Mockito.verify(companyRepoMock,times(1)).save(Mockito.any(CompanyEntity.class));
	}
	
	@Test
	public void testDelete() {
		
		Mockito.when(companyRepoMock.findOne(123L)).thenReturn(companyMock);
		Mockito.when(companyRepoMock.save(Mockito.any(CompanyEntity.class))).thenReturn(companyMock);	
		
		underTest.delete(123L);		
		Mockito.verify(companyRepoMock, times(1)).findOne(Mockito.anyLong());
		Mockito.verify(companyMock, times(1)).setDeleted(Mockito.any(Date.class));
		Mockito.verify(companyRepoMock, times(1)).save(Mockito.any(CompanyEntity.class));
	}
	
	@Test
	public void testFindOne() {
		
		Mockito.when(companyRepoMock.findOne(Mockito.anyLong())).thenReturn(companyMock);
		CompanyResource result = underTest.findOne(1L);
		Mockito.verify(companyRepoMock, times(1)).findOne(Mockito.anyLong());
	}
	
}
