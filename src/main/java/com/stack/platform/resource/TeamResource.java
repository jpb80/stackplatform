package com.stack.platform.resource;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import lombok.Getter;
import lombok.Setter;

@JsonApiResource(type="team")
@Getter @Setter
public class TeamResource extends BaseResource {

	@JsonApiId
	private Long id;

	private Long techstackid;

	private Long companyid;
}
