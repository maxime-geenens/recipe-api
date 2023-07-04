package com.pluralsight.recipe.builders;

import com.pluralsight.recipe.entities.IngredientReference;
import com.pluralsight.recipe.entities.IngredientType;

public class IngredientReferenceBuilder {

	private IngredientReference ingredientRef;

	public IngredientReferenceBuilder() {
		this.ingredientRef = new IngredientReference();
	}

	public IngredientReferenceBuilder addId(Long id) {
		ingredientRef.setId(id);
		return this;
	}

	public IngredientReferenceBuilder addLang(String lang) {
		ingredientRef.setLang(lang);
		return this;
	}

	public IngredientReferenceBuilder addName(String name) {
		ingredientRef.setName(name);
		return this;
	}

	public IngredientReferenceBuilder addType(IngredientType type) {
		ingredientRef.setType(type);
		return this;
	}

	public IngredientReference build() {
		return ingredientRef;
	}

}
