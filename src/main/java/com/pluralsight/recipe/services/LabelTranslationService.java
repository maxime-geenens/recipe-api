package com.pluralsight.recipe.services;

import java.util.List;

import com.pluralsight.recipe.dto.LabelTranslationDTO;

public interface LabelTranslationService {

	/**
	 * 
	 * Lists all labels for a given lang
	 * 
	 * @param lang
	 * @return List<LabelTranslationDTO>
	 */
	List<LabelTranslationDTO> listLabelsByLang(String lang);

}
