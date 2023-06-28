package com.pluralsight.recipe.utils;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pluralsight.recipe.dto.IngredientDTO;
import com.pluralsight.recipe.dto.RecipeDTO;
import com.pluralsight.recipe.dto.StepDTO;

public class TestUtils {

	public static String objectToJson(Object obj) {

		ObjectMapper mapper = new ObjectMapper();
		String result = "";

		try {
			result = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static List<StepDTO> buildStepDTOList(int quantity, boolean withId) {

		List<StepDTO> list = new ArrayList<>();

		for (int i = 0; i < quantity; i++) {
			StepDTO dto = new StepDTO();
			if (withId) {
				dto.setId(Long.valueOf(i + 1));
			}
			dto.setLang("FR");
			dto.setPosition(i + 1);
			dto.setDescription("Step" + String.valueOf(dto.getPosition()));
			dto.setRecipeId(1l);

			list.add(dto);
		}

		return list;
	}

	public static final StepDTO buildStepDTO(boolean withId) {

		StepDTO dto = new StepDTO();
		if (withId) {
			dto.setId(1l);
		}
		dto.setLang("FR");
		dto.setPosition(1);
		dto.setDescription("Step1");
		dto.setRecipeId(1l);

		return dto;
	}

	public static List<IngredientDTO> buildIngredientDTOList(int quantity, boolean withId) {

		List<IngredientDTO> list = new ArrayList<>();

		for (int i = 0; i < quantity; i++) {
			IngredientDTO dto = new IngredientDTO();

			if (withId) {
				dto.setId(Long.valueOf(i + 1));
			}
			dto.setLang("FR");
			dto.setName("Ingredient" + String.valueOf(i + 1));
			dto.setQuantity(1.0);
			dto.setIngredientRefId(Long.valueOf(i + 1));
			dto.setRecipeId(1l);
			dto.setUnitRefId(1l);

			list.add(dto);
		}

		return list;
	}

	public static final IngredientDTO buildIngredientDTO(boolean withId) {

		IngredientDTO dto = new IngredientDTO();

		if (withId) {
			dto.setId(1l);
		}
		dto.setLang("FR");
		dto.setName("Ingredient1");
		dto.setQuantity(1.0);
		dto.setIngredientRefId(1l);
		dto.setRecipeId(1l);
		dto.setUnitRefId(1l);

		return dto;
	}
	
	public static List<RecipeDTO> buildRecipeDTOList(int quantity, boolean withId) {
		
		List<RecipeDTO> list = new ArrayList<>();
		
		
		
		
		return list;
	}

	public static final RecipeDTO buildRecipeDTO(boolean withId) {

		RecipeDTO dto = new RecipeDTO();

		if (withId) {
			dto.setId(1l);
		}
		dto.setDescription("Recette Numero 1");
		dto.setLang("FR");
		dto.setName("Recette1");
		dto.setTypeCode(dto.getLang() + dto.getName());

		return dto;
	}

}
