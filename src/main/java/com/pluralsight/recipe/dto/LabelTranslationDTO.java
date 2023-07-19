package com.pluralsight.recipe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabelTranslationDTO {

	private Long id;
	private String label;
	private String name;
	private LangReferenceDTO lang;

}
