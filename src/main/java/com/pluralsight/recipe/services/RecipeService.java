package com.pluralsight.recipe.services;

import java.util.List;

import com.pluralsight.recipe.dto.RecipeDTO;
import com.pluralsight.recipe.entities.Recipe;
import com.pluralsight.recipe.entities.RecipeType;

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
	 * @return
	 */
	RecipeDTO getRecipeById(Long id);

	/**
	 * Create a new recipe and persist it in database
	 * 
	 * @param recipeDTO
	 * @return RecipeDetailDTO
	 */
	RecipeDTO saveRecipe(Recipe recipe);

	/**
	 * 
	 * Update a recipe
	 * 
	 * @param recipeDTO
	 * @param recipeType 
	 * @return RecipeDetailDTO
	 */
	RecipeDTO updateRecipe(RecipeDTO recipeDTO, RecipeType recipeType);

	/**
	 * 
	 * Delete a recipe with given id.
	 * 
	 * @param id
	 */
	void deleteRecipe(Long id);

	/**
	 * 
	 * Build a Recipe entity from DTO and RecipeType
	 * 
	 * @param requestDTO
	 * @param recipeType
	 * @return Recipe
	 */
	Recipe buildRecipe(RecipeDTO requestDTO, RecipeType recipeType);

}
