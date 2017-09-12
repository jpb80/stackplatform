package com.stack.platform.resource;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import lombok.Getter;
import lombok.Setter;

@JsonApiResource(type="techstack")
@Getter @Setter
public class TechStackResource extends BaseResource {

	@JsonApiId
	private Long id;
}	
