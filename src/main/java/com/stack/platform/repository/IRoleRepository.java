package com.stack.platform.repository;

import org.springframework.data.repository.Repository;

import com.stack.platform.entity.Role;

public interface IRoleRepository extends Repository<Role, Long>  {

	<S extends Role> S save(S roleEntity);
	
	Role findById(Long id);
}
