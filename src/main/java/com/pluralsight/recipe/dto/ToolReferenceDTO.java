package com.pluralsight.recipe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToolReferenceDTO {
	
	private Long id;
	private String lang;
	private String name;
	private Long typeId;
}
