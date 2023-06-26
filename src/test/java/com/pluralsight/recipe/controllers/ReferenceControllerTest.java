package com.pluralsight.recipe.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import com.pluralsight.recipe.dto.IngredientReferenceDTO;
import com.pluralsight.recipe.dto.UnitReferenceDTO;
import com.pluralsight.recipe.services.ReferencesService;
import com.pluralsight.recipe.utils.TestUtils;

@WebMvcTest(ReferenceController.class)
public class ReferenceControllerTest {

	private static final String BASE_API = "/api/references";

	@MockBean
	ReferencesService service;

	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void testFetchIngredientListByTypeAndLang() throws Exception {
		
		List<IngredientReferenceDTO> list = new ArrayList<>();
		IngredientReferenceDTO dto = new IngredientReferenceDTO(1l, "FR", "Name", "Type", 1l);
		list.add(dto);
		
		when(service.listIngredientsByTypeAndLang("Type", "FR")).thenReturn(list);
		
		mockMvc.perform(get(BASE_API + "/ingredients/Type/FR"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", Matchers.hasSize(1)))
		.andExpect(jsonPath("$[0].id", Matchers.is(1)))
		.andExpect(jsonPath("$[0].lang", Matchers.is("FR")))
		.andExpect(jsonPath("$[0].name", Matchers.is("Name")))
		.andExpect(jsonPath("$[0].type", Matchers.is("Type")))
		.andExpect(jsonPath("$[0].typeId", Matchers.is(1)));
		
	}
	
	@Test
	public void testFetchUnitListByLang() throws Exception {
		
		List<UnitReferenceDTO> list = new ArrayList<>();
		UnitReferenceDTO dto = new UnitReferenceDTO(1l,"FR","Name","Symbol","Description");
		list.add(dto);
		
		when(service.listUnitsByLang("FR")).thenReturn(list);
		
		mockMvc.perform(get(BASE_API + "/units/FR"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", Matchers.hasSize(1)))
		.andExpect(jsonPath("$[0].id", Matchers.is(1)))
		.andExpect(jsonPath("$[0].lang", Matchers.is("FR")))
		.andExpect(jsonPath("$[0].name", Matchers.is("Name")))
		.andExpect(jsonPath("$[0].symbol", Matchers.is("Symbol")))
		.andExpect(jsonPath("$[0].description", Matchers.is("Description")));
		
	}

	@Test
	public void testAddIngredientRef() throws Exception {

		IngredientReferenceDTO dto = new IngredientReferenceDTO(1l, "FR", "Name", "Type", 1l);

		when(service.addIngredientRef(dto)).thenReturn(dto);

		mockMvc.perform(
				post(BASE_API + "/ingredient").content(TestUtils.objectToJson(dto)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", Matchers.is(1)))
				.andExpect(jsonPath("$.lang", Matchers.is("FR")))
				.andExpect(jsonPath("$.name", Matchers.is("Name")))
				.andExpect(jsonPath("$.type", Matchers.is("Type")))
				.andExpect(jsonPath("$.typeId", Matchers.is(1)));

	}

}
