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

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.pluralsight.recipe.dto.ToolDTO;
import com.pluralsight.recipe.exceptions.InvalidParamException;
import com.pluralsight.recipe.services.ToolService;
import com.pluralsight.recipe.services.ValidationService;
import com.pluralsight.recipe.utils.ExceptionMessageConstants;
import com.pluralsight.recipe.utils.TestUtils;

@WebMvcTest(ToolController.class)
class ToolControllerTest {

	private static final String BASE_API = "/api/tools";

	@MockBean
	ToolService service;

	@MockBean
	ValidationService dtoValidationService;

	@Autowired
	MockMvc mockMvc;

	@Test
	void testCreateToolList() throws Exception {

		List<ToolDTO> list = TestUtils.buildToolDTOList(5, true);
		ToolDTO dto = list.get(0);

		when(dtoValidationService.validateToolDTO(dto)).thenReturn(true);
		when(service.saveToolList(list)).thenReturn(list);

		mockMvc.perform(post(BASE_API + "/list/create").content(TestUtils.objectToJson(list))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(5)));

	}

	@Test
	void testCreateToolList_withOneToolNull_thenThrowsInvalidParamException() throws Exception {

		List<ToolDTO> list = TestUtils.buildToolDTOList(5, true);
		ToolDTO dto = list.get(0);
		list.add(null);

		when(dtoValidationService.validateToolDTO(dto)).thenReturn(true);

		mockMvc.perform(post(BASE_API + "/list/create").content(TestUtils.objectToJson(list))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().is5xxServerError())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidParamException))
				.andExpect(result -> assertEquals("ToolDTO :: " + ExceptionMessageConstants.PARAMETER_NULL,
						result.getResolvedException().getMessage()));

	}

	@Test
	void testAddTool() throws Exception {

		ToolDTO dto = TestUtils.buildToolDTO(true);

		when(dtoValidationService.validateToolDTO(dto)).thenReturn(true);
		when(service.saveTool(dto)).thenReturn(dto);

		mockMvc.perform(
				post(BASE_API + "/add").content(TestUtils.objectToJson(dto)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	void testUpdateToolList() throws Exception {

		List<ToolDTO> list = TestUtils.buildToolDTOList(5, true);
		ToolDTO dto = list.get(0);

		when(dtoValidationService.validateToolDTO(dto)).thenReturn(true);
		when(service.saveToolList(list)).thenReturn(list);

		mockMvc.perform(put(BASE_API + "/list/update").content(TestUtils.objectToJson(list))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(5)));

	}

	@Test
	void testUpdateToolList_withOneToolNull_thenThrowsInvalidParamException() throws Exception {

		List<ToolDTO> list = TestUtils.buildToolDTOList(5, true);
		ToolDTO dto = list.get(0);
		list.add(null);

		when(dtoValidationService.validateToolDTO(dto)).thenReturn(true);

		mockMvc.perform(put(BASE_API + "/list/update").content(TestUtils.objectToJson(list))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().is5xxServerError())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidParamException))
				.andExpect(result -> assertEquals("ToolDTO :: " + ExceptionMessageConstants.PARAMETER_NULL,
						result.getResolvedException().getMessage()));

	}

	@Test
	void testUpdateToolList_withOneToolIdNull_thenThrowsInvalidParamException() throws Exception {

		List<ToolDTO> list = TestUtils.buildToolDTOList(5, false);
		ToolDTO dto = list.get(0);

		when(dtoValidationService.validateToolDTO(dto)).thenReturn(true);

		mockMvc.perform(put(BASE_API + "/list/update").content(TestUtils.objectToJson(list))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().is5xxServerError())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidParamException))
				.andExpect(result -> assertEquals("ToolDTO.id :: " + ExceptionMessageConstants.PARAMETER_NULL,
						result.getResolvedException().getMessage()));

	}

	@Test
	void testUpdateTool() throws Exception {

		ToolDTO dto = TestUtils.buildToolDTO(true);

		when(dtoValidationService.validateToolDTO(dto)).thenReturn(true);
		when(service.saveTool(dto)).thenReturn(dto);

		mockMvc.perform(
				put(BASE_API + "/update").content(TestUtils.objectToJson(dto)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id", Matchers.is(1)));

	}

	@Test
	void testUpdateTool_withToolIdNull_thenThrowsInvalidParamException() throws Exception {

		ToolDTO dto = TestUtils.buildToolDTO(false);

		when(dtoValidationService.validateToolDTO(dto)).thenReturn(true);
		when(service.saveTool(dto)).thenReturn(dto);

		mockMvc.perform(
				put(BASE_API + "/update").content(TestUtils.objectToJson(dto)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is5xxServerError())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidParamException))
				.andExpect(result -> assertEquals("ToolDTO.id :: " + ExceptionMessageConstants.PARAMETER_NULL,
						result.getResolvedException().getMessage()));

	}

	@Test
	void testDeleteTool() throws Exception {

		doNothing().when(service).deleteTool(1l);

		mockMvc.perform(delete(BASE_API + "/delete/1")).andExpect(status().isOk());
	}

}
