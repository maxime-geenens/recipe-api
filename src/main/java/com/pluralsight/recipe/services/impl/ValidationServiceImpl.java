package com.pluralsight.recipe.services.impl;

import org.springframework.stereotype.Service;

import com.pluralsight.recipe.dto.IngredientDTO;
import com.pluralsight.recipe.dto.IngredientReferenceDTO;
import com.pluralsight.recipe.dto.RecipeDTO;
import com.pluralsight.recipe.dto.StepDTO;
import com.pluralsight.recipe.dto.ToolDTO;
import com.pluralsight.recipe.dto.ToolReferenceDTO;
import com.pluralsight.recipe.exceptions.InvalidParamException;
import com.pluralsight.recipe.services.ValidationService;
import com.pluralsight.recipe.utils.ExceptionMessageConstants;
import com.pluralsight.recipe.utils.ValidationUtils;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ValidationServiceImpl implements ValidationService {

	// DTO Validations

	@Override
	public boolean validateRecipeDTO(RecipeDTO dto) {

		boolean isValid = false;

		ValidationUtils.stringParamNotNullNotBlankNotEmpty(dto.getName(),
				ExceptionMessageConstants.RECIPE_DTO + ExceptionMessageConstants.PARAM_NAME);
		ValidationUtils.stringParamNotNullNotBlankNotEmpty(dto.getDescription(),
				ExceptionMessageConstants.RECIPE_DTO + ExceptionMessageConstants.PARAM_DESCRIPTION);
		ValidationUtils.stringParamNotNullNotBlankNotEmpty(dto.getLang(),
				ExceptionMessageConstants.RECIPE_DTO + ExceptionMessageConstants.PARAM_LANG);
		ValidationUtils.validateLang(dto.getLang(), ExceptionMessageConstants.RECIPE_DTO);

		if (dto.getType() != null) {
			isValid = true;
		} else {
			throw new InvalidParamException(ValidationUtils.buildExceptionMessage(
					ExceptionMessageConstants.RECIPE_DTO + ExceptionMessageConstants.PARAM_TYPE,
					ExceptionMessageConstants.PARAMETER_NULL));
		}

		return isValid;
	}

	@Override
	public boolean validateIngredientDTO(IngredientDTO dto) {

		boolean isValid = false;

		ValidationUtils.stringParamNotNullNotBlankNotEmpty(dto.getLang(),
				ExceptionMessageConstants.INGREDIENT_DTO + ExceptionMessageConstants.PARAM_LANG);
		ValidationUtils.validateLang(dto.getLang(), ExceptionMessageConstants.INGREDIENT_DTO);

		if (dto.getQuantity() != null) {
			if (dto.getQuantity() < 0.0) {
				throw new InvalidParamException(ValidationUtils.buildExceptionMessage(
						ExceptionMessageConstants.INGREDIENT_DTO + ExceptionMessageConstants.PARAM_QUANTITY,
						ExceptionMessageConstants.PARAMETER_NEGATIVE));
			}
		} else {
			throw new InvalidParamException(ValidationUtils.buildExceptionMessage(
					ExceptionMessageConstants.INGREDIENT_DTO + ExceptionMessageConstants.PARAM_QUANTITY,
					ExceptionMessageConstants.PARAMETER_NULL));
		}

		if (dto.getUnitRef() != null) {
			isValid = true;
		} else {
			throw new InvalidParamException(ValidationUtils.buildExceptionMessage(
					ExceptionMessageConstants.INGREDIENT_DTO + ExceptionMessageConstants.PARAM_UNIT_REF,
					ExceptionMessageConstants.PARAMETER_NULL));
		}

		if (dto.getIngredientRef() != null) {
			isValid = true;
		} else {
			throw new InvalidParamException(ValidationUtils.buildExceptionMessage(
					ExceptionMessageConstants.INGREDIENT_DTO + ExceptionMessageConstants.PARAM_INGREDIENT_REF,
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

		ValidationUtils.stringParamNotNullNotBlankNotEmpty(dto.getLang(),
				ExceptionMessageConstants.STEP_DTO + ExceptionMessageConstants.PARAM_LANG);
		ValidationUtils.stringParamNotNullNotBlankNotEmpty(dto.getDescription(),
				ExceptionMessageConstants.STEP_DTO + ExceptionMessageConstants.PARAM_DESCRIPTION);
		ValidationUtils.validateLang(dto.getLang(), ExceptionMessageConstants.STEP_DTO);

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

		ValidationUtils.stringParamNotNullNotBlankNotEmpty(dto.getLang(),
				ExceptionMessageConstants.INGREDIENT_REF_DTO + ExceptionMessageConstants.PARAM_LANG);
		ValidationUtils.stringParamNotNullNotBlankNotEmpty(dto.getName(),
				ExceptionMessageConstants.INGREDIENT_REF_DTO + ExceptionMessageConstants.PARAM_NAME);
		ValidationUtils.validateLang(dto.getLang(), ExceptionMessageConstants.INGREDIENT_REF_DTO);

		if (dto.getType() != null) {
			isValid = true;
		} else {
			throw new InvalidParamException(ValidationUtils.buildExceptionMessage(
					ExceptionMessageConstants.INGREDIENT_REF_DTO + ExceptionMessageConstants.PARAM_TYPE,
					ExceptionMessageConstants.PARAMETER_NULL));
		}

		return isValid;
	}

	@Override
	public boolean validateToolDTO(ToolDTO dto) {

		boolean isValid = false;

		ValidationUtils.stringParamNotNullNotBlankNotEmpty(dto.getLang(),
				ExceptionMessageConstants.TOOL_DTO + ExceptionMessageConstants.PARAM_LANG);
		ValidationUtils.validateLang(dto.getLang(), ExceptionMessageConstants.TOOL_DTO);

		if (dto.getQuantity() != null) {
			if (dto.getQuantity() < 0.0) {
				throw new InvalidParamException(ValidationUtils.buildExceptionMessage(
						ExceptionMessageConstants.TOOL_DTO + ExceptionMessageConstants.PARAM_QUANTITY,
						ExceptionMessageConstants.PARAMETER_NEGATIVE));
			}
		} else {
			throw new InvalidParamException(ValidationUtils.buildExceptionMessage(
					ExceptionMessageConstants.TOOL_DTO + ExceptionMessageConstants.PARAM_QUANTITY,
					ExceptionMessageConstants.PARAMETER_NULL));
		}

		if (dto.getToolRef() != null) {
			isValid = true;
		} else {
			throw new InvalidParamException(ValidationUtils.buildExceptionMessage(
					ExceptionMessageConstants.TOOL_DTO + ExceptionMessageConstants.PARAM_TOOL_REF,
					ExceptionMessageConstants.PARAMETER_NULL));
		}

		if (dto.getRecipeId() != null) {
			isValid = true;
		} else {
			throw new InvalidParamException(ValidationUtils.buildExceptionMessage(
					ExceptionMessageConstants.TOOL_DTO + ExceptionMessageConstants.PARAM_RECIPE_ID,
					ExceptionMessageConstants.PARAMETER_NULL));
		}

		return isValid;
	}

	@Override
	public boolean validateToolReferenceDTO(ToolReferenceDTO dto) {

		boolean isValid = false;

		ValidationUtils.stringParamNotNullNotBlankNotEmpty(dto.getLang(),
				ExceptionMessageConstants.TOOL_REF_DTO + ExceptionMessageConstants.PARAM_LANG);
		ValidationUtils.stringParamNotNullNotBlankNotEmpty(dto.getName(),
				ExceptionMessageConstants.TOOL_REF_DTO + ExceptionMessageConstants.PARAM_NAME);
		ValidationUtils.validateLang(dto.getLang(), ExceptionMessageConstants.TOOL_REF_DTO);

		if (dto.getType() != null) {
			isValid = true;
		} else {
			throw new InvalidParamException(ValidationUtils.buildExceptionMessage(
					ExceptionMessageConstants.TOOL_REF_DTO + ExceptionMessageConstants.PARAM_TYPE,
					ExceptionMessageConstants.PARAMETER_NULL));
		}

		return isValid;
	}

}
