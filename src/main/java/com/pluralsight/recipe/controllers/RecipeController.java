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

import com.pluralsight.recipe.dto.RecipeRequestDTO;
import com.pluralsight.recipe.exceptions.EntityNotFoundException;
import com.pluralsight.recipe.dto.RecipeDTO;
import com.pluralsight.recipe.services.RecipeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "api/recipes")
@Slf4j
public class RecipeController {

	@Autowired
	private RecipeService recipeService;

	@GetMapping(path = "/{lang}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RecipeDTO>> listRecipes(@PathVariable(required = false) String lang) {

		if (log.isInfoEnabled()) {
			log.info(" API Call api/recipe/{} ", lang);
		}

		List<RecipeDTO> list = recipeService.listRecipes(lang);

		if (log.isInfoEnabled()) {
			log.info(" Returning from api/recipe/{} :: {}", lang, list.toString());
		}

		return new ResponseEntity<List<RecipeDTO>>(list, HttpStatus.OK);
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RecipeDTO> getRecipe(@PathVariable(required = true) Long id) throws EntityNotFoundException {

		if (log.isInfoEnabled()) {
			log.info(" API Call api/recipe/{} ", id);
		}

		RecipeDTO dto = recipeService.getRecipeById(id);

		if (log.isInfoEnabled()) {
			log.info(" Returning from api/recipe/{} :: {}", id, dto.toString());
		}

		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public RecipeDTO createRecipe(@RequestBody(required = true) RecipeRequestDTO recipe) {

		return null;
	}

	@PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public RecipeDTO updateRecipe(@RequestBody(required = true) RecipeRequestDTO recipe,
			@PathVariable(required = true) Long id) {

		return null;
	}

	@DeleteMapping(path = "/{id}")
	public void deleteRecipe(@PathVariable(required = true) Long id) {

	}
}
