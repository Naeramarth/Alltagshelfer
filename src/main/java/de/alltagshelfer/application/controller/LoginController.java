package de.alltagshelfer.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import de.alltagshelfer.application.service.KategorieService;

@Controller
public class LoginController {

	@Autowired
	private KategorieService catService;

	@RequestMapping("/login")
	public String login() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return "redirect:/success";
		}
		return "login";
	}

	@RequestMapping("/success")
	public String success(Model model) {
		model.addAttribute("categories", catService.getCategories());
		return "authentication_success";
	}

	@RequestMapping("/auth/error")
	public String authFail() {
		return "authentication_error";
	}
}
