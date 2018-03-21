package de.alltagshelfer.application.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.alltagshelfer.application.model.ErrorModel;
import de.alltagshelfer.application.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@RequestMapping("/")
	public String adminTools() {
		return "admin_tools";
	}

	@GetMapping("/add")
	public String addAdmin() {
		return "admin_add";
	}

	@PostMapping("/add")
	public String addAdminPost(Model model, @RequestParam String username) {
		ErrorModel em = adminService.addAdmin(username);
		model.addAttribute("errors", em.getErrors());
		model.addAttribute("message", em.getMessage());
		return "admin_add";
	}

	@GetMapping("/remove")
	public String removeUser() {
		return "admin_remove";
	}

	@PostMapping("/remove")
	public String removeAllUsersPost(Model model, @RequestParam String username) {
		ErrorModel em = adminService.removeUser(username);
		model.addAttribute("errors", em.getErrors());
		model.addAttribute("message", em.getMessage());
		return "admin_remove";
	}
}
