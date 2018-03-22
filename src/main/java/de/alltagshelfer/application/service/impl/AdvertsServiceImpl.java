package de.alltagshelfer.application.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.alltagshelfer.application.entity.Anzeige;
import de.alltagshelfer.application.entity.Benutzer;
import de.alltagshelfer.application.entity.Kategorie;
import de.alltagshelfer.application.model.AdvertsListModel;
import de.alltagshelfer.application.model.ArtDesPreises;
import de.alltagshelfer.application.repository.AnzeigeRepository;
import de.alltagshelfer.application.repository.BenutzerRepository;
import de.alltagshelfer.application.repository.KategorieRepository;
import de.alltagshelfer.application.service.AdvertsService;

@Service
public class AdvertsServiceImpl implements AdvertsService {

	@Autowired
	private BenutzerRepository repo;

	@Autowired
	private AnzeigeRepository advertRepo;

	@Autowired
	private KategorieRepository categoryRepo;

	@Autowired
	private Validator validator;

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

	@Override
	public AdvertsListModel getAdverts(String text, String category, String name) {
		AdvertsListModel alm = new AdvertsListModel();
		alm.setCategories(categoryRepo.findAllByOrderByNameAsc());
		Optional<Benutzer> benutzer = repo.findByBenutzername(name);
		if (benutzer.isPresent()) {
			try {
				if (category != null && !category.equals("")) {
					Optional<Kategorie> o = categoryRepo.findById(Long.parseLong(category));
					if (o.isPresent()) {
						alm.setAdverts(
								advertRepo.findByTitelContainingAndKategorieAndBenutzer(text, o.get(), benutzer.get()));
						return alm;

					}
				}
				alm.setAdverts(advertRepo.findByTitelContainingAndBenutzer(text, benutzer.get()));
				return alm;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		alm.setAdverts(advertRepo.findByTitelContaining(text));
		return alm;
	}

	@Override
	public Anzeige getAdvert(long id) {
		return advertRepo.findById(id).get();
	}

	@Override
	public List<Kategorie> getAllCategories() {
		return categoryRepo.findAllByOrderByNameAsc();
	}

	@Override
	public List<String> deleteAdvert(long id) {
		List<String> errors = new ArrayList<>();
		try {
			advertRepo.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			errors.add("Das Löschen ist fehlgeschlagen.");
		}
		return errors;
	}

	@Override
	public List<String> saveAdvert(String benutzer, LocalDate advert_until, ArtDesPreises advert_pay_type,
			long advert_category, long advert_pay, String advert_short_text, String advert_long_text) {
		List<String> errors = new ArrayList<>();
		Anzeige adv = new Anzeige();
		adv.setBenutzer(repo.findByBenutzername(benutzer).get());
		adv.setOnlineBis(advert_until);
		adv.setArtDesPreises(advert_pay_type);
		adv.setKategorie(categoryRepo.findById(advert_category).get());
		adv.setPreisvorstellung(advert_pay);
		adv.setTitel(advert_short_text);
		adv.setBeschreibung(advert_long_text);
		adv.setErstelldatum(LocalDate.now());

		Set<ConstraintViolation<Anzeige>> set = validator.validate(adv);
		set.forEach((ConstraintViolation<Anzeige> violation) -> {
			errors.add(violation.getMessage());
		});
		if (errors.isEmpty())
			advertRepo.save(adv);
		return errors;
	}

	@Override
	public Anzeige createInitialAdvert(String name) {
		Anzeige adv = new Anzeige();
		adv.setBenutzer(repo.findByBenutzername(name).get());
		adv.setOnlineBis(LocalDate.now());
		adv.setArtDesPreises(ArtDesPreises.PAUSCHALE);
		return adv;
	}

	@Override
	public List<String> editAdvert(long id, ArtDesPreises advert_pay_type, long advert_pay, long advert_category,
			LocalDate advert_until, String advert_short_text, String advert_long_text) {
		List<String> errors = new ArrayList<>();
		Anzeige adv = getAdvert(id);
		adv.setArtDesPreises(advert_pay_type);
		adv.setPreisvorstellung(advert_pay);
		adv.setKategorie(categoryRepo.findById(advert_category).get());
		adv.setOnlineBis(advert_until);
		adv.setTitel(advert_short_text);
		adv.setBeschreibung(advert_long_text);

		Set<ConstraintViolation<Anzeige>> set = validator.validate(adv);
		set.forEach((ConstraintViolation<Anzeige> violation) -> {
			errors.add(violation.getMessage());
		});
		if (errors.isEmpty())
			advertRepo.save(adv);
		return errors;
	}

}
