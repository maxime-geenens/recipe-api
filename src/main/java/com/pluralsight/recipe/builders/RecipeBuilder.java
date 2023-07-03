package com.pluralsight.recipe.builders;

import com.pluralsight.recipe.entities.Recipe;
import com.pluralsight.recipe.entities.RecipeType;

public class RecipeBuilder {

	private Recipe recipe;

	public RecipeBuilder() {
		this.recipe = new Recipe();
	}

	public RecipeBuilder(Recipe recipe) {
		this.recipe = recipe;
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
		addCode();
		return recipe;
	}

	private RecipeBuilder addCode() {
		recipe.setCode(recipe.getLang() + recipe.getName());
		return this;
	}

}
