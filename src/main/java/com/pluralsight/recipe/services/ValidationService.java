package com.pluralsight.recipe.services;

import com.pluralsight.recipe.dto.IngredientDTO;
import com.pluralsight.recipe.dto.IngredientReferenceDTO;
import com.pluralsight.recipe.dto.RecipeDTO;
import com.pluralsight.recipe.dto.StepDTO;
import com.pluralsight.recipe.exceptions.InvalidParamException;

public interface ValidationService {

	/**
	 * 
	 * Validates RecipeDTO :<br>
	 * 
	 * - name shall not be null, blank or empty <br>
	 * - lang shall not be null, blank or empty <br>
	 * - lang shall be a valid value form Lang Enum <br>
	 * - description shall not be null, blank or empty <br>
	 * - typeCode shall not be null, blank or empty <br>
	 * (- code shall be unique in DB)
	 * 
	 * @param recipeDTO
	 * @param checkCode (if true, evaluate if code is unique in DB)
	 * @throws InvalidParamException
	 * 
	 * @return boolean
	 */
	boolean validateRecipeDTO(RecipeDTO recipeDTO, boolean checkCode);

	/**
	 * 
	 * Validates IngredientDTO :<br>
	 * 
	 * - quantity shall not be null <br>
	 * - quantity shall not be negative <br>
	 * - lang shall not be null, blank or empty <br>
	 * - lang shall be a valid value form Lang Enum <br>
	 * - name shall not be null, blank or empty <br>
	 * - unitRefId shall not be null <br>
	 * - ingredientRefId shall not be null <br>
	 * - recipeId shall not be null <br>
	 * 
	 * @param ingredientDTO
	 * @throws InvalidParamException
	 * 
	 * @return boolean
	 */
	boolean validateIngredientDTO(IngredientDTO ingredientDTO);

	/**
	 * 
	 * Validates StepDTO :<br>
	 * 
	 * - lang shall not be null, blank or empty <br>
	 * - lang shall be a valid value form Lang Enum <br>
	 * - description shall not be null, blank or empty <br>
	 * - position shall not be null <br>
	 * - recipeId shall not be null <br>
	 * 
	 * @param stepDTO
	 * @throws InvalidParamException
	 * 
	 * @return boolean
	 */
	boolean validateStepDTO(StepDTO stepDTO);

	/**
	 * 
	 * Validates IngredientReferenceDTO :<br>
	 * 
	 * - lang shall not be null, blank or empty <br>
	 * - lang shall be a valid value form Lang Enum <br>
	 * - name shall not be null, blank or empty <br>
	 * - typeId shall not be null <br>
	 * (- code shall be unique in DB)
	 * 
	 * @param requestDTO
	 * @param checkCode (if true, evaluate if code is unique in DB)
	 * @throws InvalidParamException
	 * 
	 * @return boolean
	 */
	boolean validateIngredientReferenceDTO(IngredientReferenceDTO ingredientRefDTO, boolean b);

}
