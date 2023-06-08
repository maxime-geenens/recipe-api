package com.pluralsight.recipe.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDetailDTO {
	
	private RecipeDTO recipeDTO;
	private List<IngredientDTO> ingredientDTOList;
	private List<StepDTO> stepList;
}
