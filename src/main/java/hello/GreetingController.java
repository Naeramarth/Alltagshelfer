package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hello.service.MyService;

@Controller
public class GreetingController {

	@Autowired
	private MyService service;

	@RequestMapping("/greeting")
	public String greeting(@RequestParam(value = "name", required = false, defaultValue = "") String name,
			@RequestParam(value = "id", required = false, defaultValue = "0") int id, Model model) {
		if (id > 0)
			try {
				Client c = service.getById(id);
				model.addAttribute("name", c.getId() + ": " + c.getName());
				return "greeting";
			} catch (Exception e) {
			}
		if (!name.equals("")) {
			try {
				Client c = service.getByName(name);
				model.addAttribute("name", c.getId() + ": " + c.getName());
				return "greeting";
			} catch (Exception e) {
			}
		}
		model.addAttribute("name", "World");
		return "greeting";
	}

	@RequestMapping("/create")
	public String create(@RequestParam(value = "name", required = false, defaultValue = "") String name, Model model) {
		if (!name.equals("")) {
			service.create(name);
			model.addAttribute("name", "User " + name + " was created!");
			return "create";
		}
		model.addAttribute("name", "No user was created!");
		return "create";
	}

}
