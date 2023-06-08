package com.pluralsight.recipe.services;

import java.util.List;

import com.pluralsight.recipe.dto.StepDTO;

public interface StepService {

	/**
	 * Retrieves all Steps for a given recipe
	 * 
	 * @param id Long Recipe.id
	 * @return List<StepDTO>
	 */
	List<StepDTO> listStepsByRecipe(Long id);

}
