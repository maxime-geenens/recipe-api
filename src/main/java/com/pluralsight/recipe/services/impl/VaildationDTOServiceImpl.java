package com.pluralsight.recipe.services.impl;

import org.springframework.stereotype.Service;

import com.pluralsight.recipe.dto.IngredientDTO;
import com.pluralsight.recipe.dto.IngredientReferenceDTO;
import com.pluralsight.recipe.dto.RecipeDTO;
import com.pluralsight.recipe.dto.StepDTO;
import com.pluralsight.recipe.enums.Lang;
import com.pluralsight.recipe.exceptions.InvalidParamException;
import com.pluralsight.recipe.services.VaildationDTOService;
import com.pluralsight.recipe.utils.ExceptionMessageConstants;
import com.pluralsight.recipe.utils.ValidationUtils;

@Service
public class VaildationDTOServiceImpl implements VaildationDTOService {

	@Override
	public boolean validateRecipeDTO(RecipeDTO recipeDTO) {

		boolean isValid = false;

		isValid = ValidationUtils.stringParamNotNullNotBlankNotEmpty(recipeDTO.getName(), "Name");
		isValid = ValidationUtils.stringParamNotNullNotBlankNotEmpty(recipeDTO.getDescription(), "Description");
		isValid = ValidationUtils.stringParamNotNullNotBlankNotEmpty(recipeDTO.getLang(), "Lang");
		isValid = ValidationUtils.stringParamNotNullNotBlankNotEmpty(recipeDTO.getTypeCode(), "TypeCode");

		try {
			Lang.valueOf(recipeDTO.getLang());
			isValid = true;
		} catch (IllegalArgumentException e) {
			throw new InvalidParamException(" Lang ::" + ExceptionMessageConstants.PARAMETER_INVALID);
		}

		return isValid;
	}

	@Override
	public boolean validateIngredientDTO(IngredientDTO ingredientDTO) {

		boolean isValid = false;

		isValid = ValidationUtils.stringParamNotNullNotBlankNotEmpty(ingredientDTO.getLang(), "Lang");

		try {
			Lang.valueOf(ingredientDTO.getLang());
			isValid = true;
		} catch (IllegalArgumentException e) {
			throw new InvalidParamException(" Lang ::" + ExceptionMessageConstants.PARAMETER_INVALID);
		}

		if (ingredientDTO.getQuantity() != null) {
			if (ingredientDTO.getQuantity() < 0.0) {
				throw new InvalidParamException(" Quantity ::" + ExceptionMessageConstants.PARAMETER_NEGATIVE);
			} else {
				isValid = true;
			}
		} else {
			throw new InvalidParamException(" Quantity ::" + ExceptionMessageConstants.PARAMETER_NULL);
		}

		if (ingredientDTO.getUnitRefId() != null) {
			isValid = true;
		} else {
			throw new InvalidParamException(" UnitRefId ::" + ExceptionMessageConstants.PARAMETER_NULL);
		}

		if (ingredientDTO.getIngredientRefId() != null) {
			isValid = true;
		} else {
			throw new InvalidParamException(" IngredientRefId ::" + ExceptionMessageConstants.PARAMETER_NULL);
		}

		if (ingredientDTO.getRecipeId() != null) {
			isValid = true;
		} else {
			throw new InvalidParamException(" RecipeId ::" + ExceptionMessageConstants.PARAMETER_NULL);
		}

		return isValid;
	}

	@Override
	public boolean validateStepDTO(StepDTO stepDTO) {

		boolean isValid = false;

		isValid = ValidationUtils.stringParamNotNullNotBlankNotEmpty(stepDTO.getLang(), "Lang");
		isValid = ValidationUtils.stringParamNotNullNotBlankNotEmpty(stepDTO.getDescription(), "Description");

		if (stepDTO.getRecipeId() != null) {
			isValid = true;
		} else {
			throw new InvalidParamException(" RecipeId ::" + ExceptionMessageConstants.PARAMETER_NULL);
		}

		if (stepDTO.getPosition() != null) {
			isValid = true;
		} else {
			throw new InvalidParamException(" Position ::" + ExceptionMessageConstants.PARAMETER_NULL);
		}

		try {
			Lang.valueOf(stepDTO.getLang());
			isValid = true;
		} catch (IllegalArgumentException e) {
			throw new InvalidParamException(" Lang ::" + ExceptionMessageConstants.PARAMETER_INVALID);
		}
		
		return isValid;
	}

	@Override
	public boolean validateIngredientReferenceDTO(IngredientReferenceDTO ingredientRefDTO) {

		boolean isValid = false;

		isValid = ValidationUtils.stringParamNotNullNotBlankNotEmpty(ingredientRefDTO.getLang(), "Lang");
		isValid = ValidationUtils.stringParamNotNullNotBlankNotEmpty(ingredientRefDTO.getName(), "Name");

		if (ingredientRefDTO.getTypeId() != null) {
			isValid = true;
		} else {
			throw new InvalidParamException(" RecipeId ::" + ExceptionMessageConstants.PARAMETER_NULL);
		}

		try {
			Lang.valueOf(ingredientRefDTO.getLang());
			isValid = true;
		} catch (IllegalArgumentException e) {
			throw new InvalidParamException(" Lang ::" + ExceptionMessageConstants.PARAMETER_INVALID);
		}
		
		return isValid;
	}

}
