package com.stack.platform.resource;

import com.stack.platform.entity.Role;
import com.stack.platform.entity.TeamEntity;
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
		TeamEntity team = entity.getTeam();
		setTeamid(team.getId());
		Role role = entity.getRole();
		setRoleid(role.getId());
		setId(entity.getId());
	}
}
