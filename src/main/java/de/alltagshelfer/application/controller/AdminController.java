package de.alltagshelfer.application.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import de.alltagshelfer.application.model.ErrorModel;
import de.alltagshelfer.application.model.FormValues;
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
	public String removeUsersPost(Model model, @RequestParam String username) {
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
		List<String> errors = new ArrayList<>();
		if (action == null)
			action = "";
		if (action.equals("create"))
			errors.addAll(adminService.createCategory(name));
		FormValues fv = new FormValues();
		fv.setValues(request.getParameterMap());
		fv.setErrors(errors);
		model.addAttribute("categories_form", fv);
		return category(model);
	}
}
