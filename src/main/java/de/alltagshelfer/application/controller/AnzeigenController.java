package de.alltagshelfer.application.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AnzeigenController {
	
	@RequestMapping("/secured/anzeigen")
	public String anzeigen() {
		return "authentication_success";
	}

}
