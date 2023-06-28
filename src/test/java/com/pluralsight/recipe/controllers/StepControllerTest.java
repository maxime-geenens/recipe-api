package com.pluralsight.recipe.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.pluralsight.recipe.dto.StepDTO;
import com.pluralsight.recipe.exceptions.InvalidParamException;
import com.pluralsight.recipe.services.StepService;
import com.pluralsight.recipe.services.VaildationDTOService;
import com.pluralsight.recipe.utils.ExceptionMessageConstants;
import com.pluralsight.recipe.utils.TestUtils;

@WebMvcTest(StepController.class)
public class StepControllerTest {

	private static final String BASE_API = "/api/steps";

	@MockBean
	StepService service;
	
	@MockBean
	VaildationDTOService dtoValidationService;

	@Autowired
	MockMvc mockMvc;

	@Test
	public void testCreateStepList() throws Exception {

		List<StepDTO> list = TestUtils.buildStepDTOList(5, false);
		StepDTO dto = list.get(0);

		when(dtoValidationService.validateStepDTO(dto)).thenReturn(true);
		when(service.createStepList(list)).thenReturn(list);

		mockMvc.perform(post(BASE_API + "/list/create").content(TestUtils.objectToJson(list))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(5)));

	}
	
	@Test
	public void createStepList_withOneStepNull_thenThrowsInvalidParamException() throws Exception {

		List<StepDTO> list = TestUtils.buildStepDTOList(5, false);
		StepDTO dto = list.get(0);
		list.add(null);

		when(dtoValidationService.validateStepDTO(dto)).thenReturn(true);
		
		mockMvc.perform(post(BASE_API + "/list/create").content(TestUtils.objectToJson(list))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is5xxServerError())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidParamException))
				.andExpect(result -> assertEquals(" StepDTO ::" + ExceptionMessageConstants.PARAMETER_NULL,
						result.getResolvedException().getMessage()));
		
	}

	@Test
	public void testAddStep() throws Exception {

		StepDTO dto = new StepDTO(1l, "FR", 1, "Description", 1l);

		when(dtoValidationService.validateStepDTO(dto)).thenReturn(true);
		when(service.addStep(dto)).thenReturn(dto);

		mockMvc.perform(post(BASE_API + "/add").content(TestUtils.objectToJson(dto))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void testUpdateStepList() throws Exception {

		List<StepDTO> list = new ArrayList<>();
		StepDTO dto = new StepDTO(1l, "FR", 1, "Description", 1l);
		list.add(dto);

		when(dtoValidationService.validateStepDTO(dto)).thenReturn(true);
		when(service.updateStepList(list)).thenReturn(list);

		mockMvc.perform(put(BASE_API + "/list/update").content(TestUtils.objectToJson(list))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(1)));

	}
	
	@Test
	public void updateStepList_withOneStepNull_thenThrowsInvalidParamException() throws Exception {

		List<StepDTO> list = TestUtils.buildStepDTOList(5, true);
		StepDTO dto = list.get(0);
		list.add(null);

		when(dtoValidationService.validateStepDTO(dto)).thenReturn(true);
		
		mockMvc.perform(put(BASE_API + "/list/update").content(TestUtils.objectToJson(list))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is5xxServerError())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidParamException))
				.andExpect(result -> assertEquals(" StepDTO ::" + ExceptionMessageConstants.PARAMETER_NULL,
						result.getResolvedException().getMessage()));
		
	}
	
	@Test
	public void updateStepList_withOneStepIdNull_thenThrowsInvalidParamException() throws Exception {

		List<StepDTO> list = TestUtils.buildStepDTOList(5, false);
		StepDTO dto = list.get(0);

		when(dtoValidationService.validateStepDTO(dto)).thenReturn(true);
		
		mockMvc.perform(put(BASE_API + "/list/update").content(TestUtils.objectToJson(list))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is5xxServerError())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidParamException))
				.andExpect(result -> assertEquals(" Id ::" + ExceptionMessageConstants.PARAMETER_NULL,
						result.getResolvedException().getMessage()));
		
	}
	
	@Test
	public void testUpdateStep() throws Exception {
		
		StepDTO dto = new StepDTO(1l, "FR", 1, "Description", 1l);

		when(dtoValidationService.validateStepDTO(dto)).thenReturn(true);
		when(service.updateStep(dto)).thenReturn(dto);

		mockMvc.perform(put(BASE_API + "/update").content(TestUtils.objectToJson(dto))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", Matchers.is(1)));
	}
	
	@Test
	public void testUpdateStep_withStepIdNull_thenThrowsInvalidParamException() throws Exception {
		
		StepDTO dto = TestUtils.buildStepDTO(false);

		when(dtoValidationService.validateStepDTO(dto)).thenReturn(true);
		when(service.updateStep(dto)).thenReturn(dto);

		mockMvc.perform(put(BASE_API + "/update").content(TestUtils.objectToJson(dto))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is5xxServerError())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidParamException))
				.andExpect(result -> assertEquals(" Id ::" + ExceptionMessageConstants.PARAMETER_NULL,
						result.getResolvedException().getMessage()));
	}
	
	@Test
	public void testDeleteStep() throws Exception {
		
		doNothing().when(service).deleteStep(1l);

		mockMvc.perform(delete(BASE_API + "/delete/1"))
				.andExpect(status().isOk());
	}

}
