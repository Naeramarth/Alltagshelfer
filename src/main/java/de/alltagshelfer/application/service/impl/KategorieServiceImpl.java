package de.alltagshelfer.application.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.alltagshelfer.application.entity.Kategorie;
import de.alltagshelfer.application.repository.KategorieRepository;
import de.alltagshelfer.application.service.KategorieService;

@Service
public class KategorieServiceImpl implements KategorieService {

	@Autowired
	private KategorieRepository catRepo;

	@Override
	public List<Kategorie> getCategories() {
		return catRepo.findAllByOrderByNameAsc();
	}

}
