package com.pluralsight.recipe.controllers;

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

import com.pluralsight.recipe.dto.IngredientDTO;
import com.pluralsight.recipe.services.IngredientService;
import com.pluralsight.recipe.utils.TestUtils;

@WebMvcTest(IngredientController.class)
public class IngredientControllerTest {

	private static final String BASE_API = "/api/ingredients";

	@MockBean
	IngredientService service;

	@Autowired
	MockMvc mockMvc;

	@Test
	public void testCreateIngredientList() throws Exception {

		List<IngredientDTO> list = new ArrayList<>();
		IngredientDTO dto = new IngredientDTO(1l, "FR", "Name", "Type", 1.0, "Unit", 1l, 1l, 1l);
		list.add(dto);

		when(service.createIngredientList(list)).thenReturn(list);

		mockMvc.perform(post(BASE_API + "/list/create").content(TestUtils.objectToJson(list))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(1)));

	}

	@Test
	public void testAddIngredient() throws Exception {

		IngredientDTO dto = new IngredientDTO(1l, "FR", "Name", "Type", 1.0, "Unit", 1l, 1l, 1l);

		when(service.addIngredient(dto)).thenReturn(dto);

		mockMvc.perform(post(BASE_API + "/add").content(TestUtils.objectToJson(dto))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void testUpdateIngredientList() throws Exception {

		List<IngredientDTO> list = new ArrayList<>();
		IngredientDTO dto = new IngredientDTO(1l, "FR", "Name", "Type", 1.0, "Unit", 1l, 1l, 1l);
		list.add(dto);

		when(service.updateIngredientList(list)).thenReturn(list);

		mockMvc.perform(put(BASE_API + "/list/update").content(TestUtils.objectToJson(list))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(1)));

	}
	
	@Test
	public void testDeleteIngredient() throws Exception {
		
		doNothing().when(service).deleteIngredient(1l);

		mockMvc.perform(delete(BASE_API + "/delete/1"))
				.andExpect(status().isOk());
	}

}
