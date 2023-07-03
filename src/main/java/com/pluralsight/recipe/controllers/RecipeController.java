package com.pluralsight.recipe.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pluralsight.recipe.exceptions.EntityWasNotFoundException;
import com.pluralsight.recipe.exceptions.InvalidParamException;
import com.pluralsight.recipe.dto.IngredientDTO;
import com.pluralsight.recipe.dto.RecipeDTO;
import com.pluralsight.recipe.dto.RecipeDetailDTO;
import com.pluralsight.recipe.dto.StepDTO;
import com.pluralsight.recipe.services.IngredientService;
import com.pluralsight.recipe.services.RecipeService;
import com.pluralsight.recipe.services.StepService;
import com.pluralsight.recipe.services.ValidationService;
import com.pluralsight.recipe.utils.ExceptionMessageConstants;
import com.pluralsight.recipe.utils.ValidationUtils;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "api/recipes")
@Slf4j
public class RecipeController {

	@Autowired
	private RecipeService recipeService;

	@Autowired
	private IngredientService ingredientService;

	@Autowired
	private StepService stepService;

	@Autowired
	private ValidationService validationService;

	@GetMapping(path = "/lang/{lang}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RecipeDTO>> listRecipesByLang(
			@PathVariable(name = "lang", required = true) String lang) {

		if (log.isInfoEnabled()) {
			log.info(" GET API Call api/recipes/{} ", lang);
		}

		List<RecipeDTO> list = new ArrayList<>();

		if (lang != null) {

			if (lang.isEmpty() || lang.isBlank()) {
				throw new InvalidParamException("lang :: " + ExceptionMessageConstants.PARAMETER_BLANK_EMPTY);
			}

			list = recipeService.listRecipesByLang(lang);

		} else {
			throw new InvalidParamException("lang :: " + ExceptionMessageConstants.PARAMETER_NULL);
		}

		if (log.isInfoEnabled()) {
			log.info(" Returning from api/recipes/{} :: {}", lang, list.toString());
		}

		return new ResponseEntity<List<RecipeDTO>>(list, HttpStatus.OK);
	}

	@GetMapping(path = "/detail/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RecipeDetailDTO> getRecipeDetail(@PathVariable(name = "id", required = true) Long id)
			throws EntityWasNotFoundException, InvalidParamException {

		if (log.isInfoEnabled()) {
			log.info(" GET API Call api/recipes/detail/{} ", id);
		}

		RecipeDetailDTO response = new RecipeDetailDTO();
		RecipeDTO dto = new RecipeDTO();

		if (id != null) {
			dto = recipeService.getRecipeDTOById(id);
			response.setRecipe(dto);
		} else {
			throw new InvalidParamException(ValidationUtils.buildExceptionMessage(
					ExceptionMessageConstants.RECIPE_DTO + ExceptionMessageConstants.PARAM_ID,
					ExceptionMessageConstants.PARAMETER_NULL));
		}

		List<IngredientDTO> ingredientDTOList = ingredientService.listIngredientsByRecipe(id);

		if (!ingredientDTOList.isEmpty()) {
			response.setIngredientList(ingredientDTOList);
		} else {
			throw new EntityWasNotFoundException(ExceptionMessageConstants.INGREDIENT_LIST_NOT_FOUND);
		}

		List<StepDTO> stepDTOList = stepService.listStepsByRecipe(id);

		if (!stepDTOList.isEmpty()) {
			response.setStepList(stepDTOList);
		} else {
			throw new EntityWasNotFoundException(ExceptionMessageConstants.STEPT_LIST_NOT_FOUND);
		}

		if (log.isInfoEnabled()) {
			log.info(" Returning from api/recipes/detail/{} :: {}", id, response.toString());
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public ResponseEntity<RecipeDTO> createRecipe(@RequestBody(required = true) RecipeDTO requestDTO) {

		if (log.isInfoEnabled()) {
			log.info(" POST API Call api/recipes/create :: {} ", requestDTO);
		}

		if (requestDTO != null) {
			validationService.validateRecipeDTO(requestDTO, true);
		} else {
			throw new InvalidParamException(ValidationUtils.buildExceptionMessage(ExceptionMessageConstants.RECIPE_DTO,
					ExceptionMessageConstants.PARAMETER_NULL));
		}

		RecipeDTO response = recipeService.saveRecipe(requestDTO);

		if (log.isInfoEnabled()) {
			log.info(" Returning from api/recipes/create :: {} ", response);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public ResponseEntity<RecipeDTO> updateRecipe(@RequestBody(required = true) RecipeDTO requestDTO) {

		if (log.isInfoEnabled()) {
			log.info(" PUT API Call api/recipes/update :: {} ", requestDTO);
		}

		RecipeDTO response = new RecipeDTO();

		if (requestDTO != null) {
			Long id = requestDTO.getId();
			if (id != null) {
				validationService.validateRecipeDTO(requestDTO, false);
			} else {
				throw new InvalidParamException(ValidationUtils.buildExceptionMessage(
						ExceptionMessageConstants.RECIPE_DTO + ExceptionMessageConstants.PARAM_ID,
						ExceptionMessageConstants.PARAMETER_NULL));
			}
		} else {
			throw new InvalidParamException(ValidationUtils.buildExceptionMessage(ExceptionMessageConstants.RECIPE_DTO,
					ExceptionMessageConstants.PARAMETER_NULL));
		}

		response = recipeService.saveRecipe(requestDTO);

		if (log.isInfoEnabled()) {
			log.info(" Returning from api/recipes/update :: {} ", response);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping(path = "/delete/{id}")
	@Transactional
	public void deleteRecipe(@PathVariable(name = "id", required = true) Long id) {

		if (log.isInfoEnabled()) {
			log.info(" DELETE API Call api/recipes/{} ", id);
		}

		if (id != null) {
			recipeService.deleteRecipe(id);
		} else {
			throw new InvalidParamException(
					ValidationUtils.buildExceptionMessage("id", ExceptionMessageConstants.PARAMETER_NULL));
		}

		if (log.isInfoEnabled()) {
			log.info(" Recipe (id :: {}) has been deleted.", id);
		}

	}
}
