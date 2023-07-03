package com.pluralsight.recipe.services;

import java.util.List;

import com.pluralsight.recipe.dto.IngredientReferenceDTO;
import com.pluralsight.recipe.dto.UnitReferenceDTO;
import com.pluralsight.recipe.entities.IngredientReference;
import com.pluralsight.recipe.entities.RecipeType;
import com.pluralsight.recipe.entities.UnitReference;

public interface ReferencesService {

	/**
	 * Retrieves a recipe type for a given code
	 * 
	 * @param typeCode
	 * @return RecipeType
	 */
	RecipeType getRecipeTypeByCode(String typeCode);

	/**
	 * Retrieves a unit reference for a given id
	 * 
	 * @param unitRefId
	 * @return UnitReference
	 */
	UnitReference getUnitReferenceById(Long unitRefId);

	/**
	 * Retrieves an Ingredient reference for a given id
	 * 
	 * @param ingredientRefId
	 * @return IngredientReference
	 */
	IngredientReference getIngredientReferenceById(Long ingredientRefId);

	/**
	 * 
	 * Retrieves all ingredient reference for a given type and lang
	 * 
	 * @param type
	 * @param lang
	 * @return List<IngredientReferenceDTO>
	 */
	List<IngredientReferenceDTO> listIngredientsRefByTypeAndLang(String type, String lang);

	/**
	 * 
	 * Retrieves all unit references by lang
	 * 
	 * @param lang
	 * @return List<UnitReferenceDTO>
	 */
	List<UnitReferenceDTO> listUnitsByLang(String lang);

	/**
	 * 
	 * Add and persists a new ingredient reference
	 * 
	 * @param requestDTO
	 * @return IngredientReferenceDTO
	 */
	IngredientReferenceDTO addIngredientRef(IngredientReferenceDTO requestDTO);

	/**
	 * 
	 * Find IngredientReference for a given code
	 * 
	 * @param code
	 * @return IngredientReference
	 */
	IngredientReference findIngredientRefByCode(String code);
}
