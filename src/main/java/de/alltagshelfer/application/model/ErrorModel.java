package de.alltagshelfer.application.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ErrorModel {

	private String message;
	private List<String> errors;

	public ErrorModel() {
		errors = new ArrayList<>();
	}

	public ErrorModel addErrors(List<String> errors) {
		errors.addAll(errors);
		return this;
	}

	public ErrorModel addError(String error) {
		errors.add(error);
		return this;
	}
}
