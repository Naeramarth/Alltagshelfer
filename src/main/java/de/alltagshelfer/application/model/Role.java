package de.alltagshelfer.application.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Role {

	@Id
	@GeneratedValue
	private int roleId;

	@Enumerated(EnumType.STRING)
	@NotNull
	private RoleName role;

	@ManyToMany
	private Set<Benutzer> benutzer;

	public Role() {

	}

	public Role(RoleName role) {
		this.role = role;
	}
}
