package com.stack.platform.resource;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import lombok.Getter;
import lombok.Setter;

@JsonApiResource(type="userprofile")
@Getter @Setter
public class UserProfileResource extends BaseResource {
	
	@JsonApiId
	private Long id;
}
