package com.pluralsight.recipe.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import com.pluralsight.recipe.dto.IngredientTypeDTO;
import com.pluralsight.recipe.dto.RecipeTypeDTO;
import com.pluralsight.recipe.dto.ToolReferenceDTO;
import com.pluralsight.recipe.dto.ToolTypeDTO;
import com.pluralsight.recipe.dto.UnitReferenceDTO;
import com.pluralsight.recipe.exceptions.InvalidParamException;
import com.pluralsight.recipe.services.ReferencesService;
import com.pluralsight.recipe.services.ValidationService;
import com.pluralsight.recipe.utils.ExceptionMessageConstants;
import com.pluralsight.recipe.utils.TestUtils;

@WebMvcTest(ReferenceController.class)
class ReferenceControllerTest {

	private static final String BASE_API = "/api/references";

	@MockBean
	ReferencesService service;
	
	@MockBean
	ValidationService dtoValidationService;

	@Autowired
	MockMvc mockMvc;
	
	@Test
	void testFetchIngredientRefListByTypeAndLang() throws Exception {
		
		List<IngredientReferenceDTO> list = new ArrayList<>();
		IngredientReferenceDTO dto = TestUtils.buildIngredientReferenceDTO(true);
		list.add(dto);
		
		when(service.listIngredientsRefByTypeAndLang("Type", "FR")).thenReturn(list);
		
		mockMvc.perform(get(BASE_API + "/ingredients/type/Type/lang/FR"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].id", Matchers.is(1)))
				.andExpect(jsonPath("$[0].lang", Matchers.is("FR")))
				.andExpect(jsonPath("$[0].name", Matchers.is("Name")))
				.andExpect(jsonPath("$[0].type.id", Matchers.is(1)));

	}

	@Test
	void testFetchIngredientTypeListLang() throws Exception {

		List<IngredientTypeDTO> list = new ArrayList<>();
		IngredientTypeDTO dto = new IngredientTypeDTO(1l, "FR", "Name");
		list.add(dto);

		when(service.listIngredientTypesByLang("FR")).thenReturn(list);
		
		mockMvc.perform(get(BASE_API + "/ingredients/typeByLang/FR"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].id", Matchers.is(1)))
				.andExpect(jsonPath("$[0].lang", Matchers.is("FR")))
				.andExpect(jsonPath("$[0].name", Matchers.is("Name")));
		
	}
	
	@Test
	void testFetchIngredientListByTypeAndLang_withLangBlank_thenThrowsInvalidParamException() throws Exception {
		
		mockMvc.perform(get(BASE_API + "/ingredients/type/Type/lang/  "))
				.andExpect(status().is5xxServerError())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidParamException))
				.andExpect(result -> assertEquals("lang :: " + ExceptionMessageConstants.PARAMETER_BLANK_EMPTY,
						result.getResolvedException().getMessage()));
		
	}
	
