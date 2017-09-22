package com.stack.platform.resource;

import com.stack.platform.entity.TeamEntity;

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
	
	public TeamResource() {}
	
	public TeamResource(TeamEntity entity) {
		setCompanyid(entity.getCompanyid());
		setCreated(entity.getCreated());
		setDeleted(entity.getDeleted());
		setId(entity.getId());
		setModified(entity.getModified());
		setTechstackid(entity.getTechstackid());
		setVersion(entity.getVersion());
	}
}
