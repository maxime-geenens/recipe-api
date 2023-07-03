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
import com.pluralsight.recipe.exceptions.InvalidParamException;
import com.pluralsight.recipe.services.IngredientService;
import com.pluralsight.recipe.services.RecipeService;
import com.pluralsight.recipe.services.ReferencesService;
import com.pluralsight.recipe.services.StepService;
import com.pluralsight.recipe.services.ValidationDTOService;
import com.pluralsight.recipe.utils.ExceptionMessageConstants;
import com.pluralsight.recipe.utils.TestUtils;

@WebMvcTest(RecipeController.class)
public class RecipeControllerTest {

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
	ValidationDTOService dtoValidationService;

	@Autowired
	MockMvc mockMvc;

	@Test
	public void testListRecipesByLang() throws Exception {

		List<RecipeDTO> list = TestUtils.buildRecipeDTOList(5, true, true);

		when(recipeService.listRecipesByLang("FR")).thenReturn(list);

		mockMvc.perform(get(BASE_API + "/lang/FR"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(5)));
	}

	@Test
	public void testListRecipesByLang_withLangBlank_thenThrowInvalidParamException() throws Exception {

		mockMvc.perform(get(BASE_API + "/lang/  "))
				.andExpect(status().is5xxServerError())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidParamException))
				.andExpect(result -> assertEquals("lang :: " + ExceptionMessageConstants.PARAMETER_BLANK_EMPTY,
						result.getResolvedException().getMessage()));
	}

	@Test
	public void testGetRecipeDetail() throws Exception {

		RecipeDTO rDTO = TestUtils.buildRecipeDTO(true, true);
		List<IngredientDTO> iList = TestUtils.buildIngredientDTOList(5, true);
		List<StepDTO> sList = TestUtils.buildStepDTOList(5, true);

		when(recipeService.getRecipeDTOById(1l)).thenReturn(rDTO);
		when(ingredientService.listIngredientsByRecipe(1l)).thenReturn(iList);
		when(stepService.listStepsByRecipe(1l)).thenReturn(sList);

		mockMvc.perform(get(BASE_API + "/detail/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.recipe.id", Matchers.is(1)))
				.andExpect(jsonPath("$.recipe.lang", Matchers.is("FR")))
				.andExpect(jsonPath("$.recipe.name", Matchers.is("Name")))
				.andExpect(jsonPath("$.recipe.description", Matchers.is("Description")))
				.andExpect(jsonPath("$.recipe.typeId", Matchers.is(1)))
				.andExpect(jsonPath("$.ingredientList", Matchers.hasSize(5)))
				.andExpect(jsonPath("$.stepList", Matchers.hasSize(5)));
	}

	@Test
	public void testCreateRecipe() throws Exception {

		RecipeDTO dto = TestUtils.buildRecipeDTO(true, true);
		
		when(dtoValidationService.validateRecipeDTO(dto)).thenReturn(true);
		when(recipeService.saveRecipe(dto)).thenReturn(dto);

		mockMvc.perform(
				post(BASE_API + "/create").content(TestUtils.objectToJson(dto)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", Matchers.is(1)))
				.andExpect(jsonPath("$.lang", Matchers.is("FR")))
				.andExpect(jsonPath("$.name", Matchers.is("Name")))
				.andExpect(jsonPath("$.description", Matchers.is("Description")))
				.andExpect(jsonPath("$.typeId", Matchers.is(1)));
	}

	@Test
	public void testUpdateRecipe() throws Exception {

		RecipeDTO dto = TestUtils.buildRecipeDTO(true, true);
		RecipeDTO dto1 = new RecipeDTO(1l, "FR", "Name", "Updated", 1l);

		when(dtoValidationService.validateRecipeDTO(dto)).thenReturn(true);
		when(recipeService.saveRecipe(dto)).thenReturn(dto1);

		mockMvc.perform(
				put(BASE_API + "/update").content(TestUtils.objectToJson(dto)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", Matchers.is(1)))
				.andExpect(jsonPath("$.lang", Matchers.is("FR")))
				.andExpect(jsonPath("$.name", Matchers.is("Name")))
				.andExpect(jsonPath("$.description", Matchers.is("Updated")))
				.andExpect(jsonPath("$.typeId", Matchers.is(1)));
	}

	@Test
	public void testUpdateRecipe_withIdNull_thenThrowInvalidParamException() throws Exception {

		RecipeDTO dto = TestUtils.buildRecipeDTO(false, true);

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
	public void testDeleteRecipe() throws Exception {

		doNothing().when(recipeService).deleteRecipe(1l);

		mockMvc.perform(delete(BASE_API + "/delete/1"))
				.andExpect(status().isOk());
	}

}
