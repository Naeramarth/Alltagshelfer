package de.alltagshelfer.application.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.alltagshelfer.application.entity.Kategorie;
import de.alltagshelfer.application.model.AdvertsListModel;
import de.alltagshelfer.application.repository.AnzeigeRepository;
import de.alltagshelfer.application.repository.KategorieRepository;
import de.alltagshelfer.application.service.AdvertsService;

@Service
public class AdvertsServiceImpl implements AdvertsService {

	@Autowired
	private AnzeigeRepository advertRepo;

	@Autowired
	private KategorieRepository categoryRepo;

	@Override
	public AdvertsListModel getAdverts(String text, String category) {
		AdvertsListModel alm = new AdvertsListModel();
		alm.setCategories(categoryRepo.findAllByOrderByNameAsc());
		if (category != null && !category.equals("")) {
			try {
				Optional<Kategorie> o = categoryRepo.findById(Long.parseLong(category));
				if (o.isPresent()) {
					alm.setAdverts(advertRepo.findByTitelContainingAndKategorie(text, o.get()));
					return alm;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		alm.setAdverts(advertRepo.findByTitelContaining(text));
		return alm;
	}

}
