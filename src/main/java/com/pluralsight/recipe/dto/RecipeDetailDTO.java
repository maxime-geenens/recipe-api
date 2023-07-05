package com.pluralsight.recipe.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDetailDTO {
	
	private RecipeDTO recipe;
	private List<IngredientDTO> ingredientList;
	private List<StepDTO> stepList;
	private List<ToolDTO> toolList;
}
