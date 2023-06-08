package com.pluralsight.recipe.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pluralsight.recipe.dto.IngredientDTO;
import com.pluralsight.recipe.dto.RecipeDTO;
import com.pluralsight.recipe.dto.RecipeDetailDTO;
import com.pluralsight.recipe.dto.StepDTO;
import com.pluralsight.recipe.dto.mappers.RecipeMapper;
import com.pluralsight.recipe.entities.QRecipe;
import com.pluralsight.recipe.entities.Recipe;
import com.pluralsight.recipe.exceptions.EntityNotFoundException;
import com.pluralsight.recipe.repositories.RecipeRepository;
import com.pluralsight.recipe.services.IngredientService;
import com.pluralsight.recipe.services.RecipeService;
import com.pluralsight.recipe.services.StepService;
import com.pluralsight.recipe.utils.ExceptionMessageConstants;
import com.querydsl.core.types.Predicate;

@Service
public class RecipeServiceImpl implements RecipeService {

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
		
		if(!stepDTOList.isEmpty()) {
			response.setStepList(stepDTOList);
		} else {
			throw new EntityNotFoundException(ExceptionMessageConstants.STEPT_LIST_NOT_FOUND);
		}

		return response;
	}

}
