package hello;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the "Clients" database table.
 * 
 */
@Entity
@Table(name = "\"Clients\"")
@NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c")
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name = "\"id\"")
	private int id;

	@Column(name = "\"name\"")
	private String name;

	@Column(name = "\"ranking_ID\"")
	private int ranking_ID;

	@Column(name = "\"settings_ID\"")
	private int settings_ID;

	@Column(name = "\"token\"")
	private String token;

	public Client() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRanking_ID() {
		return this.ranking_ID;
	}

	public void setRanking_ID(int ranking_ID) {
		this.ranking_ID = ranking_ID;
	}

	public int getSettings_ID() {
		return this.settings_ID;
	}

	public void setSettings_ID(int settings_ID) {
		this.settings_ID = settings_ID;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}