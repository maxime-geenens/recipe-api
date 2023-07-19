package com.pluralsight.recipe.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pluralsight.recipe.dto.LabelTranslationDTO;
import com.pluralsight.recipe.dto.LangReferenceDTO;
import com.pluralsight.recipe.dto.mappers.LabelTranslationMapper;
import com.pluralsight.recipe.entities.LabelTranslation;
import com.pluralsight.recipe.entities.LangReference;
import com.pluralsight.recipe.entities.QLabelTranslation;
import com.pluralsight.recipe.repositories.LabelTranslationRepository;
import com.pluralsight.recipe.services.impl.LabelTranslationServiceImpl;
import com.querydsl.core.types.Predicate;

@ExtendWith(MockitoExtension.class)
class LabelTranslationServiceTest {

	@InjectMocks
	private LabelTranslationServiceImpl service;

	@Mock
	private LabelTranslationRepository repository;

	@Mock
	private LabelTranslationMapper mapper;

	@DisplayName("JUnit Test for listToolTypesByLang method")
	@Test
	void givenTypeAndLang_whenListToolTypesByLang_thenReturnToolTypeDTOList() {

		List<LabelTranslation> list = new ArrayList<>();
		List<LabelTranslationDTO> dtoList = new ArrayList<>();

		LangReference ref = new LangReference(1l, "Français", "FR");
		LabelTranslation entity = new LabelTranslation(1l, "recipe", "recette", ref);
		LangReferenceDTO dtoRef = new LangReferenceDTO(1l, "Français", "FR");
		LabelTranslationDTO dto = new LabelTranslationDTO(1l, "recipe", "recette", dtoRef);

		list.add(entity);
		dtoList.add(dto);

		QLabelTranslation qLabelTranslation = QLabelTranslation.labelTranslation;
		Predicate predicate = qLabelTranslation.lang.code.eq("FR");

		given(repository.findAll(predicate)).willReturn(list);

		for (int i = 0; i < list.size(); i++) {
			given(mapper.mapToDTO(list.get(i))).willReturn(dtoList.get(i));
		}

		List<LabelTranslationDTO> result = service.listLabelsByLang("FR");

		assertThat(result)
			.isNotNull()
			.isNotEmpty()
			.hasSize(1);
	}
	
	

}
