package com.pluralsight.recipe.builders;

import com.pluralsight.recipe.entities.Recipe;
import com.pluralsight.recipe.entities.Step;

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
		return step;
	}

}
