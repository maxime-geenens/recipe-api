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

	/**
	 * 
	 * Creates and persists a step list
	 * 
	 * @param requestDTO
	 * @return List<Step>
	 */
	List<StepDTO> createStepList(List<StepDTO> requestDTO);

	/**
	 * 
	 * Update a list of steps for a given recipe
	 * 
	 * @param requestDTO
	 * @return List<StepDTO>
	 */
	List<StepDTO> updateStepList(List<StepDTO> requestDTO);

	/**
	 * 
	 * Delete a step for a given id
	 * 
	 * @param id
	 */
	void deleteStep(Long id);

	/**
	 * 
	 * Add a step to a given recipe
	 * 
	 * @param requestDTO
	 * @return StepDTO
	 */
	StepDTO addStep(StepDTO requestDTO);

}
