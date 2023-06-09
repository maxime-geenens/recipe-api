package com.pluralsight.recipe.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pluralsight.recipe.builders.IngredientBuilder;
import com.pluralsight.recipe.builders.RecipeBuilder;
import com.pluralsight.recipe.dto.IngredientDTO;
import com.pluralsight.recipe.dto.RecipeDTO;
import com.pluralsight.recipe.dto.RecipeDetailDTO;
import com.pluralsight.recipe.dto.StepDTO;
import com.pluralsight.recipe.dto.mappers.IngredientMapper;
import com.pluralsight.recipe.dto.mappers.RecipeMapper;
import com.pluralsight.recipe.entities.Ingredient;
import com.pluralsight.recipe.entities.IngredientReference;
import com.pluralsight.recipe.entities.QRecipe;
import com.pluralsight.recipe.entities.Recipe;
import com.pluralsight.recipe.entities.RecipeType;
import com.pluralsight.recipe.entities.Step;
import com.pluralsight.recipe.entities.UnitReference;
import com.pluralsight.recipe.exceptions.EntityNotFoundException;
import com.pluralsight.recipe.repositories.RecipeRepository;
import com.pluralsight.recipe.services.IngredientService;
import com.pluralsight.recipe.services.RecipeService;
import com.pluralsight.recipe.services.ReferenceService;
import com.pluralsight.recipe.services.StepService;
import com.pluralsight.recipe.utils.ExceptionMessageConstants;
import com.querydsl.core.types.Predicate;

@Service
public class RecipeServiceImpl implements RecipeService {
	
	@Autowired
	private ReferenceService referenceService;

	@Autowired
	private IngredientService ingredientService;

	@Autowired
	private StepService stepService;

	@Autowired
	private RecipeRepository recipeRepository;

	@Override
	public List<RecipeDTO> listRecipes(String lang) {

		List<Recipe> recipeList = new ArrayList<>();

		if (lang != null && !lang.isEmpty() && !lang.isBlank()) {

			QRecipe qRecipe = QRecipe.recipe;
			Predicate predicate = qRecipe.lang.eq(lang);

			recipeList = (List<Recipe>) recipeRepository.findAll(predicate);
		} else {
			recipeList = recipeRepository.findAll();
		}

		return recipeList.stream().map((entity) -> RecipeMapper.MAPPER.mapToDTO(entity)).collect(Collectors.toList());
	}

	@Override
	public RecipeDetailDTO getRecipeById(Long id) throws EntityNotFoundException {

		RecipeDetailDTO response = new RecipeDetailDTO();

		Optional<Recipe> oRecipe = recipeRepository.findById(id);
		RecipeDTO recipeDTO = new RecipeDTO();

		if (oRecipe.isPresent() && !oRecipe.isEmpty()) {
			recipeDTO = RecipeMapper.MAPPER.mapToDTO(oRecipe.get());
			response.setRecipeDTO(recipeDTO);
		} else {
			throw new EntityNotFoundException(ExceptionMessageConstants.RECIPE_NOT_FOUND);
		}

		List<IngredientDTO> ingredientDTOList = ingredientService.listIngredientsByRecipe(id);

		if (!ingredientDTOList.isEmpty()) {
			response.setIngredientDTOList(ingredientDTOList);
		} else {
			throw new EntityNotFoundException(ExceptionMessageConstants.INGREDIENT_LIST_NOT_FOUND);
		}

		List<StepDTO> stepDTOList = stepService.listStepsByRecipe(id);

		if (!stepDTOList.isEmpty()) {
			response.setStepList(stepDTOList);
		} else {
			throw new EntityNotFoundException(ExceptionMessageConstants.STEPT_LIST_NOT_FOUND);
		}

		return response;
	}

	@Override
	public RecipeDetailDTO createRecipe(RecipeDetailDTO requestDTO) {

		RecipeDetailDTO responseDTO = new RecipeDetailDTO();

		// Recipe
		RecipeDTO recipeDTO = requestDTO.getRecipeDTO();
		RecipeType recipeType = referenceService.getRecipeTypeByCode(recipeDTO.getTypeCode());

		RecipeBuilder builder = new RecipeBuilder();
		Recipe recipe = builder
				.addName(recipeDTO.getName())
				.addDescription(recipeDTO.getDescription())
				.addLang(recipeDTO.getLang())
				.addType(recipeType)
				.build();

		Recipe recipeResult = recipeRepository.save(recipe);
		responseDTO.setRecipeDTO(RecipeMapper.MAPPER.mapToDTO(recipeResult));

		// Ingredients
		List<IngredientDTO> ingredientDTOList = requestDTO.getIngredientDTOList();
		List<Ingredient> ingredientList = new ArrayList<>();

		for (IngredientDTO dto : ingredientDTOList) {
			ingredientList.add(buildIngredient(dto, recipeResult.getId()));
		}

		List<Ingredient> ingredientListResult = ingredientService.createIngredientList(ingredientList);
		List<IngredientDTO> ingredientDTOResponse = ingredientListResult.stream()
				.map((entity) -> IngredientMapper.MAPPER.mapToDTO(entity)).collect(Collectors.toList());
		responseDTO.setIngredientDTOList(ingredientDTOResponse);

		// Steps
		List<StepDTO> stepDTOList = requestDTO.getStepList();
		List<Step> stepList = new ArrayList<>();

		for (StepDTO dto : stepDTOList) {
			stepList.add(buildStep(dto, recipeResult.getId()));
		}

		List<Step> stepListResult = stepService.createStepList(stepList);
		List<StepDTO> stepDTOResponse = stepListResult.stream().map((entity) -> StepMapper.MAPPER.mapToDTO(entity))
				.collect(Collectors.toList());
		responseDTO.setStepList(stepDTOResponse);

		return responseDTO;
	}

	private Ingredient buildIngredient(IngredientDTO dto, Long recipeId) {

		IngredientBuilder builder = new IngredientBuilder();

		UnitReference unit = referenceService.getUnitReferenceById(dto.getUnitRefId());
		IngredientReference ingredientRef = referenceService.getIngredientReferenceById(dto.getIngredientRefId());
		Recipe recipe = new Recipe();
		Optional<Recipe> oRecipe = recipeRepository.findById(recipeId);
		if (oRecipe.isPresent()) {
			recipe = oRecipe.get();
		} else {
			throw new EntityNotFoundException(ExceptionMessageConstants.RECIPE_NOT_FOUND);
		}

		Ingredient ingredient = builder
				.addLang(dto.getLang())
				.addQuantity(dto.getQuantity())
				.addIngredient(ingredientRef)
				.addUnit(unit)
				.addRecipe(recipe)
				.build();
		
		return null;
	}
	
	private Step buildStep(StepDTO dto, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
