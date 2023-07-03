package com.pluralsight.recipe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pluralsight.recipe.dto.IngredientDTO;
import com.pluralsight.recipe.exceptions.InvalidParamException;
import com.pluralsight.recipe.services.IngredientService;
import com.pluralsight.recipe.services.ValidationDTOService;
import com.pluralsight.recipe.utils.ExceptionMessageConstants;
import com.pluralsight.recipe.utils.ValidationUtils;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "api/ingredients")
@Slf4j
public class IngredientController {

	@Autowired
	private IngredientService ingredientService;

	@Autowired
	private ValidationDTOService dtoValidationService;

	@PostMapping(path = "/list/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public ResponseEntity<List<IngredientDTO>> createIngredientList(
			@RequestBody(required = true) List<IngredientDTO> requestDTO) {

		if (log.isInfoEnabled()) {
			log.info(" POST API Call api/ingredients/list/create :: {} ", requestDTO);
		}

		for (IngredientDTO ingredientDTO : requestDTO) {

			if (ingredientDTO != null) {
				dtoValidationService.validateIngredientDTO(ingredientDTO);
			} else {
				throw new InvalidParamException(ValidationUtils.buildExceptionMessage(
						ExceptionMessageConstants.INGREDIENT_DTO, ExceptionMessageConstants.PARAMETER_NULL));
			}
		}

		List<IngredientDTO> response = ingredientService.saveIngredientList(requestDTO);

		if (log.isInfoEnabled()) {
			log.info(" Returning from api/ingredients/list/create :: {} ", response);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public ResponseEntity<IngredientDTO> addIngredient(@RequestBody(required = true) IngredientDTO requestDTO) {

		if (log.isInfoEnabled()) {
			log.info(" POST API Call api/ingredients/add :: {} ", requestDTO);
		}

		if (requestDTO != null) {
			dtoValidationService.validateIngredientDTO(requestDTO);
		} else {
			throw new InvalidParamException(ValidationUtils.buildExceptionMessage(
					ExceptionMessageConstants.INGREDIENT_DTO, ExceptionMessageConstants.PARAMETER_NULL));
		}

		IngredientDTO response = ingredientService.saveIngredient(requestDTO);

		if (log.isInfoEnabled()) {
			log.info(" Returning from api/ingredients/add :: {} ", response);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping(path = "/list/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public ResponseEntity<List<IngredientDTO>> updateIngredientList(
			@RequestBody(required = true) List<IngredientDTO> requestDTO) {

		if (log.isInfoEnabled()) {
			log.info(" PUT API Call api/ingredients/list/update :: {} ", requestDTO);
		}

		for (IngredientDTO ingredientDTO : requestDTO) {

			if (ingredientDTO != null) {
				if (ingredientDTO.getId() != null) {
					dtoValidationService.validateIngredientDTO(ingredientDTO);
				} else {
					throw new InvalidParamException(ValidationUtils.buildExceptionMessage(
							ExceptionMessageConstants.INGREDIENT_DTO + ExceptionMessageConstants.PARAM_ID,
							ExceptionMessageConstants.PARAMETER_NULL));
				}
			} else {
				throw new InvalidParamException(ValidationUtils.buildExceptionMessage(
						ExceptionMessageConstants.INGREDIENT_DTO, ExceptionMessageConstants.PARAMETER_NULL));
			}
		}

		List<IngredientDTO> response = ingredientService.saveIngredientList(requestDTO);

		if (log.isInfoEnabled()) {
			log.info(" Returning from api/ingredients/list/update :: {} ", response);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public ResponseEntity<IngredientDTO> updateIngredient(@RequestBody(required = true) IngredientDTO requestDTO) {

		if (log.isInfoEnabled()) {
			log.info(" PUT API Call api/ingredients/update :: {} ", requestDTO);
		}

		if (requestDTO != null) {
			if (requestDTO.getId() != null) {
				dtoValidationService.validateIngredientDTO(requestDTO);
			} else {
				throw new InvalidParamException(ValidationUtils.buildExceptionMessage(
						ExceptionMessageConstants.INGREDIENT_DTO + ExceptionMessageConstants.PARAM_ID,
						ExceptionMessageConstants.PARAMETER_NULL));
			}
		} else {
			throw new InvalidParamException(ValidationUtils.buildExceptionMessage(
					ExceptionMessageConstants.INGREDIENT_DTO, ExceptionMessageConstants.PARAMETER_NULL));
		}

		IngredientDTO response = ingredientService.saveIngredient(requestDTO);

		if (log.isInfoEnabled()) {
			log.info(" Returning from api/ingredients/update :: {} ", response);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping(path = "/delete/{id}")
	public void deleteIngredient(@PathVariable(name = "id", required = true) Long id) {

		if (log.isInfoEnabled()) {
			log.info(" DELETE API Call api/ingredients/delete/{} ", id);
		}

		if (id != null) {
			ingredientService.deleteIngredient(id);
		} else {
			throw new InvalidParamException("id :: " + ExceptionMessageConstants.PARAMETER_NULL);
		}

		if (log.isInfoEnabled()) {
			log.info(" Ingredient (id :: {}) has been deleted.", id);
		}
	}

}
