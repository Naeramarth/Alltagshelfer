package de.alltagshelfer.application.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import de.alltagshelfer.application.model.RoleName;
import lombok.Data;

@Entity
@Data
public class Role {

	@Id
	private int roleId;

	@Enumerated(EnumType.STRING)
	@NotNull
	private RoleName role;

	@ManyToMany
	private Set<Benutzer> benutzer;

	public Role() {

	}

	public Role(int roleId, RoleName role) {
		this.roleId = roleId;
		this.role = role;
	}
}
