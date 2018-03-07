package de.alltagshelfer.application.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import de.alltagshelfer.application.model.ErrorModel;
import de.alltagshelfer.application.service.SuperAdminService;

@Controller
@RequestMapping("/super/admin")
public class SuperAdminController {

	@Autowired
	private SuperAdminService superAdminService;

	@RequestMapping("/")
	public String superAdminTools() {
		return "super_admin_tools";
	}

	@GetMapping("/roles")
	public String removeAdmin() {
		return "super_admin_roles";
	}

	@PostMapping("/roles")
	public String removeAdminPost(HttpServletRequest request, Model model) {
		String username = request.getParameter("username");
		ErrorModel em = superAdminService.removeAdmin(username);
		model.addAttribute("errors", em.getErrors());
		model.addAttribute("message", em.getMessage());
		return "super_admin_roles";
	}
}
