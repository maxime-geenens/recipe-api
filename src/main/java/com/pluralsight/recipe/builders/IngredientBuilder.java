package com.pluralsight.recipe.builders;

import com.pluralsight.recipe.entities.Ingredient;
import com.pluralsight.recipe.entities.IngredientReference;
import com.pluralsight.recipe.entities.Recipe;
import com.pluralsight.recipe.entities.UnitReference;
import com.pluralsight.recipe.enums.Lang;
import com.pluralsight.recipe.exceptions.InvalidParamException;
import com.pluralsight.recipe.utils.ExceptionMessageConstants;

public class IngredientBuilder {

	private Ingredient ingredient;

	public IngredientBuilder() {
		this.ingredient = new Ingredient();
	}

	public IngredientBuilder addId(Long id) {
		ingredient.setId(id);
		return this;
	}

	public IngredientBuilder addLang(String lang) {
		ingredient.setLang(lang);
		return this;
	}

	public IngredientBuilder addQuantity(Double quantity) {
		ingredient.setQuantity(quantity);
		return this;
	}

	public IngredientBuilder addUnit(UnitReference unit) {
		ingredient.setUnitReference(unit);
		return this;
	}

	public IngredientBuilder addIngredient(IngredientReference ingredientRef) {
		ingredient.setIngredientReference(ingredientRef);
		return this;
	}

	public IngredientBuilder addRecipe(Recipe recipe) {
		ingredient.setRecipe(recipe);
		return this;
	}

	public Ingredient build() {
		validate();
		return ingredient;
	}

	// Validations
	private void validate() {

		if (ingredient.getQuantity() != null) {
			if (ingredient.getQuantity() < 0.0) {
				throw new InvalidParamException(" Quantity ::" + ExceptionMessageConstants.PARAMETER_NEGATIVE);
			}
		} else {
			throw new InvalidParamException(" Name ::" + ExceptionMessageConstants.PARAMETER_NULL);
		}

		try {
			Lang.valueOf(ingredient.getLang());
		} catch (IllegalArgumentException e) {
			throw new InvalidParamException(" Lang ::" + ExceptionMessageConstants.PARAMETER_INVALID);
		}

		if (ingredient.getUnitReference() != null) {
			if (ingredient.getUnitReference().getCode().isBlank()
					|| ingredient.getUnitReference().getCode().isEmpty()) {
				throw new InvalidParamException(
						" unitReference.code ::" + ExceptionMessageConstants.PARAMETER_BLANK_EMPTY);
			}
		} else {
			throw new InvalidParamException(" unitReference ::" + ExceptionMessageConstants.PARAMETER_NULL);
		}

		if (ingredient.getIngredientReference() != null) {
			if (ingredient.getIngredientReference().getCode().isBlank()
					|| ingredient.getIngredientReference().getCode().isEmpty()) {
				throw new InvalidParamException(
						" ingredientReference.code ::" + ExceptionMessageConstants.PARAMETER_BLANK_EMPTY);
			}
		} else {
			throw new InvalidParamException(" ingredientReference ::" + ExceptionMessageConstants.PARAMETER_NULL);
		}

		if (ingredient.getRecipe() != null) {
			if (ingredient.getRecipe().getCode().isBlank() || ingredient.getRecipe().getCode().isEmpty()) {
				throw new InvalidParamException(
						" recipe.code ::" + ExceptionMessageConstants.PARAMETER_BLANK_EMPTY);
			}
		} else {
			throw new InvalidParamException(" Recipe ::" + ExceptionMessageConstants.PARAMETER_NULL);
		}
	}

}
