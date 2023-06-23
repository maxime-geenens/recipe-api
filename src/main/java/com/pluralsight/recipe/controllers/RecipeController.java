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

import com.pluralsight.recipe.exceptions.EntityNotFoundException;
import com.pluralsight.recipe.exceptions.InvalidParameterException;
import com.pluralsight.recipe.dto.RecipeDTO;
import com.pluralsight.recipe.dto.RecipeDetailDTO;
import com.pluralsight.recipe.services.RecipeService;
import com.pluralsight.recipe.utils.ExceptionMessageConstants;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "api/recipes")
@Slf4j
public class RecipeController {

	@Autowired
	private RecipeService recipeService;

	@GetMapping(path = "/{lang}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RecipeDTO>> listRecipes(@PathVariable(name="lang", required = false) String lang) {

		if (log.isInfoEnabled()) {
			log.info(" GET API Call api/recipes/{} ", lang);
		}

		List<RecipeDTO> list = recipeService.listRecipes(lang);

		if (log.isInfoEnabled()) {
			log.info(" Returning from api/recipes/{} :: {}", lang, list.toString());
		}

		return new ResponseEntity<List<RecipeDTO>>(list, HttpStatus.OK);
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RecipeDetailDTO> getRecipeDetail(@PathVariable(name="id", required = true) Long id)
			throws EntityNotFoundException, InvalidParameterException {

		if (log.isInfoEnabled()) {
			log.info(" GET API Call api/recipes/{} ", id);
		}

		RecipeDetailDTO dto = new RecipeDetailDTO();

		if (id != null) {
			dto = recipeService.getRecipeById(id);
		} else {
			throw new InvalidParameterException(ExceptionMessageConstants.PARAMETER_NULL);
		}

		if (log.isInfoEnabled()) {
			log.info(" Returning from api/recipes/{} :: {}", id, dto.toString());
		}

		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RecipeDTO> createRecipe(@RequestBody(required = true) RecipeDTO recipeDTO) {

		if (log.isInfoEnabled()) {
			log.info(" POST API Call api/recipes/create :: {} ", recipeDTO);
		}

		RecipeDTO recipe = recipeService.createRecipe(recipeDTO);

		if (log.isInfoEnabled()) {
			log.info(" Returning from api/recipes/create :: {} ", recipe);
		}

		return new ResponseEntity<>(recipe, HttpStatus.OK);
	}

	@PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RecipeDTO> updateRecipe(@RequestBody(required = true) RecipeDTO recipeDTO,
			@PathVariable(name="id", required = true) Long id) {

		if (log.isInfoEnabled()) {
			log.info(" PUT API Call api/recipes/{} :: {} ", id, recipeDTO);
		}

		RecipeDTO recipe = recipeService.updateRecipe(recipeDTO);

		if (log.isInfoEnabled()) {
			log.info(" Returning from api/recipes/{} :: {} ", id, recipe);
		}

		return new ResponseEntity<>(recipe, HttpStatus.OK);
	}

	@DeleteMapping(path = "/{id}")
	public void deleteRecipe(@PathVariable(name="id", required = true) Long id) {

		if (log.isInfoEnabled()) {
			log.info(" DELETE API Call api/recipes/{} ", id);
		}

		recipeService.deleteRecipe(id);

		if (log.isInfoEnabled()) {
			log.info(" Recipe (id :: {}) has been deleted.", id);
		}

	}
}
