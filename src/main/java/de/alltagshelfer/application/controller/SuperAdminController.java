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
	public String removeAdminPost(Model model, @RequestParam String username) {
		ErrorModel em = superAdminService.removeAdmin(username);
		model.addAttribute("errors", em.getErrors());
		model.addAttribute("message", em.getMessage());
		return "super_admin_roles";
	}

	@GetMapping("/reset")
	public String reset() {
		return "super_admin_reset";
	}

	@PostMapping("/reset")
	public String resetPost(Model model, @RequestParam String checkbox) {
		if (checkbox !=null && checkbox.equals("on")) {
			ErrorModel em = superAdminService.reset();
			model.addAttribute("errors", em.getErrors());
			model.addAttribute("message", em.getMessage());
		} else {
			model.addAttribute("errors", "Bestätigen Sie die Aktion");
		}
		return "super_admin_reset";
	}

	@GetMapping("/remove")
	public String removeUser() {
		return "super_admin_remove";
	}

	@PostMapping("/remove")
	public String removeUserPost(Model model, @RequestParam String username) {
		ErrorModel em = superAdminService.removeUser(username);
		model.addAttribute("errors", em.getErrors());
		model.addAttribute("message", em.getMessage());
		return "super_admin_remove";
	}

	@GetMapping("/remove/all")
	public String removeAllUsers() {
		return "super_admin_remove_all";
	}

	@PostMapping("/remove/all")
	public String removeAllUsersPost(Model model, @RequestParam String checkbox) {
		if (checkbox !=null && checkbox.equals("on")) {
			ErrorModel em = superAdminService.removeAllUsers();
			model.addAttribute("errors", em.getErrors());
			model.addAttribute("message", em.getMessage());
		} else {
			model.addAttribute("errors", "Bestätigen Sie die Aktion");
		}
		return "super_admin_remove_all";
	}

	@GetMapping("/categories")
	public String category(Model model) {
		model.addAttribute("categories", superAdminService.findAllCategories());
		return "super_admin_category";
	}

	@PostMapping("/categories")
	public String categoryPost(HttpServletRequest request, Model model, @RequestParam String action,
			@RequestParam String name, @RequestParam(required = false) long[] category) {
		List<String> errors = new ArrayList<>();
		if (action == null)
			action = "";
		if (action.equals("create"))
			errors.addAll(superAdminService.createCategory(name));
		if (action.equals("delete"))
			errors.addAll(superAdminService.deleteCategory(category));
		FormValues fv = new FormValues();
		fv.setValues(request.getParameterMap());
		fv.setErrors(errors);
		model.addAttribute("categories_form", fv);
		return category(model);
	}
}
