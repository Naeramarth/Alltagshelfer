package de.alltagshelfer.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AnzeigenController {
	
	@RequestMapping(value = "/secured/anzeigen")
	public String login() {
		return "authentication_success";
	}

}
