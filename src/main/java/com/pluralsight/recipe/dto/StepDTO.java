package com.pluralsight.recipe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StepDTO {

	private Long id;
	private String lang;
	private Integer position;
	private String description;
	private Long recipeId;

}
