package com.pluralsight.recipe.controllers;

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
import com.pluralsight.recipe.dto.RecipeDetailDTO;
import com.pluralsight.recipe.dto.StepDTO;
import com.pluralsight.recipe.services.RecipeService;
import com.pluralsight.recipe.utils.TestUtils;

@WebMvcTest(RecipeController.class)
public class RecipeControllerTest {

	private static final String BASE_API = "/api/recipes";

	@MockBean
	RecipeService service;

	@Autowired
	MockMvc mockMvc;

	@Test
	public void testListRecipesByLang() throws Exception {

		List<RecipeDTO> list = new ArrayList<>();
		RecipeDTO dto = new RecipeDTO(1l, "FR", "Test", "Recette Test", "Entrée");
		list.add(dto);

		when(service.listRecipes("FR")).thenReturn(list);

		mockMvc.perform(get(BASE_API + "/lang/FR")).andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(1))).andExpect(jsonPath("$[0].id", Matchers.is(1)))
				.andExpect(jsonPath("$[0].lang", Matchers.is("FR")))
				.andExpect(jsonPath("$[0].name", Matchers.is("Test")))
				.andExpect(jsonPath("$[0].description", Matchers.is("Recette Test")))
				.andExpect(jsonPath("$[0].typeCode", Matchers.is("Entrée")));

	}

	@Test
	public void testGetRecipeById() throws Exception {

		RecipeDetailDTO dto = new RecipeDetailDTO();
		RecipeDTO recipeDto = new RecipeDTO(1l, "FR", "Test", "Recette Test", "Entrée");

		List<IngredientDTO> ingredientDTOList = new ArrayList<>();
		IngredientDTO ingredientDTO = new IngredientDTO(1l, "FR", "Ingr", "Type", 1.0, "mL", 1l, 1l, 1l);
		ingredientDTOList.add(ingredientDTO);

		List<StepDTO> stepList = new ArrayList<>();
		StepDTO stepDTO = new StepDTO(1l, "FR", 1, "Step", 1l);
		stepList.add(stepDTO);

		dto.setRecipe(recipeDto);
		dto.setIngredientList(ingredientDTOList);
		dto.setStepList(stepList);

		when(service.getRecipeById(1l)).thenReturn(dto);

		mockMvc.perform(get(BASE_API + "/1")).andExpect(status().isOk())
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

		when(service.createRecipe(dto)).thenReturn(dto);

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
		RecipeDTO dto1 = new RecipeDTO(1l, "FR", "Test1", "Recette Test Updated", "Entrée");

		when(service.updateRecipe(dto)).thenReturn(dto1);

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

		doNothing().when(service).deleteRecipe(1l);

		mockMvc.perform(delete(BASE_API + "/delete/1"))
				.andExpect(status().isOk());

	}

}
