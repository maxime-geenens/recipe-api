package com.pluralsight.recipe.utils;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pluralsight.recipe.builders.IngredientBuilder;
import com.pluralsight.recipe.builders.RecipeBuilder;
import com.pluralsight.recipe.builders.StepBuilder;
import com.pluralsight.recipe.dto.IngredientDTO;
import com.pluralsight.recipe.dto.IngredientReferenceDTO;
import com.pluralsight.recipe.dto.IngredientTypeDTO;
import com.pluralsight.recipe.dto.RecipeDTO;
import com.pluralsight.recipe.dto.RecipeTypeDTO;
import com.pluralsight.recipe.dto.StepDTO;
import com.pluralsight.recipe.dto.ToolDTO;
import com.pluralsight.recipe.dto.ToolReferenceDTO;
import com.pluralsight.recipe.dto.ToolTypeDTO;
import com.pluralsight.recipe.entities.Ingredient;
import com.pluralsight.recipe.entities.IngredientReference;
import com.pluralsight.recipe.entities.IngredientType;
import com.pluralsight.recipe.entities.Recipe;
import com.pluralsight.recipe.entities.RecipeType;
import com.pluralsight.recipe.entities.UnitReference;
import com.pluralsight.recipe.entities.Step;
import com.pluralsight.recipe.entities.Tool;
import com.pluralsight.recipe.entities.ToolReference;
import com.pluralsight.recipe.entities.ToolType;

public class TestUtils {

	private TestUtils() {
	}

	private static final Long ID = 1l;
	private static final String LANG = "FR";
	private static final int POSITION = 1;
	private static final double QUANTITY = 1.0;
	private static final String NAME = "Name";
	private static final String DESCRIPTION = "Description";
	private static final String SYMBOL = "g";

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

	// RECIPE

	public static List<Recipe> buildRecipeList(int quantity) {

		List<Recipe> list = new ArrayList<>();

		for (int i = 0; i < quantity; i++) {

			RecipeBuilder builder = new RecipeBuilder();

			RecipeType type = new RecipeType(ID, LANG, NAME);

			list.add(builder.addId(ID + Long.valueOf(i)).addName(NAME).addLang(LANG).addDescription(DESCRIPTION)
					.addType(type).build());
		}

		return list;
	}

	public static Recipe buildRecipe(boolean withId) {

		RecipeBuilder builder = new RecipeBuilder();

		RecipeType type = new RecipeType(ID, LANG, NAME);

		if (withId) {
			builder.addId(ID);
		}

		return builder.addName(NAME).addLang(LANG).addDescription(DESCRIPTION).addType(type).build();
	}

	public static List<RecipeDTO> buildRecipeDTOList(int quantity, boolean withId) {

		List<RecipeDTO> list = new ArrayList<>();

		for (int i = 0; i < quantity; i++) {

			RecipeDTO dto = new RecipeDTO();

			if (withId) {
				dto.setId(ID + Long.valueOf(i));
			}
			dto.setDescription(DESCRIPTION + String.valueOf(i + 1));
			dto.setLang(LANG);
			dto.setName(NAME + String.valueOf(i + 1));
			dto.setTypeId(ID + Long.valueOf(i));

			list.add(dto);
		}

		return list;
	}

	public static final RecipeDTO buildRecipeDTO(boolean withId, boolean withTypeId) {

		RecipeDTO dto = new RecipeDTO();

		if (withId) {
			dto.setId(1l);
		}
		dto.setDescription(DESCRIPTION);
		dto.setLang(LANG);
		dto.setName(NAME);
		if (withTypeId) {
			dto.setTypeId(ID);
		}

		return dto;
	}

	// STEP

	public static Step buildStep() {

		StepBuilder builder = new StepBuilder();
		Recipe recipe = buildRecipe(true);

		return builder.addId(ID).addLang(LANG).addPosition(POSITION).addRecipe(recipe).build();
	}

	public static List<Step> buildStepList(int quantity) {

		List<Step> list = new ArrayList<>();

		for (int i = 0; i < quantity; i++) {

			StepBuilder builder = new StepBuilder();
			Recipe recipe = buildRecipe(true);

			list.add(builder.addId(ID + Long.valueOf(i)).addLang(LANG).addPosition(i + POSITION).addRecipe(recipe)
					.build());
		}
		return list;
	}

	public static final StepDTO buildStepDTO(boolean withId) {

		StepDTO dto = new StepDTO();
		if (withId) {
			dto.setId(ID);
		}
		dto.setLang(LANG);
		dto.setPosition(POSITION);
		dto.setDescription(DESCRIPTION + String.valueOf(dto.getPosition()));
		dto.setRecipeId(ID);

		return dto;
	}

	public static List<StepDTO> buildStepDTOList(int quantity, boolean withId) {

		List<StepDTO> list = new ArrayList<>();

		for (int i = 0; i < quantity; i++) {
			StepDTO dto = new StepDTO();
			if (withId) {
				dto.setId(ID + Long.valueOf(i));
			}
			dto.setLang(LANG);
			dto.setPosition(POSITION + i);
			dto.setDescription(DESCRIPTION + String.valueOf(dto.getPosition()));
			dto.setRecipeId(ID);

			list.add(dto);
		}

		return list;
	}

