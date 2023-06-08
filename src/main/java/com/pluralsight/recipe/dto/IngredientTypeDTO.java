package com.pluralsight.recipe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientTypeDTO {

	private Long id;
	private String lang;
	private String name;

}
