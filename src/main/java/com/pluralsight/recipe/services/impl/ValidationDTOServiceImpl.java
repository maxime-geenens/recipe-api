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

		isValid = ValidationUtils.stringParamNotNullNotBlankNotEmpty(dto.getName(), "Name");
		isValid = ValidationUtils.stringParamNotNullNotBlankNotEmpty(dto.getDescription(), "Description");
		isValid = ValidationUtils.stringParamNotNullNotBlankNotEmpty(dto.getLang(), "Lang");
		isValid = ValidationUtils.stringParamNotNullNotBlankNotEmpty(dto.getTypeCode(), "TypeCode");
		isValid = ValidationUtils.validateLang(dto.getLang());

		return isValid;
	}

	@Override
	public boolean validateIngredientDTO(IngredientDTO dto) {

		boolean isValid = false;

		isValid = ValidationUtils.stringParamNotNullNotBlankNotEmpty(dto.getLang(), "Lang");
		isValid = ValidationUtils.validateLang(dto.getLang());

		if (dto.getQuantity() != null) {
			if (dto.getQuantity() < 0.0) {
				throw new InvalidParamException(" Quantity ::" + ExceptionMessageConstants.PARAMETER_NEGATIVE);
			} else {
				isValid = true;
			}
		} else {
			throw new InvalidParamException(" Quantity ::" + ExceptionMessageConstants.PARAMETER_NULL);
		}

		if (dto.getUnitRefId() != null) {
			isValid = true;
		} else {
			throw new InvalidParamException(" UnitRefId ::" + ExceptionMessageConstants.PARAMETER_NULL);
		}

		if (dto.getIngredientRefId() != null) {
			isValid = true;
		} else {
			throw new InvalidParamException(" IngredientRefId ::" + ExceptionMessageConstants.PARAMETER_NULL);
		}

		if (dto.getRecipeId() != null) {
			isValid = true;
		} else {
			throw new InvalidParamException(" RecipeId ::" + ExceptionMessageConstants.PARAMETER_NULL);
		}

		return isValid;
	}

	@Override
	public boolean validateStepDTO(StepDTO dto) {

		boolean isValid = false;

		isValid = ValidationUtils.stringParamNotNullNotBlankNotEmpty(dto.getLang(), "Lang");
		isValid = ValidationUtils.stringParamNotNullNotBlankNotEmpty(dto.getDescription(), "Description");
		isValid = ValidationUtils.validateLang(dto.getLang());

		if (dto.getRecipeId() != null) {
			isValid = true;
		} else {
			throw new InvalidParamException(" RecipeId ::" + ExceptionMessageConstants.PARAMETER_NULL);
		}

		if (dto.getPosition() != null) {
			if (dto.getPosition() < 0.0) {
				throw new InvalidParamException(" Position ::" + ExceptionMessageConstants.PARAMETER_NEGATIVE);
			} else {
				isValid = true;
			}
		} else {
			throw new InvalidParamException(" Position ::" + ExceptionMessageConstants.PARAMETER_NULL);
		}

		return isValid;
	}

	@Override
	public boolean validateIngredientReferenceDTO(IngredientReferenceDTO dto) {

		boolean isValid = false;

		isValid = ValidationUtils.stringParamNotNullNotBlankNotEmpty(dto.getLang(), "Lang");
		isValid = ValidationUtils.stringParamNotNullNotBlankNotEmpty(dto.getName(), "Name");
		isValid = ValidationUtils.validateLang(dto.getLang());

		if (dto.getTypeId() != null) {
			isValid = true;
		} else {
			throw new InvalidParamException(" TypeId ::" + ExceptionMessageConstants.PARAMETER_NULL);
		}

		return isValid;
	}

}
