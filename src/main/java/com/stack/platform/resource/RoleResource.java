package com.stack.platform.resource;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import lombok.Getter;
import lombok.Setter;

@JsonApiResource(type = "role")
@Getter @Setter
public class RoleResource extends BaseResource {

	@JsonApiId
	private Long id;
	private String name;	
}
