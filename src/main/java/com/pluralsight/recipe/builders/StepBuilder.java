package com.pluralsight.recipe.builders;

import com.pluralsight.recipe.entities.Recipe;
import com.pluralsight.recipe.entities.Step;
import com.pluralsight.recipe.enums.Lang;
import com.pluralsight.recipe.exceptions.InvalidParameterException;
import com.pluralsight.recipe.utils.ExceptionMessageConstants;

public class StepBuilder {

	private Step step;

	public StepBuilder() {
		this.step = new Step();
	}

	public StepBuilder addId(Long id) {
		step.setId(id);
		return this;
	}

	public StepBuilder addLang(String lang) {
		step.setLang(lang);
		return this;
	}

	public StepBuilder addPosition(Integer position) {
		step.setPosition(position);
		return this;
	}

	public StepBuilder addDescription(String description) {
		step.setDescription(description);
		return this;
	}

	public StepBuilder addRecipe(Recipe recipe) {
		step.setRecipe(recipe);
		return this;
	}

	public Step build() {
		validate();
		return step;
	}

	// Validations
	private void validate() {

		try {
			Lang.valueOf(step.getLang());
		} catch (IllegalArgumentException e) {
			throw new InvalidParameterException(" Lang ::" + ExceptionMessageConstants.PARAMETER_INVALID);
		}

		if (step.getRecipe() != null) {
			if (step.getRecipe().getCode().isBlank() || step.getRecipe().getCode().isEmpty()) {
				throw new InvalidParameterException(
						" recipe.code ::" + ExceptionMessageConstants.PARAMETER_BLANK_EMPTY);
			}
		} else {
			throw new InvalidParameterException(" Recipe ::" + ExceptionMessageConstants.PARAMETER_NULL);
		}

	}

}