	// INGREDIENT

	public static Ingredient buildIngredient() {

		IngredientBuilder builder = new IngredientBuilder();

		UnitReference unit = new UnitReference(ID, LANG, "g", "gramme", "USI");
		IngredientType type = new IngredientType(ID, LANG, "Type");
		IngredientReference ref = new IngredientReference(ID, LANG, NAME, type);
		Recipe recipe = buildRecipe(true);

		return builder.addId(ID).addLang(LANG).addQuantity(QUANTITY).addUnit(unit).addIngredient(ref).addRecipe(recipe)
				.build();
	}

	public static List<Ingredient> buildIngredientList(int quantity) {

		List<Ingredient> list = new ArrayList<>();

		for (int i = 0; i < quantity; i++) {

			IngredientBuilder builder = new IngredientBuilder();

			UnitReference unit = new UnitReference(ID + Long.valueOf(i), LANG, "g", "gramme", "USI");
			IngredientType type = new IngredientType(ID + Long.valueOf(i), LANG, "Type");
			IngredientReference ref = new IngredientReference(ID + Long.valueOf(i), LANG, "Ref", type);
			Recipe recipe = buildRecipe(true);

			list.add(builder.addId(ID + Long.valueOf(i)).addLang(LANG).addQuantity(QUANTITY).addUnit(unit)
					.addIngredient(ref).addRecipe(recipe).build());
		}

		return list;
	}

	public static final IngredientDTO buildIngredientDTO(boolean withId) {

		IngredientDTO dto = new IngredientDTO();

		if (withId) {
			dto.setId(1l);
		}
		dto.setLang(LANG);
		dto.setQuantity(QUANTITY);
		dto.setIngredientRefId(ID);
		dto.setRecipeId(ID);
		dto.setUnitRefId(ID);

		return dto;
	}

	public static List<IngredientDTO> buildIngredientDTOList(int quantity, boolean withId) {

		List<IngredientDTO> list = new ArrayList<>();

		for (int i = 0; i < quantity; i++) {
			IngredientDTO dto = new IngredientDTO();

			if (withId) {
				dto.setId(ID + Long.valueOf(i));
			}
			dto.setLang(LANG);
			dto.setQuantity(QUANTITY);
			dto.setIngredientRefId(ID + Long.valueOf(i));
			dto.setRecipeId(ID);
			dto.setUnitRefId(ID);

			list.add(dto);
		}

		return list;
	}

	// TOOL

	public static Tool buildTool() {

		Tool tool = new Tool();
		Recipe recipe = buildRecipe(true);
		ToolType type = buildToolType();
		ToolReference ref = buildToolReference(true, type);

		tool.setId(ID);
		tool.setLang(LANG);
		tool.setQuantity(1);
		tool.setRecipe(recipe);
		tool.setToolReference(ref);

		return tool;
	}

	public static List<Tool> buildToolList(int quantity) {
		List<Tool> list = new ArrayList<>();

		for (int i = 0; i < quantity; i++) {

			Tool tool = new Tool();
			Recipe recipe = buildRecipe(true);
			ToolType type = buildToolType();
			ToolReference ref = buildToolReference(true, type);

			tool.setId(ID + Long.valueOf(i));
			tool.setLang(LANG);
			tool.setQuantity(1);
			tool.setRecipe(recipe);
			tool.setToolReference(ref);

			list.add(tool);
		}

		return list;
	}

	public static ToolDTO buildToolDTO(boolean withId) {

		ToolDTO dto = new ToolDTO();

		if (withId)
			dto.setId(ID);
		dto.setLang(LANG);
		dto.setQuantity(1);
		dto.setRecipeId(ID);
		dto.setToolRefId(ID);

		return dto;
	}

	public static List<ToolDTO> buildToolDTOList(int quantity, boolean withId) {

		List<ToolDTO> list = new ArrayList<>();

		for (int i = 0; i < quantity; i++) {
			ToolDTO dto = new ToolDTO();

			if (withId)
				dto.setId(ID + Long.valueOf(i));
			dto.setLang(LANG);
			dto.setQuantity(quantity);
			dto.setRecipeId(ID);
			dto.setToolRefId(ID);

			list.add(dto);
		}

		return list;
	}

	// REFERENCES

	public static IngredientReference buildIngredientReference(boolean withId, IngredientType type) {

		IngredientReference result = new IngredientReference();

		if (withId) {
			result.setId(ID);
		}
		result.setLang(LANG);
		result.setName(NAME);
		result.setType(type);

		return result;
	}

	public static List<IngredientReference> buildIngredientReferenceList(int quantity) {

		List<IngredientReference> list = new ArrayList<>();

		for (int i = 0; i < quantity; i++) {

			IngredientReference result = new IngredientReference();

			IngredientType type = new IngredientType();

			result.setId(ID + Long.valueOf(i));
			result.setLang(LANG);
			result.setName(NAME);
			result.setType(type);

			list.add(result);
		}

		return list;
	}

