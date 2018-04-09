package de.alltagshelfer.application.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import de.alltagshelfer.application.entity.Anzeige;
import de.alltagshelfer.application.entity.Benutzer;
import de.alltagshelfer.application.entity.Kategorie;
import de.alltagshelfer.application.model.AdvertModel;
import de.alltagshelfer.application.model.ArtDesPreises;
import de.alltagshelfer.application.service.AdvertsService;
import de.alltagshelfer.application.service.KategorieService;

@Controller
public class AdvertsController {

	public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	public static final DateTimeFormatter LABEL_DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

	@Autowired
	private AdvertsService service;
	
	@Autowired
	private KategorieService catService;

	@RequestMapping("/adverts")
	public String adverts(Model model, @RequestParam(defaultValue = "") String text,
			@RequestParam(required = false) String category) {
		text = text.trim();
		model.addAttribute("adverts", service.getAdverts(text, category));
		return "adverts";
	}

	@RequestMapping("/adverts/user")
	public String advertsUser(Model model, @RequestParam(defaultValue = "") String text,
			@RequestParam(required = false) String category) {
		text = text.trim();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("user", true);
		model.addAttribute("adverts", service.getAdverts(text, category, auth.getName()));
		return "adverts";
	}

	@GetMapping("/advert/new")
	public String advertsCreate(Model model) {
		model.addAttribute("edit", false);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Anzeige adv = service.createInitialAdvert(auth.getName());
		setValues(model, adv, auth);
		return "adverts_edit";
	}

	@PostMapping("/advert/new")
	public String advertsCreatePost(HttpServletRequest request, Model model, @RequestParam String action,
			@RequestParam ArtDesPreises advert_pay_type, @RequestParam long advert_pay,
			@RequestParam long advert_category,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate advert_until,
			@RequestParam String advert_short_text, @RequestParam String advert_long_text,
			@RequestParam(required = false) MultipartFile advert_image) {
		advert_short_text = advert_short_text.trim();
		advert_long_text = advert_long_text.trim();
		List<String> errors = new ArrayList<>();
		AdvertModel am = new AdvertModel();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (action == null)
			action = "";
		if (action.equals("save")) {
			errors.addAll((am = service.saveAdvert(auth.getName(), advert_until, advert_pay_type, advert_category,
					advert_pay, advert_short_text, advert_long_text, advert_image)).getErrors());
			if (errors.isEmpty())
				return "redirect:/adverts";
		}
		model.addAttribute("errors", errors);
		model.addAttribute("image_name", advert_image.getOriginalFilename());
		setValues(model, am.getAdvert(), auth);
		return "adverts_edit";
	}

	@GetMapping("/advert/{id}")
	public String advertsEdit(Model model, @PathVariable long id) {
		model.addAttribute("edit", true);
		Anzeige adv = service.getAdvert(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		setValues(model, adv, auth);
		return "adverts_edit";
	}

	@PostMapping("/advert/{id}")
	public String advertsEditPost(HttpServletRequest request, Model model, @PathVariable long id,
			@RequestParam String action, @RequestParam ArtDesPreises advert_pay_type, @RequestParam long advert_pay,
			@RequestParam long advert_category,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate advert_until,
			@RequestParam String advert_short_text, @RequestParam String advert_long_text,
			@RequestParam(required = false) MultipartFile advert_image) {
		advert_short_text = advert_short_text.trim();
		advert_long_text = advert_long_text.trim();
		List<String> errors = new ArrayList<>();
		AdvertModel am = new AdvertModel();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (action == null)
			action = "";
		if (action.equals("save")) {
			errors.addAll((am = service.editAdvert(id, advert_pay_type, advert_pay, advert_category, advert_until,
					advert_short_text, advert_long_text, auth.getName(), advert_image, isAdmin(auth))).getErrors());

			if (errors.isEmpty())
				return "redirect:/adverts";
		}
		if (action.equals("delete")) {
			errors.addAll((am = service.deleteAdvert(id, auth.getName(), isAdmin(auth))).getErrors());
			if (errors.isEmpty())
				return "redirect:/adverts";
		}
		if (action.equals("delete_image")) {
			service.deleteImage(id);
			return "redirect:/advert/" + id;
		}
		model.addAttribute("errors", errors);
		model.addAttribute("edit", true);
		setValues(model, am.getAdvert(), auth);
		return "adverts_edit";
	}
	
	@ModelAttribute("categories")
	public List<Kategorie> getCategories() {
	    return catService.getCategories();
	}

	private void setValues(Model model, Anzeige adv, Authentication auth) {
		model.addAttribute("advert", adv);
		model.addAttribute("values", ArtDesPreises.values());
		otherUser(model, adv, auth);
	}

	private void otherUser(Model model, Anzeige adv, Authentication auth) {
		Benutzer user = adv.getBenutzer();
		boolean b = true;
		if (auth.getName().equals(user.getBenutzername()))
			b = false;
		else
			b = !isAdmin(auth);
		model.addAttribute("other_user", b);
	}
	
	private boolean isAdmin(Authentication auth) {
		for (GrantedAuthority a : auth.getAuthorities()) {
			if (a.getAuthority().equals("ROLE_ADMIN")) {
				return true;
			}
		}
		return false;
	}
}
