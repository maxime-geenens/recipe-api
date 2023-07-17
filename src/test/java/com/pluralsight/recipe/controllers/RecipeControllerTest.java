package com.pluralsight.recipe.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import com.pluralsight.recipe.dto.IngredientDTO;
import com.pluralsight.recipe.dto.RecipeDTO;
import com.pluralsight.recipe.dto.StepDTO;
import com.pluralsight.recipe.dto.ToolDTO;
import com.pluralsight.recipe.exceptions.InvalidParamException;
import com.pluralsight.recipe.services.IngredientService;
import com.pluralsight.recipe.services.RecipeService;
import com.pluralsight.recipe.services.ReferencesService;
import com.pluralsight.recipe.services.StepService;
import com.pluralsight.recipe.services.ToolService;
import com.pluralsight.recipe.services.ValidationService;
import com.pluralsight.recipe.utils.ExceptionMessageConstants;
import com.pluralsight.recipe.utils.TestUtils;

@WebMvcTest(RecipeController.class)
class RecipeControllerTest {

	private static final String BASE_API = "/api/recipes";

	@MockBean
	RecipeService recipeService;

	@MockBean
	ReferencesService referenceService;

	@MockBean
	IngredientService ingredientService;

	@MockBean
	StepService stepService;

	@MockBean
	ValidationService dtoValidationService;

	@MockBean
	ToolService toolService;

	@Autowired
	MockMvc mockMvc;

	@Test
	void testListRecipesByLang() throws Exception {

		List<RecipeDTO> list = TestUtils.buildRecipeDTOList(5, true);

		when(recipeService.listRecipesByLang("FR")).thenReturn(list);

		mockMvc.perform(get(BASE_API + "/lang/FR")).andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(5)));
	}

	@Test
	void testListRecipesByLang_withLangBlank_thenThrowInvalidParamException() throws Exception {

		mockMvc.perform(get(BASE_API + "/lang/  ")).andExpect(status().is5xxServerError())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidParamException))
				.andExpect(result -> assertEquals("lang :: " + ExceptionMessageConstants.PARAMETER_BLANK_EMPTY,
						result.getResolvedException().getMessage()));
	}

	@Test
	void testGetRecipeById() throws Exception {

		RecipeDTO dto = TestUtils.buildRecipeDTO(true);

		when(recipeService.getRecipeDTOById(1l)).thenReturn(dto);

		mockMvc.perform(get(BASE_API + "/id/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", Matchers.is(1)));
	}

	@Test
	void testGetRecipeDetail() throws Exception {

		RecipeDTO rDTO = TestUtils.buildRecipeDTO(true);
		List<IngredientDTO> iList = TestUtils.buildIngredientDTOList(5, true);
		List<StepDTO> sList = TestUtils.buildStepDTOList(5, true);
		List<ToolDTO> tList = TestUtils.buildToolDTOList(5, true);

		when(recipeService.getRecipeDTOById(1l)).thenReturn(rDTO);
		when(ingredientService.listIngredientsByRecipe(1l)).thenReturn(iList);
		when(stepService.listStepsByRecipe(1l)).thenReturn(sList);
		when(toolService.listToolsByRecipe(1l)).thenReturn(tList);

		mockMvc.perform(get(BASE_API + "/detail/1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.recipe.id", Matchers.is(1)))
				.andExpect(jsonPath("$.recipe.lang", Matchers.is("FR")))
				.andExpect(jsonPath("$.recipe.name", Matchers.is("Name1")))
				.andExpect(jsonPath("$.recipe.description", Matchers.is("Description1")))
				.andExpect(jsonPath("$.recipe.type.id", Matchers.is(1)))
				.andExpect(jsonPath("$.ingredientList", Matchers.hasSize(5)))
				.andExpect(jsonPath("$.stepList", Matchers.hasSize(5)));
	}

	@Test
	void testCreateRecipe() throws Exception {

		RecipeDTO dto = TestUtils.buildRecipeDTO(true);

		when(dtoValidationService.validateRecipeDTO(dto)).thenReturn(true);
		when(recipeService.saveRecipe(dto)).thenReturn(dto);

		mockMvc.perform(
				post(BASE_API + "/create").content(TestUtils.objectToJson(dto)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id", Matchers.is(1)))
				.andExpect(jsonPath("$.lang", Matchers.is("FR"))).andExpect(jsonPath("$.name", Matchers.is("Name1")))
				.andExpect(jsonPath("$.description", Matchers.is("Description1")))
				.andExpect(jsonPath("$.type.id", Matchers.is(1)));
	}

	@Test
	void testUpdateRecipe() throws Exception {

		RecipeDTO dto = TestUtils.buildRecipeDTO(true);
		RecipeDTO dto1 = TestUtils.buildRecipeDTO(true);
		dto1.setDescription("Updated");

		when(dtoValidationService.validateRecipeDTO(dto)).thenReturn(true);
		when(recipeService.saveRecipe(dto)).thenReturn(dto1);

		mockMvc.perform(
				put(BASE_API + "/update").content(TestUtils.objectToJson(dto)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id", Matchers.is(1)))
				.andExpect(jsonPath("$.lang", Matchers.is("FR"))).andExpect(jsonPath("$.name", Matchers.is("Name1")))
				.andExpect(jsonPath("$.description", Matchers.is("Updated")))
				.andExpect(jsonPath("$.type.id", Matchers.is(1)));
	}

	@Test
	void testUpdateRecipe_withIdNull_thenThrowInvalidParamException() throws Exception {

		RecipeDTO dto = TestUtils.buildRecipeDTO(false);

		when(dtoValidationService.validateRecipeDTO(dto)).thenReturn(true);
		when(recipeService.saveRecipe(dto)).thenReturn(dto);

		mockMvc.perform(
				put(BASE_API + "/update").content(TestUtils.objectToJson(dto)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is5xxServerError())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidParamException))
				.andExpect(result -> assertEquals("RecipeDTO.id :: " + ExceptionMessageConstants.PARAMETER_NULL,
						result.getResolvedException().getMessage()));
	}

	@Test
	void testDeleteRecipe() throws Exception {

		doNothing().when(recipeService).deleteRecipe(1l);

		mockMvc.perform(delete(BASE_API + "/delete/1")).andExpect(status().isOk());
	}

}
