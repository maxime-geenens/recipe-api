package com.pluralsight.recipe.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.pluralsight.recipe.builders.StepBuilder;
import com.pluralsight.recipe.dto.StepDTO;
import com.pluralsight.recipe.dto.mappers.StepMapper;
import com.pluralsight.recipe.entities.QStep;
import com.pluralsight.recipe.entities.Recipe;
import com.pluralsight.recipe.entities.Step;
import com.pluralsight.recipe.exceptions.EntityNotFoundException;
import com.pluralsight.recipe.exceptions.InvalidParameterException;
import com.pluralsight.recipe.repositories.RecipeRepository;
import com.pluralsight.recipe.repositories.StepRepository;
import com.pluralsight.recipe.services.StepService;
import com.pluralsight.recipe.utils.ExceptionMessageConstants;
import com.querydsl.core.types.Predicate;

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

		return stepList.stream().map((entity) -> StepMapper.MAPPER.mapToDTO(entity)).collect(Collectors.toList());
	}

	@Override
	public List<StepDTO> createStepList(List<StepDTO> stepDTOList) {

		List<Integer> distinctPositionList = stepDTOList.stream().map(StepDTO::getPosition).distinct()
				.collect(Collectors.toList());

		if (distinctPositionList.size() != stepDTOList.size()) {
			throw new InvalidParameterException(" Position ::" + ExceptionMessageConstants.PARAMETER_UNIQUE);
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
			throw new EntityNotFoundException(ExceptionMessageConstants.RECIPE_NOT_FOUND);
		}
		
		Step step = builder
				.addLang(dto.getLang())
				.addPosition(dto.getPosition())
				.addDescription(dto.getDescription())
				.addRecipe(recipe)
				.build();

		return step;
	}

}
