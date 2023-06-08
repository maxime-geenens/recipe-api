package com.pluralsight.recipe.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.pluralsight.recipe.dto.StepDTO;
import com.pluralsight.recipe.dto.mappers.StepMapper;
import com.pluralsight.recipe.entities.QStep;
import com.pluralsight.recipe.entities.Step;
import com.pluralsight.recipe.repositories.StepRepository;
import com.pluralsight.recipe.services.StepService;
import com.querydsl.core.types.Predicate;

public class StepServiceImpl implements StepService {

	@Autowired
	private StepRepository stepRepository;

	@Override
	public List<StepDTO> listStepsByRecipe(Long id) {

		List<Step> stepList = new ArrayList<>();

		QStep qStep = QStep.step;
		Predicate predicate = qStep.recipe.id.eq(id);

		stepList = (List<Step>) stepRepository.findAll(predicate);

		return stepList.stream().map((entity) -> StepMapper.MAPPER.mapToDTO(entity)).collect(Collectors.toList());
	}

}
