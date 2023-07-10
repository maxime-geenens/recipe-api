package com.pluralsight.recipe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToolDTO {

	private Long id;
	private String lang;
	private Integer quantity;
	private ToolReferenceDTO toolRef;
	private Long recipeId;
}
