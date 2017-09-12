package com.stack.platform.resource;

import java.util.Set;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiRelation;
import io.katharsis.resource.annotations.JsonApiResource;
import io.katharsis.resource.annotations.LookupIncludeBehavior;
import io.katharsis.resource.annotations.SerializeType;
import lombok.Getter;
import lombok.Setter;

@JsonApiResource(type="company")
@Getter @Setter
public class CompanyResource extends BaseResource {

	@JsonApiId
	private Long id;
	
	@JsonApiRelation(lookUp=LookupIncludeBehavior.AUTOMATICALLY_WHEN_NULL, serialize=SerializeType.ONLY_ID)
	private Set<TeamResource> companyTeams;
}
