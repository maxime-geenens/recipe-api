package com.pluralsight.recipe.controllers;

import java.util.ArrayList;
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
import com.pluralsight.recipe.exceptions.InvalidParamException;
import com.pluralsight.recipe.services.ReferencesService;
import com.pluralsight.recipe.services.ValidationService;
import com.pluralsight.recipe.utils.ExceptionMessageConstants;
import com.pluralsight.recipe.utils.ValidationUtils;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "api/references")
@Slf4j
public class ReferenceController {

	@Autowired
	private ReferencesService referenceService;

	@Autowired
	private ValidationService validationService;

	@GetMapping(path = "/ingredients/type/{type}/lang/{lang}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<IngredientReferenceDTO>> fetchIngredientListByTypeAndLang(
			@PathVariable(name = "type", required = false) String type,
			@PathVariable(name = "lang", required = false) String lang) {

		if (log.isInfoEnabled()) {
			log.info(" GET API Call api/references/ingredients/type/{}/lang/{} ", type, lang);
		}

		List<IngredientReferenceDTO> list = new ArrayList<>();

		if (type != null && lang != null) {

			if (type.isEmpty() || type.isBlank()) {
				throw new InvalidParamException(
						ValidationUtils.buildExceptionMessage("type", ExceptionMessageConstants.PARAMETER_BLANK_EMPTY));
			}

			if (lang.isEmpty() || lang.isBlank()) {
				throw new InvalidParamException(
						ValidationUtils.buildExceptionMessage("lang", ExceptionMessageConstants.PARAMETER_BLANK_EMPTY));
			}

			list = referenceService.listIngredientsRefByTypeAndLang(type, lang);

		} else {
			throw new InvalidParamException(
					ValidationUtils.buildExceptionMessage("type or lang", ExceptionMessageConstants.PARAMETER_NULL));
		}

		if (log.isInfoEnabled()) {
			log.info(" Returning from api/references/ingredients/type/{}/lang/{} :: {}", type, lang, list.toString());
		}

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping(path = "/units/lang/{lang}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UnitReferenceDTO>> fetchUnitListByLang(
			@PathVariable(name = "lang", required = true) String lang) {

		if (log.isInfoEnabled()) {
			log.info(" GET API Call api/references/units/lang/{} ", lang);
		}

		List<UnitReferenceDTO> list = new ArrayList<>();

		if (lang != null) {

			if (lang.isEmpty() || lang.isBlank()) {
				throw new InvalidParamException(
						ValidationUtils.buildExceptionMessage("lang", ExceptionMessageConstants.PARAMETER_BLANK_EMPTY));
			}

			list = referenceService.listUnitsByLang(lang);

		} else {
			throw new InvalidParamException(
					ValidationUtils.buildExceptionMessage("lang", ExceptionMessageConstants.PARAMETER_NULL));
		}

		if (log.isInfoEnabled()) {
			log.info(" Returning from api/references/units/lang/{} :: {}", lang, list.toString());
		}

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@PostMapping(path = "/ingredient", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public ResponseEntity<IngredientReferenceDTO> addIngredientReference(
			@RequestBody(required = true) IngredientReferenceDTO requestDTO) {

		if (log.isInfoEnabled()) {
			log.info(" POST API Call api/references/ingredient :: {} ", requestDTO);
		}

		if (requestDTO != null) {
			validationService.validateIngredientReferenceDTO(requestDTO, true);
		} else {
			throw new InvalidParamException(ValidationUtils.buildExceptionMessage(
					ExceptionMessageConstants.INGREDIENT_REF_DTO, ExceptionMessageConstants.PARAMETER_NULL));
		}

		IngredientReferenceDTO response = referenceService.addIngredientRef(requestDTO);

		if (log.isInfoEnabled()) {
			log.info(" Returning from api/references/ingredient :: {} ", response);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
