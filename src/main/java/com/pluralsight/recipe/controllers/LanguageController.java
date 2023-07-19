package com.pluralsight.recipe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pluralsight.recipe.dto.LabelTranslationDTO;
import com.pluralsight.recipe.services.LabelTranslationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "api/languages")
@Slf4j
public class LanguageController {

	@Autowired
	private LabelTranslationService labelTranslationService;

	@GetMapping(path = "/label/list/{lang}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LabelTranslationDTO>> fetchLabelListByLang(
			@PathVariable(name = "lang", required = false) String lang) {

		if (log.isInfoEnabled()) {
			log.info(" GET API Call api/languages/label/list");
		}

		List<LabelTranslationDTO> list = labelTranslationService.listLabelsByLang(lang);

		if (log.isInfoEnabled()) {
			log.info(" Returning from api/languages/label/list", list.toString());
		}

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
