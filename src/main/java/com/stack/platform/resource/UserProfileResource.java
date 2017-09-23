package com.stack.platform.resource;

import com.stack.platform.entity.UserProfileEntity;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import lombok.Getter;
import lombok.Setter;

@JsonApiResource(type="userprofile")
@Getter @Setter
public class UserProfileResource extends BaseResource {
	
	@JsonApiId
	private Long id;
	
	private Long teamid;
	
	private Long roleid;
	
	public UserProfileResource() {}
	
	public UserProfileResource(UserProfileEntity entity) {
		setCreated(entity.getCreated());
		setDeleted(entity.getDeleted());
		setModified(entity.getModified());
		setTeamid(entity.getTeam().getId());
		setRoleid(entity.getRole().getId());
		setId(entity.getId());
	}
}
