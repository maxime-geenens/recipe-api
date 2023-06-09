package com.pluralsight.recipe.services;

import com.pluralsight.recipe.entities.IngredientReference;
import com.pluralsight.recipe.entities.RecipeType;
import com.pluralsight.recipe.entities.UnitReference;

public interface ReferenceService {

	/**
	 * Retrieves a recipe type for a given code
	 * 
	 * @param typeCode
	 * @return RecipeType
	 */
	RecipeType getRecipeTypeByCode(String typeCode);

	/**
	 * Retrieves a unit reference for a given id
	 * 
	 * @param unitRefId
	 * @return UnitReference
	 */
	UnitReference getUnitReferenceById(Long unitRefId);

	/**
	 * Retrieves an Ingredient reference for a given id
	 * 
	 * @param ingredientRefId
	 * @return IngredientReference
	 */
	IngredientReference getIngredientReferenceById(Long ingredientRefId);
}
