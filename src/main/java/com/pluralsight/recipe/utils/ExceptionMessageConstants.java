package com.pluralsight.recipe.utils;

public class ExceptionMessageConstants {

	private ExceptionMessageConstants() {
	}

	// ENTITY NOT FOUND
	public static final String RECIPE_NOT_FOUND = "Recipe not found.";
	public static final String INGREDIENT_LIST_NOT_FOUND = "Ingredient list not found for this recipe.";
	public static final String STEPT_LIST_NOT_FOUND = "Step list not found for this recipe.";
	public static final String RECIPE_TYPE_CODE_NOT_FOUND = "RecipeType not found for the given code.";
	public static final String UNIT_REFERENCE_NOT_FOUND = "UnitReference not found.";
	public static final String INGREDIENT_REFERENCE_NOT_FOUND = "IngredientReference not found.";
	public static final String STEP_NOT_FOUND = "Step not found.";
	public static final String INGREDIENT_NOT_FOUND = "Ingredient not found.";
	public static final String INGREDIENT_TYPE_NOT_FOUND = "Ingredient type not found.";
	public static final String TOOL_LIST_NOT_FOUND = "Tool list not found for this recipe.";

	// INVALID PARAMETER
	public static final String PARAMETER_NULL = "Parameter shall not be null.";
	public static final String PARAMETER_INVALID = "Parameter isn't valid.";
	public static final String PARAMETER_BLANK_EMPTY = "Parameter shall not be blank or empty.";
	public static final String PARAMETER_NEGATIVE = "Parameter shall not < 0 .";
	public static final String PARAMETER_UNIQUE = "Parameter shall be unique.";
	public static final String CODE_ALREADY_EXISTS_IN_DB = "Name already exists for this language in Database.";

	// OBJECTS AND PARAMS
	public static final String RECIPE_DTO = "RecipeDTO";
	public static final String RECIPE = "Recipe";
	public static final String INGREDIENT_DTO = "IngredientDTO";
	public static final String STEP_DTO = "StepDTO";
	public static final String INGREDIENT_REF_DTO = "IngredientReferenceDTO";
	public static final String INGREDIENT_REF = "IngredientReference";
	public static final String TOOL_DTO = "ToolDTO";
	public static final String TOOL = "Tool";
	public static final String TOOL_REF_DTO = "ToolReferenceDTO";
	public static final String TOOL_REF = "ToolReference";

	public static final String PARAM_ID = ".id";
	public static final String PARAM_NAME = ".name";
	public static final String PARAM_DESCRIPTION = ".description";
	public static final String PARAM_LANG = ".lang";
	public static final String PARAM_QUANTITY = ".quantity";
	public static final String PARAM_POSITION = ".position";
	public static final String PARAM_UNIT_REF_ID = ".unitRefId";
	public static final String PARAM_INGREDIENT_REF_ID = ".ingredientRefId";
	public static final String PARAM_RECIPE_ID = ".recipeId";
	public static final String PARAM_TYPE_ID = ".typeId";
	public static final String PARAM_TOOL_REF_ID = ".toolRefId";
}
