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
	List<IngredientDTO> saveIngredientList(List<IngredientDTO> requestDTO);

	/**
	 * 
	 * Delete an ingredient for a given id
	 * 
	 * @param id
	 */
	void deleteIngredient(Long id);

	/**
	 * 
	 * Add/Update an ingredient for a given recipe
	 * 
	 * @param requestDTO
	 * @return IngredientDTO
	 */
	IngredientDTO saveIngredient(IngredientDTO dto);

}
