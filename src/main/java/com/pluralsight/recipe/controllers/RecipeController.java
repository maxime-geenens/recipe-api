package com.pluralsight.recipe.controllers;

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
import com.pluralsight.recipe.entities.Recipe;
import com.pluralsight.recipe.entities.RecipeType;
import com.pluralsight.recipe.services.IngredientService;
import com.pluralsight.recipe.services.RecipeService;
import com.pluralsight.recipe.services.ReferencesService;
import com.pluralsight.recipe.services.StepService;
import com.pluralsight.recipe.services.VaildationDTOService;
import com.pluralsight.recipe.utils.ExceptionMessageConstants;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "api/recipes")
@Slf4j
public class RecipeController {

	@Autowired
	private RecipeService recipeService;

	@Autowired
	private ReferencesService referenceService;

	@Autowired
	private IngredientService ingredientService;

	@Autowired
	private StepService stepService;

	@Autowired
	private VaildationDTOService dtoValidationService;

	@GetMapping(path = "/lang/{lang}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RecipeDTO>> listRecipesByLang(
			@PathVariable(name = "lang", required = false) String lang) {

		if (log.isInfoEnabled()) {
			log.info(" GET API Call api/recipes/{} ", lang);
		}

		List<RecipeDTO> list = recipeService.listRecipesByLang(lang);

		if (log.isInfoEnabled()) {
			log.info(" Returning from api/recipes/{} :: {}", lang, list.toString());
		}

		return new ResponseEntity<List<RecipeDTO>>(list, HttpStatus.OK);
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RecipeDetailDTO> getRecipeDetail(@PathVariable(name = "id", required = true) Long id)
			throws EntityWasNotFoundException, InvalidParamException {

		if (log.isInfoEnabled()) {
			log.info(" GET API Call api/recipes/{} ", id);
		}

		RecipeDetailDTO response = new RecipeDetailDTO();
		RecipeDTO dto = new RecipeDTO();

		if (id != null) {
			dto = recipeService.getRecipeById(id);
			response.setRecipe(dto);
		} else {
			throw new InvalidParamException(ExceptionMessageConstants.PARAMETER_NULL);
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
			log.info(" Returning from api/recipes/{} :: {}", id, response.toString());
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RecipeDTO> createRecipe(@RequestBody(required = true) RecipeDTO requestDTO) {

		if (log.isInfoEnabled()) {
			log.info(" POST API Call api/recipes/create :: {} ", requestDTO);
		}

		dtoValidationService.validateRecipeDTO(requestDTO);

		RecipeType recipeType = referenceService.getRecipeTypeByCode(requestDTO.getTypeCode());

		Recipe recipe = recipeService.buildRecipe(requestDTO, recipeType);

		RecipeDTO response = recipeService.saveRecipe(recipe);

		if (log.isInfoEnabled()) {
			log.info(" Returning from api/recipes/create :: {} ", response);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping(path = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RecipeDTO> updateRecipe(@RequestBody(required = true) RecipeDTO requestDTO,
			@PathVariable(name = "id", required = true) Long id) {

		if (log.isInfoEnabled()) {
			log.info(" PUT API Call api/recipes/{} :: {} ", id, requestDTO);
		}

		RecipeDTO response = new RecipeDTO();

		if (id != null) {

			dtoValidationService.validateRecipeDTO(requestDTO);

			RecipeType recipeType = new RecipeType();

			String typeCode = requestDTO.getTypeCode();
			if (typeCode != null && !typeCode.isEmpty() && !typeCode.isBlank()) {
				recipeType = referenceService.getRecipeTypeByCode(requestDTO.getTypeCode());
			}

			response = recipeService.updateRecipe(requestDTO, recipeType);

		} else {
			throw new InvalidParamException(" Id ::" + ExceptionMessageConstants.PARAMETER_NULL);
		}

		if (log.isInfoEnabled()) {
			log.info(" Returning from api/recipes/{} :: {} ", id, response);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping(path = "/delete/{id}")
	public void deleteRecipe(@PathVariable(name = "id", required = true) Long id) {

		if (log.isInfoEnabled()) {
			log.info(" DELETE API Call api/recipes/{} ", id);
		}

		if (id != null) {
			recipeService.deleteRecipe(id);
		} else {
			throw new InvalidParamException(" Id ::" + ExceptionMessageConstants.PARAMETER_NULL);
		}

		if (log.isInfoEnabled()) {
			log.info(" Recipe (id :: {}) has been deleted.", id);
		}

	}
}
