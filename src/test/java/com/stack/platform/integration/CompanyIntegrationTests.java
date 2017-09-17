package com.stack.platform.integration;

import static com.jayway.restassured.RestAssured.given;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import com.stack.platform.repository.ICompanyRepository;

import io.katharsis.repository.response.HttpStatus;

public class CompanyIntegrationTests extends BaseIntegrationTests {

	@Autowired
	ICompanyRepository companyRepo;
	
	private static final String RESOURCE_URL = "/api/company";
	private static final String JSON_HEADER = "application/vnd.api+json;charset=UTF-8";
	
	@Test
	public void testFindAll() {
		given(spec)
			.accept(JSON_HEADER)
			.get(RESOURCE_URL)
			.then()
			.statusCode(HttpStatus.OK_200)
			.body("data", hasSize(1));
		
	}
}
