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

import com.pluralsight.recipe.dto.ToolDTO;
import com.pluralsight.recipe.exceptions.InvalidParamException;
import com.pluralsight.recipe.services.ToolService;
import com.pluralsight.recipe.services.ValidationService;
import com.pluralsight.recipe.utils.ExceptionMessageConstants;
import com.pluralsight.recipe.utils.ValidationUtils;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "api/tools")
@Slf4j
public class ToolController {

	@Autowired
	private ToolService toolService;

	@Autowired
	private ValidationService validationService;

	@PostMapping(path = "/list/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public ResponseEntity<List<ToolDTO>> createToolList(
			@RequestBody(required = true) List<ToolDTO> requestDTO) {

		if (log.isInfoEnabled()) {
			log.info(" POST API Call api/tools/list/create :: {} ", requestDTO);
		}

		for (ToolDTO toolDTO : requestDTO) {

			if (toolDTO != null) {
				validationService.validateToolDTO(toolDTO);
			} else {
				throw new InvalidParamException(ValidationUtils.buildExceptionMessage(
						ExceptionMessageConstants.TOOL_DTO, ExceptionMessageConstants.PARAMETER_NULL));
			}
		}

		List<ToolDTO> response = toolService.saveToolList(requestDTO);

		if (log.isInfoEnabled()) {
			log.info(" Returning from api/tools/list/create :: {} ", response);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public ResponseEntity<ToolDTO> addTool(@RequestBody(required = true) ToolDTO requestDTO) {

		if (log.isInfoEnabled()) {
			log.info(" POST API Call api/tools/add :: {} ", requestDTO);
		}

		if (requestDTO != null) {
			validationService.validateToolDTO(requestDTO);
		} else {
			throw new InvalidParamException(ValidationUtils.buildExceptionMessage(
					ExceptionMessageConstants.TOOL_DTO, ExceptionMessageConstants.PARAMETER_NULL));
		}

		ToolDTO response = toolService.saveTool(requestDTO);

		if (log.isInfoEnabled()) {
			log.info(" Returning from api/tools/add :: {} ", response);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping(path = "/list/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public ResponseEntity<List<ToolDTO>> updateToolList(
			@RequestBody(required = true) List<ToolDTO> requestDTO) {

		if (log.isInfoEnabled()) {
			log.info(" PUT API Call api/tools/list/update :: {} ", requestDTO);
		}

		for (ToolDTO toolDTO : requestDTO) {

			if (toolDTO != null) {
				if (toolDTO.getId() != null) {
					validationService.validateToolDTO(toolDTO);
				} else {
					throw new InvalidParamException(ValidationUtils.buildExceptionMessage(
							ExceptionMessageConstants.TOOL_DTO + ExceptionMessageConstants.PARAM_ID,
							ExceptionMessageConstants.PARAMETER_NULL));
				}
			} else {
				throw new InvalidParamException(ValidationUtils.buildExceptionMessage(
						ExceptionMessageConstants.TOOL_DTO, ExceptionMessageConstants.PARAMETER_NULL));
			}
		}

		List<ToolDTO> response = toolService.saveToolList(requestDTO);

		if (log.isInfoEnabled()) {
			log.info(" Returning from api/tools/list/update :: {} ", response);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public ResponseEntity<ToolDTO> updateTool(@RequestBody(required = true) ToolDTO requestDTO) {

		if (log.isInfoEnabled()) {
			log.info(" PUT API Call api/tools/update :: {} ", requestDTO);
		}

		if (requestDTO != null) {
			if (requestDTO.getId() != null) {
				validationService.validateToolDTO(requestDTO);
			} else {
				throw new InvalidParamException(ValidationUtils.buildExceptionMessage(
						ExceptionMessageConstants.TOOL_DTO + ExceptionMessageConstants.PARAM_ID,
						ExceptionMessageConstants.PARAMETER_NULL));
			}
		} else {
			throw new InvalidParamException(ValidationUtils.buildExceptionMessage(
					ExceptionMessageConstants.TOOL_DTO, ExceptionMessageConstants.PARAMETER_NULL));
		}

		ToolDTO response = toolService.saveTool(requestDTO);

		if (log.isInfoEnabled()) {
			log.info(" Returning from api/tools/update :: {} ", response);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping(path = "/delete/{id}")
	public void deleteTool(@PathVariable(name = "id", required = true) Long id) {

		if (log.isInfoEnabled()) {
			log.info(" DELETE API Call api/tools/delete/{} ", id);
		}

		if (id != null) {
			toolService.deleteTool(id);
		} else {
			throw new InvalidParamException("id :: " + ExceptionMessageConstants.PARAMETER_NULL);
		}

		if (log.isInfoEnabled()) {
			log.info(" Tool (id :: {}) has been deleted.", id);
		}
	}

}
