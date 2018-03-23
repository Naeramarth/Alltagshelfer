package de.alltagshelfer.application.model;

import java.util.List;

import de.alltagshelfer.application.entity.Anzeige;
import lombok.Data;

@Data
public class AdvertModel {
	private List<String> errors;
	private Anzeige advert;

	public AdvertModel(List<String> errors, Anzeige advert) {
		this.errors = errors;
		this.advert = advert;
	}

	public AdvertModel() {
	}
}
