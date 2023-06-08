package com.pluralsight.recipe.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pluralsight.recipe.dto.RecipeDTO;
import com.pluralsight.recipe.dto.mappers.RecipeMapper;
import com.pluralsight.recipe.entities.QRecipe;
import com.pluralsight.recipe.entities.Recipe;
import com.pluralsight.recipe.exceptions.EntityNotFoundException;
import com.pluralsight.recipe.repositories.RecipeRepository;
import com.pluralsight.recipe.services.RecipeService;
import com.pluralsight.recipe.utils.ExceptionMessageConstants;

@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	private RecipeRepository recipeRepository;

	@Override
	public List<RecipeDTO> listRecipes(String lang) {

		List<Recipe> recipeList = new ArrayList<>();

		if (lang != null && !lang.isEmpty() && !lang.isBlank()) {

			QRecipe qRecipe = QRecipe.recipe;

			recipeList = (List<Recipe>) recipeRepository.findAll(qRecipe.lang.eq(lang));
		} else {
			recipeList = recipeRepository.findAll();
		}

		return recipeList.stream().map((entity) -> RecipeMapper.MAPPER.mapToDTO(entity)).collect(Collectors.toList());
	}

	@Override
	public RecipeDTO getRecipeById(Long id) throws EntityNotFoundException {

		Optional<Recipe> oRecipe = recipeRepository.findById(id);

		if (oRecipe.isPresent() && !oRecipe.isEmpty()) {
			return RecipeMapper.MAPPER.mapToDTO(oRecipe.get());
		} else {
			throw new EntityNotFoundException(ExceptionMessageConstants.RECIPE_NOT_FOUND);
		}

	}

}
