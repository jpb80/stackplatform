package com.stack.platform.resource;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiRelation;
import io.katharsis.resource.annotations.JsonApiResource;
import io.katharsis.resource.annotations.LookupIncludeBehavior;
import io.katharsis.resource.annotations.SerializeType;
import lombok.Getter;
import lombok.Setter;

@JsonApiResource(type="technology")
@Getter @Setter
public class TechnologyResource extends BaseResource {

	@JsonApiId
	private Long id;
	private String name;
	private String type;

	@JsonApiRelation(lookUp=LookupIncludeBehavior.AUTOMATICALLY_WHEN_NULL,serialize=SerializeType.ONLY_ID)
	private TechStackResource techstack;
}
