package com.stack.platform.integration;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.removeHeaders;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.documentationConfiguration;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.restassured.RestDocumentationFilter;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;
import com.netflix.hystrix.strategy.HystrixPlugins;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations="classpath:application-integration.properties")
public abstract class BaseIntegrationTests {

	@LocalServerPort
	int port;
	
	private static final String outputDirectory = "target/generated-snippets";
	
	protected RequestSpecification spec;
	
	@Rule
	public final JUnitRestDocumentation restDocs = new JUnitRestDocumentation(outputDirectory);
	
	protected RestDocumentationFilter documentationFilter = document("{ClassName}/{methodName}",
			preprocessRequest(removeHeaders("Authorization"), prettyPrint()),
			preprocessResponse(prettyPrint()));
	
	@BeforeClass
	public static void initClass(){	
		HystrixPlugins.reset();
	//	HystrixPlugins.getInstance().registerCommandExecutionHook(new SecurityContextRegistratorCommandHook());
		
	}
	
	@Before
	public void init()  {
		RestAssured.port = port;
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		
		this.spec = new RequestSpecBuilder()
				.addFilter(documentationConfiguration(this.restDocs))
				.addFilter(documentationFilter)
				.build();
	}

}