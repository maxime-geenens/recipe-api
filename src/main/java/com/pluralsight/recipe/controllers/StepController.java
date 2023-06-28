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

import com.pluralsight.recipe.dto.StepDTO;
import com.pluralsight.recipe.exceptions.InvalidParamException;
import com.pluralsight.recipe.services.StepService;
import com.pluralsight.recipe.services.ValidationDTOService;
import com.pluralsight.recipe.utils.ExceptionMessageConstants;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "api/steps")
@Slf4j
public class StepController {

	@Autowired
	private StepService stepService;

	@Autowired
	private ValidationDTOService dtoValidationService;

	@PostMapping(path = "/list/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StepDTO>> createStepList(@RequestBody(required = true) List<StepDTO> requestDTO) {

		if (log.isInfoEnabled()) {
			log.info(" POST API Call api/steps/list/create :: {} ", requestDTO);
		}

		for (StepDTO stepDTO : requestDTO) {
			if (stepDTO != null) {
				dtoValidationService.validateStepDTO(stepDTO);
			} else {
				throw new InvalidParamException(" StepDTO ::" + ExceptionMessageConstants.PARAMETER_NULL);
			}
		}

		List<StepDTO> response = stepService.createStepList(requestDTO);

		if (log.isInfoEnabled()) {
			log.info(" Returning from api/steps/list/create :: {} ", response);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StepDTO> addStep(@RequestBody(required = true) StepDTO requestDTO) {

		if (log.isInfoEnabled()) {
			log.info(" POST API Call api/steps/add :: {} ", requestDTO);
		}

		if (requestDTO != null) {
			dtoValidationService.validateStepDTO(requestDTO);
		} else {
			throw new InvalidParamException(" StepDTO ::" + ExceptionMessageConstants.PARAMETER_NULL);
		}

		StepDTO response = stepService.addStep(requestDTO);

		if (log.isInfoEnabled()) {
			log.info(" Returning from api/steps/add :: {} ", response);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping(path = "/list/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StepDTO>> updateStepList(@RequestBody(required = true) List<StepDTO> requestDTO) {

		if (log.isInfoEnabled()) {
			log.info(" PUT API Call api/steps/list/update :: {} ", requestDTO);
		}

		for (StepDTO stepDTO : requestDTO) {
			if (stepDTO != null) {
				if (stepDTO.getId() != null) {
					dtoValidationService.validateStepDTO(stepDTO);
				} else {
					throw new InvalidParamException(" Id ::" + ExceptionMessageConstants.PARAMETER_NULL);
				}
			} else {
				throw new InvalidParamException(" StepDTO ::" + ExceptionMessageConstants.PARAMETER_NULL);
			}
		}

		List<StepDTO> response = stepService.updateStepList(requestDTO);

		if (log.isInfoEnabled()) {
			log.info(" Returning from api/steps/list/update :: {} ", response);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StepDTO> updateStep(@RequestBody(required = true) StepDTO requestDTO) {

		if (log.isInfoEnabled()) {
			log.info(" PUT API Call api/steps/update :: {} ", requestDTO);
		}

		if (requestDTO != null) {
			if (requestDTO.getId() != null) {
				dtoValidationService.validateStepDTO(requestDTO);
			} else {
				throw new InvalidParamException(" Id ::" + ExceptionMessageConstants.PARAMETER_NULL);
			}
			
		} else {
			throw new InvalidParamException(" StepDTO ::" + ExceptionMessageConstants.PARAMETER_NULL);
		}

		StepDTO response = stepService.updateStep(requestDTO);

		if (log.isInfoEnabled()) {
			log.info(" Returning from api/steps/update :: {} ", response);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping(path = "/delete/{id}")
	public void deleteStep(@PathVariable(name = "id", required = true) Long id) {

		if (log.isInfoEnabled()) {
			log.info(" DELETE API Call api/steps/{} ", id);
		}

		if (id != null) {
			stepService.deleteStep(id);
		} else {
			throw new InvalidParamException(" Id ::" + ExceptionMessageConstants.PARAMETER_NULL);
		}

		if (log.isInfoEnabled()) {
			log.info(" Step (id :: {}) has been deleted.", id);
		}
	}

}
