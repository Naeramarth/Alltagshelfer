package de.alltagshelfer.application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.alltagshelfer.application.entity.Anzeige;
import de.alltagshelfer.application.repository.AnzeigeRepository;

@RestController
@RequestMapping("/image")
public class RestImageController {

	@Autowired
	private AnzeigeRepository advertRepo;

	@RequestMapping("/{id}")
	public byte[] getImage(@PathVariable long id) {
		Optional<Anzeige> adv = advertRepo.findById(id);
		if (adv.isPresent())
			return advertRepo.findById(id).get().getBild();
		return null;
	}
}
