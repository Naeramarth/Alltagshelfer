package de.alltagshelfer.application.model;

import java.util.List;

import de.alltagshelfer.application.entity.Anzeige;
import de.alltagshelfer.application.entity.Kategorie;
import lombok.Data;

@Data
public class AdvertsListModel {

	private List<Anzeige> adverts;
	private List<Kategorie> categories;
}
