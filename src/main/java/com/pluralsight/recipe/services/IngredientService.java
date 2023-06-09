package com.pluralsight.recipe.services;

import java.util.List;

import com.pluralsight.recipe.dto.IngredientDTO;
import com.pluralsight.recipe.entities.Ingredient;

public interface IngredientService {

	/**
	 * Retrieves all ingredients for a given recipe
	 * 
	 * @param id Long Recipe.id
	 * @return List<IngredientDTO>
	 */
	List<IngredientDTO> listIngredientsByRecipe(Long id);

	/**
	 * Creates and persists an ingredient list
	 * 
	 * @param ingredientList
	 * @return List<Ingredient>
	 */
	List<Ingredient> createIngredientList(List<Ingredient> ingredientList);

}
