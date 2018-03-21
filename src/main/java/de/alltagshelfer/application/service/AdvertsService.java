package de.alltagshelfer.application.service;

import de.alltagshelfer.application.model.AdvertsListModel;

public interface AdvertsService {

	AdvertsListModel getAdverts(String text, String category);

}
