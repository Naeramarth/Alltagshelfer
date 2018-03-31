package de.alltagshelfer.application.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class AdminData {

	@Value("${admin.username}")
	private String username;

	@Value("${admin.password}")
	private String password;

}
