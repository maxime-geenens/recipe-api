package com.pluralsight.recipe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDTO {

	private Long id;
	private String lang;
	private String name;
	private String type;
	private Double quantity;
	private String unit;
	private Long unitRefId;
	private Long ingredientRefId;
	private Long recipeId;
}
