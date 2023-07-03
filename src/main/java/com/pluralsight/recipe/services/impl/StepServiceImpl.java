package com.pluralsight.recipe.services.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pluralsight.recipe.dto.StepDTO;
import com.pluralsight.recipe.dto.mappers.StepMapper;
import com.pluralsight.recipe.entities.QStep;
import com.pluralsight.recipe.entities.Step;
import com.pluralsight.recipe.exceptions.InvalidParamException;
import com.pluralsight.recipe.repositories.StepRepository;
import com.pluralsight.recipe.services.StepService;
import com.pluralsight.recipe.utils.ExceptionMessageConstants;
import com.querydsl.core.types.Predicate;

@Service
public class StepServiceImpl implements StepService {

	@Autowired
	private StepRepository stepRepository;
	
	@Autowired
	private StepMapper mapper;

	@Override
	public List<StepDTO> listStepsByRecipe(Long id) {

		List<Step> stepList = new ArrayList<>();

		QStep qStep = QStep.step;
		Predicate predicate = qStep.recipe.id.eq(id);

		stepList = (List<Step>) stepRepository.findAll(predicate);

		return stepList.stream().map((entity) -> mapper.mapToDTO(entity))
				.sorted(Comparator.comparingInt(StepDTO::getPosition)).collect(Collectors.toList());
	}

	@Override
	public StepDTO addStep(StepDTO dto) {

		List<Step> stepList = new ArrayList<>();

		QStep qStep = QStep.step;
		Predicate predicate = qStep.recipe.id.eq(dto.getRecipeId());

		stepList = (List<Step>) stepRepository.findAll(predicate);

		List<Integer> positionList = stepList.stream().map(Step::getPosition).sorted().collect(Collectors.toList());
		Integer lastPosition = positionList.get(positionList.size() - 1);
		
		if (!dto.getPosition().equals(lastPosition + 1)) {
			dto.setPosition(lastPosition + 1);
		}

		Step savedStep = stepRepository.save(mapper.mapToEntity(dto));

		return mapper.mapToDTO(savedStep);
	}

	@Override
	public List<StepDTO> saveStepList(List<StepDTO> stepDTOList) {

		if (!isPositionUnique(stepDTOList)) {
			throw new InvalidParamException(" Position ::" + ExceptionMessageConstants.PARAMETER_UNIQUE);
		}

		List<Step> stepList = new ArrayList<>();

		for (StepDTO dto : stepDTOList) {
			stepList.add(mapper.mapToEntity(dto));
		}

		List<Step> stepListResult = stepRepository.saveAll(stepList);

		return stepListResult.stream().map((entity) -> mapper.mapToDTO(entity))
				.sorted(Comparator.comparingInt(StepDTO::getPosition)).collect(Collectors.toList());
	}

	@Override
	public void deleteStep(Long id) {
		stepRepository.deleteById(id);
	}

	@Override
	public StepDTO updateStep(StepDTO dto) {

		List<StepDTO> stepDTOList = listStepsByRecipe(dto.getRecipeId());

		if (!isPositionUnique(stepDTOList)) {
			throw new InvalidParamException(" Position :: " + ExceptionMessageConstants.PARAMETER_UNIQUE);
		}

		Step step = mapper.mapToEntity(dto);

		Step updatedStep = stepRepository.save(step);

		return mapper.mapToDTO(updatedStep);
	}

	private boolean isPositionUnique(List<StepDTO> stepDTOList) {

		List<Integer> distinctPositionList = stepDTOList.stream().map(StepDTO::getPosition).distinct()
				.collect(Collectors.toList());

		return distinctPositionList.size() == stepDTOList.size();
	}

}
