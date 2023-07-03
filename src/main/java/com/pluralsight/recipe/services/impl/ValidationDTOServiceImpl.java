package com.pluralsight.recipe.services.impl;

import org.springframework.stereotype.Service;

import com.pluralsight.recipe.dto.IngredientDTO;
import com.pluralsight.recipe.dto.IngredientReferenceDTO;
import com.pluralsight.recipe.dto.RecipeDTO;
import com.pluralsight.recipe.dto.StepDTO;
import com.pluralsight.recipe.exceptions.InvalidParamException;
import com.pluralsight.recipe.services.ValidationDTOService;
import com.pluralsight.recipe.utils.ExceptionMessageConstants;
import com.pluralsight.recipe.utils.ValidationUtils;

@Service
public class ValidationDTOServiceImpl implements ValidationDTOService {

	@Override
	public boolean validateRecipeDTO(RecipeDTO dto) {

		boolean isValid = false;

		isValid = ValidationUtils.stringParamNotNullNotBlankNotEmpty(dto.getName(),
				ExceptionMessageConstants.RECIPE_DTO + ExceptionMessageConstants.PARAM_NAME);
		isValid = ValidationUtils.stringParamNotNullNotBlankNotEmpty(dto.getDescription(),
				ExceptionMessageConstants.RECIPE_DTO + ExceptionMessageConstants.PARAM_DESCRIPTION);
		isValid = ValidationUtils.stringParamNotNullNotBlankNotEmpty(dto.getLang(),
				ExceptionMessageConstants.RECIPE_DTO + ExceptionMessageConstants.PARAM_LANG);
		isValid = ValidationUtils.validateLang(dto.getLang(), ExceptionMessageConstants.RECIPE_DTO);

		if (dto.getTypeId() != null) {
			isValid = true;
		} else {
			throw new InvalidParamException(ValidationUtils.buildExceptionMessage(
					ExceptionMessageConstants.RECIPE_DTO + ExceptionMessageConstants.PARAM_TYPE_ID,
					ExceptionMessageConstants.PARAMETER_NULL));
		}

		return isValid;
	}

	@Override
	public boolean validateIngredientDTO(IngredientDTO dto) {

		boolean isValid = false;

		isValid = ValidationUtils.stringParamNotNullNotBlankNotEmpty(dto.getLang(),
				ExceptionMessageConstants.INGREDIENT_DTO + ExceptionMessageConstants.PARAM_LANG);
		isValid = ValidationUtils.validateLang(dto.getLang(), ExceptionMessageConstants.INGREDIENT_DTO);

		if (dto.getQuantity() != null) {
			if (dto.getQuantity() < 0.0) {
				throw new InvalidParamException(ValidationUtils.buildExceptionMessage(
						ExceptionMessageConstants.INGREDIENT_DTO + ExceptionMessageConstants.PARAM_QUANTITY,
						ExceptionMessageConstants.PARAMETER_NEGATIVE));
			} else {
				isValid = true;
			}
		} else {
			throw new InvalidParamException(ValidationUtils.buildExceptionMessage(
					ExceptionMessageConstants.INGREDIENT_DTO + ExceptionMessageConstants.PARAM_QUANTITY,
					ExceptionMessageConstants.PARAMETER_NULL));
		}

		if (dto.getUnitRefId() != null) {
			isValid = true;
		} else {
			throw new InvalidParamException(ValidationUtils.buildExceptionMessage(
					ExceptionMessageConstants.INGREDIENT_DTO + ExceptionMessageConstants.PARAM_UNIT_REF_ID,
					ExceptionMessageConstants.PARAMETER_NULL));
		}

		if (dto.getIngredientRefId() != null) {
			isValid = true;
		} else {
			throw new InvalidParamException(ValidationUtils.buildExceptionMessage(
					ExceptionMessageConstants.INGREDIENT_DTO + ExceptionMessageConstants.PARAM_INGREDIENT_REF_ID,
					ExceptionMessageConstants.PARAMETER_NULL));
		}

		if (dto.getRecipeId() != null) {
			isValid = true;
		} else {
			throw new InvalidParamException(ValidationUtils.buildExceptionMessage(
					ExceptionMessageConstants.INGREDIENT_DTO + ExceptionMessageConstants.PARAM_RECIPE_ID,
					ExceptionMessageConstants.PARAMETER_NULL));
		}

		return isValid;
	}

	@Override
	public boolean validateStepDTO(StepDTO dto) {

		boolean isValid = false;

		isValid = ValidationUtils.stringParamNotNullNotBlankNotEmpty(dto.getLang(),
				ExceptionMessageConstants.STEP_DTO + ExceptionMessageConstants.PARAM_LANG);
		isValid = ValidationUtils.stringParamNotNullNotBlankNotEmpty(dto.getDescription(),
				ExceptionMessageConstants.STEP_DTO + ExceptionMessageConstants.PARAM_DESCRIPTION);
		isValid = ValidationUtils.validateLang(dto.getLang(), ExceptionMessageConstants.STEP_DTO);

		if (dto.getRecipeId() != null) {
			isValid = true;
		} else {
			throw new InvalidParamException(ValidationUtils.buildExceptionMessage(
					ExceptionMessageConstants.STEP_DTO + ExceptionMessageConstants.PARAM_RECIPE_ID,
					ExceptionMessageConstants.PARAMETER_NULL));
		}

		if (dto.getPosition() != null) {
			if (dto.getPosition() < 0.0) {
				throw new InvalidParamException(ValidationUtils.buildExceptionMessage(
						ExceptionMessageConstants.STEP_DTO + ExceptionMessageConstants.PARAM_POSITION,
						ExceptionMessageConstants.PARAMETER_NEGATIVE));
			} else {
				isValid = true;
			}
		} else {
			throw new InvalidParamException(ValidationUtils.buildExceptionMessage(
					ExceptionMessageConstants.STEP_DTO + ExceptionMessageConstants.PARAM_POSITION,
					ExceptionMessageConstants.PARAMETER_NULL));
		}

		return isValid;
	}

	@Override
	public boolean validateIngredientReferenceDTO(IngredientReferenceDTO dto) {

		boolean isValid = false;

		isValid = ValidationUtils.stringParamNotNullNotBlankNotEmpty(dto.getLang(),
				ExceptionMessageConstants.INGREDIENT_REF_DTO + ExceptionMessageConstants.PARAM_LANG);
		isValid = ValidationUtils.stringParamNotNullNotBlankNotEmpty(dto.getName(),
				ExceptionMessageConstants.INGREDIENT_REF_DTO + ExceptionMessageConstants.PARAM_NAME);
		isValid = ValidationUtils.validateLang(dto.getLang(), ExceptionMessageConstants.INGREDIENT_REF_DTO);

		if (dto.getTypeId() != null) {
			isValid = true;
		} else {
			throw new InvalidParamException(ValidationUtils.buildExceptionMessage(
					ExceptionMessageConstants.INGREDIENT_REF_DTO + ExceptionMessageConstants.PARAM_TYPE_ID,
					ExceptionMessageConstants.PARAMETER_NULL));
		}

		return isValid;
	}

}
