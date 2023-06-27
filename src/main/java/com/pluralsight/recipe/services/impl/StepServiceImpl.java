package com.pluralsight.recipe.services.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pluralsight.recipe.builders.StepBuilder;
import com.pluralsight.recipe.dto.StepDTO;
import com.pluralsight.recipe.dto.mappers.StepMapper;
import com.pluralsight.recipe.entities.QStep;
import com.pluralsight.recipe.entities.Recipe;
import com.pluralsight.recipe.entities.Step;
import com.pluralsight.recipe.exceptions.EntityWasNotFoundException;
import com.pluralsight.recipe.exceptions.InvalidParamException;
import com.pluralsight.recipe.repositories.RecipeRepository;
import com.pluralsight.recipe.repositories.StepRepository;
import com.pluralsight.recipe.services.StepService;
import com.pluralsight.recipe.utils.ExceptionMessageConstants;
import com.querydsl.core.types.Predicate;

@Service
public class StepServiceImpl implements StepService {

	@Autowired
	private StepRepository stepRepository;

	@Autowired
	private RecipeRepository recipeRepository;

	@Override
	public List<StepDTO> listStepsByRecipe(Long id) {

		List<Step> stepList = new ArrayList<>();

		QStep qStep = QStep.step;
		Predicate predicate = qStep.recipe.id.eq(id);

		stepList = (List<Step>) stepRepository.findAll(predicate);

		return stepList.stream().map((entity) -> StepMapper.MAPPER.mapToDTO(entity))
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

		StepBuilder builder = new StepBuilder();

		Recipe recipe = new Recipe();
		Optional<Recipe> oRecipe = recipeRepository.findById(dto.getRecipeId());
		if (oRecipe.isPresent()) {
			recipe = oRecipe.get();
		} else {
			throw new EntityWasNotFoundException(ExceptionMessageConstants.RECIPE_NOT_FOUND);
		}

		Step step = builder
				.addLang(dto.getLang())
				.addPosition(dto.getPosition())
				.addDescription(dto.getDescription())
				.addRecipe(recipe)
				.build();

		Step savedStep = stepRepository.save(step);

		return StepMapper.MAPPER.mapToDTO(savedStep);
	}

	@Override
	public List<StepDTO> createStepList(List<StepDTO> stepDTOList) {

		List<Integer> distinctPositionList = stepDTOList.stream().map(StepDTO::getPosition).distinct()
				.collect(Collectors.toList());

		if (distinctPositionList.size() != stepDTOList.size()) {
			throw new InvalidParamException(" Position ::" + ExceptionMessageConstants.PARAMETER_UNIQUE);
		}

		List<Step> stepList = new ArrayList<>();

		for (StepDTO dto : stepDTOList) {
			stepList.add(buildStep(dto));
		}

		List<Step> stepListResult = stepRepository.saveAll(stepList);

		return stepListResult.stream().map((entity) -> StepMapper.MAPPER.mapToDTO(entity)).collect(Collectors.toList());
	}

	private Step buildStep(StepDTO dto) {

		StepBuilder builder = new StepBuilder();

		Recipe recipe = new Recipe();
		Optional<Recipe> oRecipe = recipeRepository.findById(dto.getRecipeId());
		if (oRecipe.isPresent()) {
			recipe = oRecipe.get();
		} else {
			throw new EntityWasNotFoundException(ExceptionMessageConstants.RECIPE_NOT_FOUND);
		}
		
		Step step = builder
				.addLang(dto.getLang())
				.addPosition(dto.getPosition())
				.addDescription(dto.getDescription())
				.addRecipe(recipe)
				.build();

		return step;
	}

	@Override
	public List<StepDTO> updateStepList(List<StepDTO> requestDTO) {

		List<Step> stepList = new ArrayList<>();

		for (StepDTO dto : requestDTO) {
			stepList.add(fetchAndUpdateStep(dto));
		}

		List<Step> updatedStepList = stepRepository.saveAll(stepList);

		return updatedStepList.stream().map((entity) -> StepMapper.MAPPER.mapToDTO(entity))
				.sorted(Comparator.comparingInt(StepDTO::getPosition)).collect(Collectors.toList());
	}

	@Override
	public void deleteStep(Long id) {
		stepRepository.deleteById(id);
	}

	@Override
	public StepDTO updateStep(StepDTO stepDTO) {
		
		Step step = fetchAndUpdateStep(stepDTO);
		
		return StepMapper.MAPPER.mapToDTO(step);
	}
	
	private Step fetchAndUpdateStep(StepDTO dto) {

		Step step = new Step();

		if (dto.getId() != null) {

			Optional<Step> oStep = stepRepository.findById(dto.getId());

			if (oStep.isPresent()) {
				step = oStep.get();
			} else {
				throw new EntityWasNotFoundException(ExceptionMessageConstants.STEP_NOT_FOUND);
			}
		} else {
			throw new InvalidParamException(" Id ::" + ExceptionMessageConstants.PARAMETER_NULL);
		}

		String description = dto.getDescription();
		if (description != null && !description.isEmpty() && !description.isBlank()) {
			step.setDescription(description);
		}

		Integer position = dto.getPosition();
		if (position != null) {
			step.setPosition(position);
		}

		return step;
	}

}
