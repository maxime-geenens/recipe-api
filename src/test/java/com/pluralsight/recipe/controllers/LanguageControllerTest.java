package com.pluralsight.recipe.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.pluralsight.recipe.dto.LabelTranslationDTO;
import com.pluralsight.recipe.dto.LangReferenceDTO;
import com.pluralsight.recipe.services.LabelTranslationService;

@WebMvcTest(LanguageController.class)
class LanguageControllerTest {

	private static final String BASE_API = "/api/languages";

	@MockBean
	LabelTranslationService service;

	@Autowired
	MockMvc mockMvc;

	@Test
	void testFetchLabelListByLang() throws Exception {

		List<LabelTranslationDTO> list = new ArrayList<>();
		LangReferenceDTO refDTO = new LangReferenceDTO(1l, "Français", "FR");
		LabelTranslationDTO dto = new LabelTranslationDTO(1l, "recipe", "recette", refDTO);
		list.add(dto);

		when(service.listLabelsByLang("FR")).thenReturn(list);

		mockMvc.perform(get(BASE_API + "/label/list/FR"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].id", Matchers.is(1)))
				.andExpect(jsonPath("$[0].lang.id", Matchers.is(1)))
				.andExpect(jsonPath("$[0].label", Matchers.is("recipe")))
				.andExpect(jsonPath("$[0].name", Matchers.is("recette")));
		
	}

}
