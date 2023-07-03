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
import com.pluralsight.recipe.exceptions.EntityWasNotFoundException;
import com.pluralsight.recipe.repositories.RecipeRepository;
import com.pluralsight.recipe.services.RecipeService;
import com.pluralsight.recipe.utils.ExceptionMessageConstants;
import com.querydsl.core.types.Predicate;

@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	private RecipeRepository recipeRepository;
	
	@Autowired 
	private RecipeMapper mapper;

	@Override
	public List<RecipeDTO> listRecipesByLang(String lang) {

		List<Recipe> recipeList = new ArrayList<>();

		QRecipe qRecipe = QRecipe.recipe;
		Predicate predicate = qRecipe.lang.eq(lang);

		recipeList = (List<Recipe>) recipeRepository.findAll(predicate);

		return recipeList.stream().map((entity) -> mapper.mapToDTO(entity)).collect(Collectors.toList());
	}

	@Override
	public RecipeDTO getRecipeDTOById(Long id) {

		Optional<Recipe> oRecipe = recipeRepository.findById(id);

		if (oRecipe.isPresent()) {
			return mapper.mapToDTO(oRecipe.get());
		} else {
			throw new EntityWasNotFoundException(ExceptionMessageConstants.RECIPE_NOT_FOUND);
		}
	}

	@Override
	public Recipe getRecipeById(Long id) {

		Optional<Recipe> oRecipe = recipeRepository.findById(id);

		if (oRecipe.isPresent()) {
			return oRecipe.get();
		} else {
			throw new EntityWasNotFoundException(ExceptionMessageConstants.RECIPE_NOT_FOUND);
		}
	}

	@Override
	public RecipeDTO saveRecipe(RecipeDTO dto) {

		Recipe savedRecipe = recipeRepository.save(mapper.mapToEntity(dto));

		return mapper.mapToDTO(savedRecipe);
	}

	@Override
	public void deleteRecipe(Long id) {
		recipeRepository.deleteById(id);
	}

}
