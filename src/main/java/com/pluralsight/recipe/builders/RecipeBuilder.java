package com.pluralsight.recipe.builders;

import com.pluralsight.recipe.entities.Recipe;
import com.pluralsight.recipe.entities.RecipeType;
import com.pluralsight.recipe.enums.Lang;
import com.pluralsight.recipe.exceptions.InvalidParameterException;
import com.pluralsight.recipe.utils.ExceptionMessageConstants;

public class RecipeBuilder {

	private Recipe recipe;

	public RecipeBuilder() {
		this.recipe = new Recipe();
	}

	public RecipeBuilder addId(Long id) {
		recipe.setId(id);
		return this;
	}

	public RecipeBuilder addName(String name) {
		recipe.setName(name);
		return this;
	}

	public RecipeBuilder addDescription(String description) {
		recipe.setDescription(description);
		return this;
	}

	public RecipeBuilder addLang(String lang) {
		recipe.setLang(lang);
		return this;
	}

	public RecipeBuilder addType(RecipeType type) {
		recipe.setType(type);
		return this;
	}

	public Recipe build() {
		validate();
		addCode();
		return recipe;
	}

	private RecipeBuilder addCode() {
		recipe.setCode(recipe.getLang() + recipe.getName());
		return this;
	}

	// Validations
	private void validate() {

		if (recipe.getName() != null) {
			if (recipe.getName().isBlank() || recipe.getName().isEmpty()) {
				throw new InvalidParameterException(" Name ::" + ExceptionMessageConstants.PARAMETER_BLANK_EMPTY);
			}
		} else {
			throw new InvalidParameterException(" Name ::" + ExceptionMessageConstants.PARAMETER_NULL);
		}

		if (recipe.getDescription() != null) {
			if (recipe.getDescription().isBlank() || recipe.getDescription().isEmpty()) {
				throw new InvalidParameterException(
						" Description ::" + ExceptionMessageConstants.PARAMETER_BLANK_EMPTY);
			}
		} else {
			throw new InvalidParameterException(" Description ::" + ExceptionMessageConstants.PARAMETER_NULL);
		}

		try {
			Lang.valueOf(recipe.getLang());
		} catch (IllegalArgumentException e) {
			throw new InvalidParameterException(" Lang ::" + ExceptionMessageConstants.PARAMETER_INVALID);
		}

		if (recipe.getType() != null) {
			if (recipe.getType().getCode().isBlank() || recipe.getType().getCode().isEmpty()) {
				throw new InvalidParameterException(
						" RecipeType.code ::" + ExceptionMessageConstants.PARAMETER_BLANK_EMPTY);
			}
		} else {
			throw new InvalidParameterException(" RecipeType ::" + ExceptionMessageConstants.PARAMETER_NULL);
		}
	}

	// Only to update the recipe
	public void validateId() {
		if (recipe.getId() == null) {
			throw new InvalidParameterException(" Id ::" + ExceptionMessageConstants.PARAMETER_NULL);
		}
	}

}
