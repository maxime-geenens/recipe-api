package com.pluralsight.recipe.services;

import java.util.List;

import com.pluralsight.recipe.dto.IngredientDTO;

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
	 * @param requestDTO
	 * @return List<Ingredient>
	 */
	List<IngredientDTO> createIngredientList(List<IngredientDTO> requestDTO);

	/**
	 * 
	 * Update a list of ingredients for a given recipe
	 * 
	 * @param requestDTO
	 * @return List<IngredientDTO>
	 */
	List<IngredientDTO> updateIngredientList(List<IngredientDTO> requestDTO);

	/**
	 * 
	 * Delete an ingredient for a given id
	 * 
	 * @param id
	 */
	void deleteIngredient(Long id);

	/**
	 * 
	 * Add an ingredient to a given recipe
	 * 
	 * @param requestDTO
	 * @return IngredientDTO
	 */
	IngredientDTO addIngredient(IngredientDTO requestDTO);

}
