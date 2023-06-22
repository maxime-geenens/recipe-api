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

import com.pluralsight.recipe.dto.StepDTO;
import com.pluralsight.recipe.services.StepService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "api/steps")
@Slf4j
public class StepController {

	@Autowired
	private StepService stepService;

	@PostMapping(path = "/list/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StepDTO>> createIngredientList(@RequestBody(required = true) List<StepDTO> requestDTO) {

		if (log.isInfoEnabled()) {
			log.info(" POST API Call api/steps/list/create :: {} ", requestDTO);
		}

		List<StepDTO> response = stepService.createStepList(requestDTO);

		if (log.isInfoEnabled()) {
			log.info(" Returning from api/steps/list/create :: {} ", response);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