	@Test
	void testFetchIngredientListByTypeAndLang_withTypeBlank_thenThrowsInvalidParamException() throws Exception {
		
		mockMvc.perform(get(BASE_API + "/ingredients/type/  /lang/FR"))
				.andExpect(status().is5xxServerError())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidParamException))
				.andExpect(result -> assertEquals("type :: " + ExceptionMessageConstants.PARAMETER_BLANK_EMPTY,
						result.getResolvedException().getMessage()));
		
	}
	
	@Test
	void testFetchUnitListByLang() throws Exception {
		
		List<UnitReferenceDTO> list = new ArrayList<>();
		UnitReferenceDTO dto = new UnitReferenceDTO(1l,"FR","Name","Symbol","Description");
		list.add(dto);
		
		when(service.listUnitsByLang("FR")).thenReturn(list);
		
		mockMvc.perform(get(BASE_API + "/units/lang/FR"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].id", Matchers.is(1)))
				.andExpect(jsonPath("$[0].lang", Matchers.is("FR")))
				.andExpect(jsonPath("$[0].name", Matchers.is("Name")))
				.andExpect(jsonPath("$[0].symbol", Matchers.is("Symbol")))
				.andExpect(jsonPath("$[0].description", Matchers.is("Description")));
		
	}
	
	@Test
	void testFetchUnitListByLang_withLangBlank_thenThrowsInvalidParamException() throws Exception {
		
		mockMvc.perform(get(BASE_API + "/units/lang/  "))
				.andExpect(status().is5xxServerError())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidParamException))
				.andExpect(result -> assertEquals("lang :: " + ExceptionMessageConstants.PARAMETER_BLANK_EMPTY,
						result.getResolvedException().getMessage()));
		
	}

	@Test
	void testAddIngredientRef() throws Exception {

		IngredientReferenceDTO dto = TestUtils.buildIngredientReferenceDTO(true);

		when(dtoValidationService.validateIngredientReferenceDTO(dto)).thenReturn(true);
		when(service.addIngredientRef(dto)).thenReturn(dto);

		mockMvc.perform(
				post(BASE_API + "/ingredient/add").content(TestUtils.objectToJson(dto)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", Matchers.is(1)))
				.andExpect(jsonPath("$.lang", Matchers.is("FR")))
				.andExpect(jsonPath("$.name", Matchers.is("Name")))
				.andExpect(jsonPath("$.type.id", Matchers.is(1)));

	}
	
	@Test
	void testFetchRecipeTypeListByLang() throws Exception {
		
		List<RecipeTypeDTO> list = new ArrayList<>();
		RecipeTypeDTO dto = new RecipeTypeDTO(1l,"FR","Name");
		list.add(dto);
		
		when(service.listRecipeTypesByLang("FR")).thenReturn(list);
		
		mockMvc.perform(get(BASE_API + "/recipes/typeByLang/FR"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].id", Matchers.is(1)))
				.andExpect(jsonPath("$[0].lang", Matchers.is("FR")))
				.andExpect(jsonPath("$[0].name", Matchers.is("Name")));
		
	}

	@Test
	void testAddToolRef() throws Exception {

		ToolReferenceDTO dto = TestUtils.buildToolReferenceDTO(true);

		when(dtoValidationService.validateToolReferenceDTO(dto)).thenReturn(true);
		when(service.addToolRef(dto)).thenReturn(dto);

		mockMvc.perform(
				post(BASE_API + "/tool/add").content(TestUtils.objectToJson(dto)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", Matchers.is(1)))
				.andExpect(jsonPath("$.lang", Matchers.is("FR")))
				.andExpect(jsonPath("$.name", Matchers.is("Name")))
				.andExpect(jsonPath("$.type.id", Matchers.is(1)));

	}
	
	@Test
	void testFetchToolRefListByTypeAndLang() throws Exception {
		
		List<ToolReferenceDTO> list = new ArrayList<>();
		ToolReferenceDTO dto = TestUtils.buildToolReferenceDTO(true);
		list.add(dto);
		
		when(service.listToolsRefByTypeAndLang("Type", "FR")).thenReturn(list);
		
		mockMvc.perform(get(BASE_API + "/tools/type/Type/lang/FR"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].id", Matchers.is(1)))
				.andExpect(jsonPath("$[0].lang", Matchers.is("FR")))
				.andExpect(jsonPath("$[0].name", Matchers.is("Name")))
				.andExpect(jsonPath("$[0].type.id", Matchers.is(1)));

	}

	@Test
	void testFetchToolTypeListLang() throws Exception {

		List<ToolTypeDTO> list = new ArrayList<>();
		ToolTypeDTO dto = new ToolTypeDTO(1l, "FR", "Name");
		list.add(dto);

		when(service.listToolTypesByLang("FR")).thenReturn(list);
		
		mockMvc.perform(get(BASE_API + "/tools/typeByLang/FR"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].id", Matchers.is(1)))
				.andExpect(jsonPath("$[0].lang", Matchers.is("FR")))
				.andExpect(jsonPath("$[0].name", Matchers.is("Name")));
		
	}

}
