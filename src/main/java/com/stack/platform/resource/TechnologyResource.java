package com.stack.platform.resource;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import lombok.Getter;
import lombok.Setter;

@JsonApiResource(type="technology")
@Getter @Setter
public class TechnologyResource extends BaseResource {

	@JsonApiId
	private Long id;
	private String name;
	private String type;
	private Long techstackid;

}
