package com.pluralsight.recipe.builders;

import com.pluralsight.recipe.entities.Ingredient;
import com.pluralsight.recipe.entities.IngredientReference;
import com.pluralsight.recipe.entities.Recipe;
import com.pluralsight.recipe.entities.UnitReference;

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
		return ingredient;
	}

}
