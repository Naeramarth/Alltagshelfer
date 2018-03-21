package de.alltagshelfer.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import de.alltagshelfer.application.model.AdvertsListModel;
import de.alltagshelfer.application.service.AdvertsService;

@Controller
public class AdvertsController {

	@Autowired
	private AdvertsService service;

	@RequestMapping("/adverts")
	public String adverts(Model model, @RequestParam(defaultValue = "") String text,
			@RequestParam(required = false) String category) {
		AdvertsListModel alm = service.getAdverts(text, category);
		model.addAttribute("categories", alm.getCategories());
		model.addAttribute("adverts", alm.getAdverts());
		return "adverts";
	}

	@GetMapping("/advert/{id}")
	public String advertsEdit(Model model, @PathVariable long id) {

		return "adverts_edit";
	}
}
