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
import com.pluralsight.recipe.dto.RecipeDTO;
import com.pluralsight.recipe.dto.StepDTO;
import com.pluralsight.recipe.entities.Recipe;
import com.pluralsight.recipe.entities.RecipeType;
import com.pluralsight.recipe.exceptions.InvalidParamException;
import com.pluralsight.recipe.services.IngredientService;
import com.pluralsight.recipe.services.RecipeService;
import com.pluralsight.recipe.services.ReferencesService;
import com.pluralsight.recipe.services.StepService;
import com.pluralsight.recipe.services.VaildationDTOService;
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
	VaildationDTOService dtoValidationService;

	@Autowired
	MockMvc mockMvc;

	@Test
	public void testListRecipesByLang() throws Exception {

		List<RecipeDTO> list = new ArrayList<>();
		RecipeDTO dto = new RecipeDTO(1l, "FR", "Test", "Recette Test", "Entrée");
		list.add(dto);

		when(recipeService.listRecipesByLang("FR")).thenReturn(list);

		mockMvc.perform(get(BASE_API + "/lang/FR"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(1)));
	}

	@Test
	public void testListRecipesByLang_withLangBlank_thenThrowInvalidParamException() throws Exception {

		mockMvc.perform(get(BASE_API + "/lang/  "))
				.andExpect(status().is5xxServerError())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidParamException))
				.andExpect(result -> assertEquals(" Lang ::" + ExceptionMessageConstants.PARAMETER_BLANK_EMPTY,
						result.getResolvedException().getMessage()));
	}

	@Test
	public void testGetRecipeDetail() throws Exception {

		RecipeDTO rDTO = new RecipeDTO(1l, "FR", "Test", "Recette Test", "Entrée");

		List<IngredientDTO> iList = new ArrayList<>();
		IngredientDTO iDTO = new IngredientDTO(1l, "FR", "Name", 1.0, 1l, 1l, 1l);
		iList.add(iDTO);

		List<StepDTO> sList = new ArrayList<>();
		StepDTO sDTO = new StepDTO(1l, "FR", 1, "Description", 1l);
		sList.add(sDTO);

		when(recipeService.getRecipeById(1l)).thenReturn(rDTO);
		when(ingredientService.listIngredientsByRecipe(1l)).thenReturn(iList);
		when(stepService.listStepsByRecipe(1l)).thenReturn(sList);

		mockMvc.perform(get(BASE_API + "/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.recipe.id", Matchers.is(1)))
				.andExpect(jsonPath("$.recipe.lang", Matchers.is("FR")))
				.andExpect(jsonPath("$.recipe.name", Matchers.is("Test")))
				.andExpect(jsonPath("$.recipe.description", Matchers.is("Recette Test")))
				.andExpect(jsonPath("$.recipe.typeCode", Matchers.is("Entrée")))
				.andExpect(jsonPath("$.ingredientList", Matchers.hasSize(1)))
				.andExpect(jsonPath("$.stepList", Matchers.hasSize(1)));
	}

	@Test
	public void testCreateRecipe() throws Exception {

		RecipeDTO dto = new RecipeDTO(1l, "FR", "Test", "Recette Test", "Entrée");
		Recipe recipe = new Recipe();
		RecipeType type = new RecipeType();
		
		when(dtoValidationService.validateRecipeDTO(dto)).thenReturn(true);
		when(referenceService.getRecipeTypeByCode(dto.getTypeCode())).thenReturn(type);
		when(recipeService.buildRecipe(dto, type)).thenReturn(recipe);
		when(recipeService.saveRecipe(recipe)).thenReturn(dto);

		mockMvc.perform(
				post(BASE_API + "/create").content(TestUtils.objectToJson(dto)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", Matchers.is(1)))
				.andExpect(jsonPath("$.lang", Matchers.is("FR")))
				.andExpect(jsonPath("$.name", Matchers.is("Test")))
				.andExpect(jsonPath("$.description", Matchers.is("Recette Test")))
				.andExpect(jsonPath("$.typeCode", Matchers.is("Entrée")));
	}

	@Test
	public void testUpdateRecipe() throws Exception {

		RecipeDTO dto = new RecipeDTO(1l, "FR", "Test", "Recette Test", "Entrée");
		RecipeType type = new RecipeType();
		RecipeDTO dto1 = new RecipeDTO(1l, "FR", "Test1", "Recette Test Updated", "Entrée");

		when(dtoValidationService.validateRecipeDTO(dto)).thenReturn(true);
		when(referenceService.getRecipeTypeByCode(dto.getTypeCode())).thenReturn(type);
		when(recipeService.updateRecipe(dto, type)).thenReturn(dto1);

		mockMvc.perform(
				put(BASE_API + "/update/1").content(TestUtils.objectToJson(dto)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", Matchers.is(1)))
				.andExpect(jsonPath("$.lang", Matchers.is("FR")))
				.andExpect(jsonPath("$.name", Matchers.is("Test1")))
				.andExpect(jsonPath("$.description", Matchers.is("Recette Test Updated")))
				.andExpect(jsonPath("$.typeCode", Matchers.is("Entrée")));
	}
	
	@Test
	public void testDeleteRecipe() throws Exception {

		doNothing().when(recipeService).deleteRecipe(1l);

		mockMvc.perform(delete(BASE_API + "/delete/1"))
				.andExpect(status().isOk());
	}

}
