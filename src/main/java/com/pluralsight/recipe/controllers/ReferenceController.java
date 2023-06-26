package com.pluralsight.recipe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pluralsight.recipe.dto.IngredientReferenceDTO;
import com.pluralsight.recipe.dto.UnitReferenceDTO;
import com.pluralsight.recipe.services.ReferencesService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "api/references")
@Slf4j
public class ReferenceController {

	@Autowired
	private ReferencesService referenceService;

	@GetMapping(path = "/ingredients/{type}/{lang}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<IngredientReferenceDTO>> fetchIngredientListByTypeAndLang(
			@PathVariable(name = "type", required = false) String type,
			@PathVariable(name = "lang", required = false) String lang) {

		if (log.isInfoEnabled()) {
			log.info(" GET API Call api/references/ingredients/{}/{} ", type, lang);
		}

		List<IngredientReferenceDTO> list = referenceService.listIngredientsByTypeAndLang(type, lang);

		if (log.isInfoEnabled()) {
			log.info(" Returning from api/references/ingredients/{}/{} :: {}", type, lang, list.toString());
		}

		return new ResponseEntity<List<IngredientReferenceDTO>>(list, HttpStatus.OK);
	}

	@GetMapping(path = "/units/{lang}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UnitReferenceDTO>> fetchUnitListByLang(
			@PathVariable(name = "lang", required = false) String lang) {

		if (log.isInfoEnabled()) {
			log.info(" GET API Call api/references/units/{} ", lang);
		}

		List<UnitReferenceDTO> list = referenceService.listUnitsByLang(lang);

		if (log.isInfoEnabled()) {
			log.info(" Returning from api/references/units/{} :: {}", lang, list.toString());
		}

		return new ResponseEntity<List<UnitReferenceDTO>>(list, HttpStatus.OK);
	}

	@PostMapping(path = "/ingredient", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<IngredientReferenceDTO> addIngredientReference(
			@RequestBody(required = true) IngredientReferenceDTO requestDTO) {

		if (log.isInfoEnabled()) {
			log.info(" POST API Call api/references/ingredient :: {} ", requestDTO);
		}

		IngredientReferenceDTO response = referenceService.addIngredientRef(requestDTO);

		if (log.isInfoEnabled()) {
			log.info(" Returning from api/references/ingredient :: {} ", response);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
