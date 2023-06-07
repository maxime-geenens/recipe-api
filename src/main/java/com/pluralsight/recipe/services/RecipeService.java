package com.pluralsight.recipe.services;

import java.util.List;

import com.pluralsight.recipe.dto.RecipeDTO;

public interface RecipeService {

	/**
	 * 
	 * Retrieves all recipes for a given language or not.
	 * This function doesn't retrieve all recipe details.
	 * 
	 * @param lang String optional parameter referring to the recipe Language 
	 * @return RecipeResponseDTO
	 */
	List<RecipeDTO> listRecipes(String lang);

	/**
	 * Retrieves 1 recipe with given id
	 * @param id Long
	 * @return
	 */
	RecipeDTO getRecipeById(Long id);

}
