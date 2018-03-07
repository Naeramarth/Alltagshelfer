package de.alltagshelfer.application.repository;

import org.springframework.data.repository.CrudRepository;

import de.alltagshelfer.application.entity.Role;
import de.alltagshelfer.application.model.RoleName;

public interface RoleRepository extends CrudRepository<Role, Long> {

	public Role findByRole(RoleName role);

}
