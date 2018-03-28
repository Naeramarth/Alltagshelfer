package de.alltagshelfer.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.alltagshelfer.application.repository.AnzeigeRepository;

@RestController
@RequestMapping("/image")
public class RestImageController {

	@Autowired
	private AnzeigeRepository advertRepo;

	@RequestMapping("/{id}")
	public byte[] getImage(@PathVariable long id) {
		return advertRepo.findById(id).get().getBild();
	}
}
