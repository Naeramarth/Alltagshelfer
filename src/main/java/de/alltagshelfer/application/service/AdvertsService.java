package de.alltagshelfer.application.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import de.alltagshelfer.application.entity.Anzeige;
import de.alltagshelfer.application.entity.Benutzer;
import de.alltagshelfer.application.entity.Kategorie;
import de.alltagshelfer.application.model.AdvertsListModel;
import de.alltagshelfer.application.model.ArtDesPreises;

public interface AdvertsService {

	AdvertsListModel getAdverts(String text, String category);

	AdvertsListModel getAdverts(String text, String category, String name);

	Anzeige getAdvert(long id);

	List<Kategorie> getAllCategories();

	List<String> deleteAdvert(long id);

	List<String> saveAdvert(String string, LocalDate advert_until, ArtDesPreises advert_pay_type,
			long advert_category, long advert_pay, String advert_short_text, String advert_long_text);

	Anzeige createInitialAdvert(String name);

	List<String> editAdvert(long id, ArtDesPreises advert_pay_type, long advert_pay, long advert_category,
			LocalDate advert_until, String advert_short_text, String advert_long_text);

}
