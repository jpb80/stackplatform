package com.stack.platform.integration;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stack.platform.repository.ICompanyRepository;
import com.stack.platform.resource.CompanyResource;

import io.katharsis.repository.response.HttpStatus;

public class CompanyIntegrationTests extends BaseIntegrationTests {

	@Autowired
	ICompanyRepository companyRepo;
	
	private static final String RESOURCE_URL = "/api/company";
	private static final String JSON_HEADER = "application/vnd.api+json;charset=UTF-8";
	private static final String TYPE = "company";
	private static final String TEST_COMPANY_NAME = "Test Company";
	
	@Test
	public void testFindAll() {
		given(spec)
			.accept(JSON_HEADER)
			.get(RESOURCE_URL)
			.then()
			.statusCode(HttpStatus.OK_200)
			.body("data", hasSize(1));
		
	}
	
	@Test
	public void testSave() throws JsonProcessingException {
		
		CompanyResource resource = new CompanyResource();
		resource.setName(TEST_COMPANY_NAME);
		String body = getJsonAPIBody(TYPE, resource);
		
		given(this.spec)
		.accept(JSON_HEADER)
		.body(body)
		.post(RESOURCE_URL)
		.then()
		.statusCode(HttpStatus.CREATED_201)
		.body("data.type", equalTo(TYPE))
		.body("data.attributes.name", equalTo(TEST_COMPANY_NAME));		
	}
	
	
}
