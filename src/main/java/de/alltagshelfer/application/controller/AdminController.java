package de.alltagshelfer.application.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import de.alltagshelfer.application.entity.Kategorie;
import de.alltagshelfer.application.model.ErrorModel;
import de.alltagshelfer.application.service.AdminService;
import de.alltagshelfer.application.service.KategorieService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private KategorieService catService;

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
		username = username.trim();
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
	public String removeUsersPost(Model model, @RequestParam String username) {
		username = username.trim();
		ErrorModel em = adminService.removeUser(username);
		model.addAttribute("errors", em.getErrors());
		model.addAttribute("message", em.getMessage());
		return "admin_remove";
	}

	@GetMapping("/categories")
	public String category(Model model) {
		model.addAttribute("categories", adminService.findAllCategories());
		return "admin_category";
	}

	@PostMapping("/categories")
	public String categoryPost(HttpServletRequest request, Model model, @RequestParam String action,
			@RequestParam String name) {
		name = name.trim();
		List<String> errors = new ArrayList<>();
		if (action == null)
			action = "";
		if (action.equals("create"))
			errors.addAll(adminService.createCategory(name));
		model.addAttribute("action", action);
		model.addAttribute("name", name);
		model.addAttribute("errors", errors);
		return category(model);
	}
	
	@ModelAttribute("categories")
	public List<Kategorie> getCategories() {
	    return catService.getCategories();
	}
}
