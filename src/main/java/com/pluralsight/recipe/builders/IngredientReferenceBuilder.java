package com.pluralsight.recipe.builders;

import com.pluralsight.recipe.entities.IngredientReference;
import com.pluralsight.recipe.entities.IngredientType;
import com.pluralsight.recipe.enums.Lang;
import com.pluralsight.recipe.exceptions.InvalidParamException;
import com.pluralsight.recipe.utils.ExceptionMessageConstants;

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

	public IngredientReferenceBuilder addCode() {
		ingredientRef.setCode(ingredientRef.getLang() + ingredientRef.getName());
		return this;
	}

	public IngredientReferenceBuilder addType(IngredientType type) {
		ingredientRef.setType(type);
		return this;
	}

	public IngredientReference build() {
		validate();
		addCode();
		return ingredientRef;
	}

	private void validate() {

		if (ingredientRef.getName() != null) {
			if (ingredientRef.getName().isBlank() || ingredientRef.getName().isEmpty()) {
				throw new InvalidParamException(" Name ::" + ExceptionMessageConstants.PARAMETER_BLANK_EMPTY);
			}
		} else {
			throw new InvalidParamException(" Name ::" + ExceptionMessageConstants.PARAMETER_NULL);
		}

		try {
			Lang.valueOf(ingredientRef.getLang());
		} catch (IllegalArgumentException e) {
			throw new InvalidParamException(" Lang ::" + ExceptionMessageConstants.PARAMETER_INVALID);
		}

		if (ingredientRef.getType() != null) {
			if (ingredientRef.getType().getCode().isBlank() || ingredientRef.getType().getCode().isEmpty()) {
				throw new InvalidParamException(
						" RecipeType.code ::" + ExceptionMessageConstants.PARAMETER_BLANK_EMPTY);
			}
		} else {
			throw new InvalidParamException(" RecipeType ::" + ExceptionMessageConstants.PARAMETER_NULL);
		}
	}

}
