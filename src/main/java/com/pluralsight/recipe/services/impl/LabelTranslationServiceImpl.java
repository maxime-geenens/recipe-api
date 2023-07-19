package com.pluralsight.recipe.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pluralsight.recipe.dto.LabelTranslationDTO;
import com.pluralsight.recipe.dto.mappers.LabelTranslationMapper;
import com.pluralsight.recipe.entities.LabelTranslation;
import com.pluralsight.recipe.entities.QLabelTranslation;
import com.pluralsight.recipe.repositories.LabelTranslationRepository;
import com.pluralsight.recipe.services.LabelTranslationService;
import com.querydsl.core.types.Predicate;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class LabelTranslationServiceImpl implements LabelTranslationService {

	@Autowired
	private LabelTranslationRepository labelTranslationRepository;

	@Autowired
	private LabelTranslationMapper labelTranslationMapper;

	@Override
	public List<LabelTranslationDTO> listLabelsByLang(String lang) {

		QLabelTranslation qLabelTranslation = QLabelTranslation.labelTranslation;
		Predicate predicate = qLabelTranslation.lang.code.eq(lang);

		List<LabelTranslation> list = (List<LabelTranslation>) labelTranslationRepository.findAll(predicate);

		return list.stream().map(entity -> labelTranslationMapper.mapToDTO(entity)).toList();
	}

}
