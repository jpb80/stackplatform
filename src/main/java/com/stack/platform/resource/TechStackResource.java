package com.stack.platform.resource;

import java.util.Set;

import com.stack.platform.entity.TechStackEntity;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiRelation;
import io.katharsis.resource.annotations.JsonApiResource;
import io.katharsis.resource.annotations.LookupIncludeBehavior;
import io.katharsis.resource.annotations.SerializeType;
import lombok.Getter;
import lombok.Setter;

@JsonApiResource(type="techstack")
@Getter @Setter
public class TechStackResource extends BaseResource {

	@JsonApiId
	private Long id;
	
	@JsonApiRelation(lookUp=LookupIncludeBehavior.AUTOMATICALLY_WHEN_NULL,serialize=SerializeType.ONLY_ID)
	private Set<TechnologyResource> technologies;
	
	public TechStackResource() {}
	
	public TechStackResource(TechStackEntity entity) {
		setCreated(entity.getCreated());
		setDeleted(entity.getDeleted());
		setModified(entity.getModified());
		setId(entity.getId());
		setVersion(entity.getVersion());
	}
}	
