package com.pluralsight.recipe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pluralsight.recipe.dto.IngredientDTO;
import com.pluralsight.recipe.services.IngredientService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "api/ingredients")
@Slf4j
public class IngredientController {

	@Autowired
	private IngredientService ingredientService;

	@PostMapping(path = "/list/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<IngredientDTO>> createIngredientList(
			@RequestBody(required = true) List<IngredientDTO> requestDTO) {

		if (log.isInfoEnabled()) {
			log.info(" POST API Call api/ingredients/list/create :: {} ", requestDTO);
		}

		List<IngredientDTO> response = ingredientService.createIngredientList(requestDTO);

		if (log.isInfoEnabled()) {
			log.info(" Returning from api/ingredients/list/create :: {} ", response);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
