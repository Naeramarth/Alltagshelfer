package de.alltagshelfer.application.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import de.alltagshelfer.application.model.Role;
import de.alltagshelfer.application.model.RoleName;

public interface RoleRepository extends CrudRepository<Role, Long> {

	public Set<Role> findByRole(RoleName role);

}
