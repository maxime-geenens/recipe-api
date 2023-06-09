package com.pluralsight.recipe.services;

import java.util.List;

import com.pluralsight.recipe.dto.RecipeDTO;
import com.pluralsight.recipe.dto.RecipeDetailDTO;

public interface RecipeService {

	/**
	 * 
	 * Retrieves all recipes for a given language or not. This function doesn't
	 * retrieve all recipe details.
	 * 
	 * @param lang String optional parameter referring to the recipe Language
	 * @return RecipeResponseDTO
	 */
	List<RecipeDTO> listRecipes(String lang);

	/**
	 * Retrieves 1 recipe with all details for given id
	 * 
	 * @param id Long
	 * @return
	 */
	RecipeDetailDTO getRecipeById(Long id);

	/**
	 * Create a new recipe and persist it in database
	 * 
	 * @param recipeDetailDTO
	 * @return recipeDetailDTO
	 */
	RecipeDetailDTO createRecipe(RecipeDetailDTO recipeDetailDTO);

}
