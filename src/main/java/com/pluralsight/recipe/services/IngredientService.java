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

}