	public static IngredientReferenceDTO buildIngredientReferenceDTO(boolean withId) {

		IngredientReferenceDTO dto = new IngredientReferenceDTO();

		if (withId) {
			dto.setId(ID);
		}
		dto.setLang(LANG);
		dto.setName(NAME);
		dto.setTypeId(ID);

		return dto;
	}

	public static UnitReference buildUnitReference() {

		UnitReference result = new UnitReference();

		result.setId(ID);
		result.setLang(LANG);
		result.setName(NAME);
		result.setSymbol(SYMBOL);
		result.setDescription(DESCRIPTION);

		return result;
	}

	public static List<UnitReference> buildUnitReferenceList(int quantity) {

		List<UnitReference> list = new ArrayList<>();

		for (int i = 0; i < quantity; i++) {

			UnitReference result = new UnitReference();

			result.setId(ID + Long.valueOf(i));
			result.setLang(LANG);
			result.setName(NAME);
			result.setSymbol(SYMBOL);
			result.setDescription(DESCRIPTION);

			list.add(result);
		}

		return list;
	}

	public static ToolReference buildToolReference(boolean withId, ToolType type) {

		ToolReference ref = new ToolReference();

		if (withId)
			ref.setId(ID);
		ref.setLang(LANG);
		ref.setName(NAME);
		ref.setType(type);

		return ref;
	}

	public static List<ToolReference> buildToolReferenceList(int quantity) {

		List<ToolReference> list = new ArrayList<>();

		for (int i = 0; i < quantity; i++) {

			ToolReference result = new ToolReference();
			ToolType type = buildToolType();

			result.setId(ID + Long.valueOf(i));
			result.setLang(LANG);
			result.setName(NAME);
			result.setType(type);

			list.add(result);
		}

		return list;
	}

	public static ToolReferenceDTO buildToolReferenceDTO(boolean withId) {

		ToolReferenceDTO dto = new ToolReferenceDTO();

		if (withId)
			dto.setId(ID);
		dto.setLang(LANG);
		dto.setName(NAME);
		dto.setTypeId(ID);

		return dto;
	}

	// TYPES

	public static RecipeType buildRecipeType() {

		RecipeType result = new RecipeType();

		result.setId(ID);
		result.setLang(LANG);
		result.setName(NAME);

		return result;
	}

	public static List<RecipeType> buildRecipeTypeList(int quantity) {

		List<RecipeType> list = new ArrayList<>();

		for (int i = 0; i < quantity; i++) {
			RecipeType result = new RecipeType();

			result.setId(ID + Long.valueOf(i));
			result.setLang(LANG);
			result.setName(NAME);

			list.add(result);
		}

		return list;
	}

	public static List<RecipeTypeDTO> buildRecipeTypeDTO(int quantity) {

		List<RecipeTypeDTO> list = new ArrayList<>();

		for (int i = 0; i < quantity; i++) {
			RecipeTypeDTO dto = new RecipeTypeDTO();

			dto.setId(ID + Long.valueOf(i));
			dto.setLang(LANG);
			dto.setName(NAME);

			list.add(dto);
		}

		return list;
	}

	public static IngredientType buildIngredientType() {

		IngredientType result = new IngredientType();

		result.setId(ID);
		result.setLang(LANG);
		result.setName(NAME);

		return result;
	}

	public static List<IngredientType> buildIngredientTypeList(int quantity) {

		List<IngredientType> list = new ArrayList<>();

		for (int i = 0; i < quantity; i++) {

			IngredientType result = new IngredientType();

			result.setId(ID + Long.valueOf(i));
			result.setLang(LANG);
			result.setName(NAME);

			list.add(result);
		}

		return list;
	}

	public static List<IngredientTypeDTO> buildIngredientTypeDTOList(int quantity) {

		List<IngredientTypeDTO> list = new ArrayList<>();

		for (int i = 0; i < quantity; i++) {
			IngredientTypeDTO dto = new IngredientTypeDTO();

			dto.setId(ID + Long.valueOf(i));
			dto.setLang(LANG);
			dto.setName(NAME);

			list.add(dto);
		}

		return list;
	}

	public static ToolType buildToolType() {

		ToolType type = new ToolType();

		type.setId(ID);
		type.setLang(LANG);
		type.setName(NAME);

		return type;
	}

	public static List<ToolType> buildToolTypeList(int quantity) {

		List<ToolType> list = new ArrayList<>();

		for (int i = 0; i < quantity; i++) {

			ToolType type = new ToolType();

			type.setId(ID + Long.valueOf(i));
			type.setLang(LANG);
			type.setName(NAME);

			list.add(type);
		}

		return list;
	}

	public static List<ToolTypeDTO> buildToolTypeDTOList(int quantity) {

		List<ToolTypeDTO> list = new ArrayList<>();

		for (int i = 0; i < quantity; i++) {

			ToolTypeDTO type = new ToolTypeDTO();

			type.setId(ID + Long.valueOf(i));
			type.setLang(LANG);
			type.setName(NAME);

			list.add(type);
		}

		return list;
	}

}
