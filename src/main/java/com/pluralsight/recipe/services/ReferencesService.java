package com.pluralsight.recipe.services;

import java.util.List;

import com.pluralsight.recipe.dto.IngredientReferenceDTO;
import com.pluralsight.recipe.dto.IngredientTypeDTO;
import com.pluralsight.recipe.dto.RecipeTypeDTO;
import com.pluralsight.recipe.dto.UnitReferenceDTO;
import com.pluralsight.recipe.entities.IngredientReference;
import com.pluralsight.recipe.entities.RecipeType;
import com.pluralsight.recipe.entities.UnitReference;

public interface ReferencesService {

	/**
	 * Retrieves a recipe type for a given id
	 * 
	 * @param id
	 * @return RecipeType
	 */
	RecipeType getRecipeTypeById(Long id);

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
	 * Lists all recipe types for a given lang
	 * 
	 * @param lang
	 * @return List<RecipeTypeDTO>
	 */
	List<RecipeTypeDTO> listRecipeTypesByLang(String lang);

	/**
	 * 
	 * Lists all ingredient types for a given lang
	 * 
	 * @param lang
	 * @return List<IngredientTypeDTO>
	 */
	List<IngredientTypeDTO> listIngredientTypesByLang(String lang);
}
