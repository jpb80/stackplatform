package com.stack.platform.services;

import static org.mockito.Mockito.times;

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
import com.stack.platform.services.impl.CompanyService;

public class CompanyServiceTests extends BaseServiceTests {

	@InjectMocks
	ICompanyService companyService = new CompanyService();
	
	@Mock
	ICompanyRepository companyRepoMock;
	
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
		
		Iterable<CompanyResource> response = companyService.findAll();
		
		Assert.assertNotNull(response);
		Mockito.verify(companyRepoMock, times(1)).findAll();
		String name = null;
		for (CompanyResource resource : response) {
			name = resource.getName();
		}
		Assert.assertTrue(name.equals("test"));
	}
}
