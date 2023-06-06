package com.pluralsight.recipe.entities;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class LocalizedRecipeId implements Serializable {

	private static final long serialVersionUID = 203026021093759351L;

	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String locale;

	public LocalizedRecipeId(String locale) {
		this.locale = locale;
	}
}
