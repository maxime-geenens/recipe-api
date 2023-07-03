package com.pluralsight.recipe.services;

import java.util.List;

import com.pluralsight.recipe.dto.RecipeDTO;
import com.pluralsight.recipe.entities.Recipe;

public interface RecipeService {

	/**
	 * 
	 * Retrieves all recipes for a given language or not. This function doesn't
	 * retrieve all recipe details.
	 * 
	 * @param lang String optional parameter referring to the recipe Language
	 * @return RecipeResponseDTO
	 */
	List<RecipeDTO> listRecipesByLang(String lang);

	/**
	 * Retrieves 1 recipe with all details for given id
	 * 
	 * @param id Long
	 * @return RecipeDTO
	 */
	RecipeDTO getRecipeDTOById(Long id);

	/**
	 * Retrieves 1 recipe with all details for given id
	 * 
	 * @param id Long
	 * @return Recipe
	 */
	Recipe getRecipeById(Long id);

	/**
	 * Create/Update a recipe
	 * 
	 * @param recipeDTO
	 * @return RecipeDetailDTO
	 */
	RecipeDTO saveRecipe(RecipeDTO dto);

	/**
	 * 
	 * Delete a recipe with given id.
	 * 
	 * @param id
	 */
	void deleteRecipe(Long id);

	/**
	 * 
	 * Find a recipe for a given code
	 * 
	 * @param code
	 * @return Recipe
	 */
	Recipe findByCode(String code);

}
