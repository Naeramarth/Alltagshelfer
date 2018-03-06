package de.alltagshelfer.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@RequestMapping("/")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/auth/error")
	public String authFail() {
		return "authentication_error";
	}
}
