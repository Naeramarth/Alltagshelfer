package de.alltagshelfer.application.service.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import de.alltagshelfer.application.entity.Anzeige;
import de.alltagshelfer.application.entity.Benutzer;
import de.alltagshelfer.application.entity.Kategorie;
import de.alltagshelfer.application.model.AdvertModel;
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
	public List<Anzeige> getAdverts(String text, String category) {
		List<Anzeige> ret;
		if (category != null && !category.equals("")) {
			try {
				Optional<Kategorie> o = categoryRepo.findById(Long.parseLong(category));
				if (o.isPresent()) {
					ret = advertRepo.findByTitelContainingAndKategorie(text, o.get());
					return ret;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		ret = advertRepo.findByTitelContaining(text);
		return ret;
	}

	@Override
	public List<Anzeige> getAdverts(String text, String category, String name) {
		Optional<Benutzer> benutzer = repo.findByBenutzername(name);
		if (benutzer.isPresent()) {
			try {
				if (category != null && !category.equals("")) {
					Optional<Kategorie> o = categoryRepo.findById(Long.parseLong(category));
					if (o.isPresent())
						return advertRepo.findByTitelContainingAndKategorieAndBenutzer(text, o.get(), benutzer.get());
				}
				return advertRepo.findByTitelContainingAndBenutzer(text, benutzer.get());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return advertRepo.findByTitelContaining(text);
	}

	@Override
	public Anzeige getAdvert(long id) {
		return advertRepo.findById(id).get();
	}

	@Override
	public AdvertModel deleteAdvert(long id, String benutzername, boolean isAdmin) {
		List<String> errors = new ArrayList<>();
		Anzeige adv = new Anzeige();
		Optional<Anzeige> o = advertRepo.findById(id);
		if (o.isPresent()) {
			adv = o.get();
			if (adv.getBenutzer().getBenutzername().equals(benutzername) || isAdmin) {
				try {
					advertRepo.deleteById(id);
				} catch (Exception e) {
					e.printStackTrace();
					errors.add("Das Löschen ist fehlgeschlagen.");
				}
			} else {
				errors.add("Diese Anzeige gehört nicht dem angemeldeten Benutzer");
			}
		} else {
			errors.add("Diese Anzeige existiert nicht mehr.");
		}
		return new AdvertModel(errors, adv);
	}

	@Override
	public AdvertModel saveAdvert(String benutzer, LocalDate advert_until, ArtDesPreises advert_pay_type,
			long advert_category, long advert_pay, String advert_short_text, String advert_long_text,
			MultipartFile advert_image) {
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
		if (advert_image != null) {
			if (advert_image.getContentType().equals(MediaType.IMAGE_PNG_VALUE)
					|| advert_image.getContentType().equals(MediaType.IMAGE_JPEG_VALUE)
					|| advert_image.getContentType().equals(MediaType.APPLICATION_OCTET_STREAM_VALUE)) {
				if (advert_image.getSize() <= 1000000) { // 1MB
					try {
						adv.setBild(advert_image.getBytes());
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					errors.add("Das hochgeladene Bild darf nicht größer als 1 MB sein.");
				}
			} else {
				errors.add("Der Datentyp des hochgeladenen Bilds wird nicht unterstützt.");
			}
		}

		Set<ConstraintViolation<Anzeige>> set = validator.validate(adv);
		set.forEach((ConstraintViolation<Anzeige> violation) -> {
			errors.add(violation.getMessage());
		});
		if (errors.isEmpty())
			advertRepo.save(adv);
		return new AdvertModel(errors, adv);
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
	public AdvertModel editAdvert(long id, ArtDesPreises advert_pay_type, long advert_pay, long advert_category,
			LocalDate advert_until, String advert_short_text, String advert_long_text, String benutzername,
			MultipartFile advert_image, boolean isAdmin) {
		List<String> errors = new ArrayList<>();
		Anzeige adv = getAdvert(id);
		if (adv.getBenutzer().getBenutzername().equals(benutzername) || isAdmin) {
			adv.setArtDesPreises(advert_pay_type);
			adv.setPreisvorstellung(advert_pay);
			adv.setKategorie(categoryRepo.findById(advert_category).get());
			adv.setOnlineBis(advert_until);
			adv.setTitel(advert_short_text);
			adv.setBeschreibung(advert_long_text);
			if (advert_image != null) {
				if (advert_image.getContentType().equals(MediaType.IMAGE_PNG_VALUE)
						|| advert_image.getContentType().equals(MediaType.IMAGE_JPEG_VALUE)
						|| advert_image.getContentType().equals(MediaType.APPLICATION_OCTET_STREAM_VALUE)) {
					if (advert_image.getSize() <= 1000000) { // 1MB
						try {
							if (advert_image.getSize() != 0)
								adv.setBild(advert_image.getBytes());
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						errors.add("Das hochgeladene Bild darf nicht größer als 1 MB sein.");
					}
				} else {
					errors.add("Der Datentyp des hochgeladenen Bilds wird nicht unterstützt.");
				}
			}

		} else {
			errors.add("Diese Anzeige gehört nicht dem angemeldeten Benutzer");
		}

		Set<ConstraintViolation<Anzeige>> set = validator.validate(adv);
		set.forEach((ConstraintViolation<Anzeige> violation) -> {
			errors.add(violation.getMessage());
		});
		if (errors.isEmpty())
			advertRepo.save(adv);
		return new AdvertModel(errors, adv);
	}

	@Override
	public void deleteImage(long id) {
		Anzeige adv = advertRepo.findById(id).get();
		adv.setBild(null);
		advertRepo.save(adv);
	}

}
