package com.pluralsight.recipe.services;

import java.util.List;

import com.pluralsight.recipe.dto.ToolDTO;

public interface ToolService {

	/**
	 * Creates and persists a tool list
	 * 
	 * @param dtoList
	 * @return List<ToolDTO>
	 */
	List<ToolDTO> saveToolList(List<ToolDTO> dtoList);

	/**
	 * 
	 * Add/Update a tool for a given recipe
	 * 
	 * @param dto
	 * @return ToolDTO
	 */
	ToolDTO saveTool(ToolDTO dto);

	/**
	 * 
	 * Delete a tool for a given id
	 * 
	 * @param id
	 */
	void deleteTool(Long id);

	/**
	 * Retrieves all tools for a given recipe
	 * 
	 * @param id Long Recipe.id
	 * @return List<ToolDTO>
	 */
	List<ToolDTO> listToolsByRecipe(Long id);

}
