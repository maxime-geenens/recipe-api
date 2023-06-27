package com.pluralsight.recipe.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pluralsight.recipe.builders.RecipeBuilder;
import com.pluralsight.recipe.dto.RecipeDTO;
import com.pluralsight.recipe.dto.mappers.RecipeMapper;
import com.pluralsight.recipe.entities.QRecipe;
import com.pluralsight.recipe.entities.Recipe;
import com.pluralsight.recipe.entities.RecipeType;
import com.pluralsight.recipe.exceptions.EntityWasNotFoundException;
import com.pluralsight.recipe.exceptions.InvalidParamException;
import com.pluralsight.recipe.repositories.RecipeRepository;
import com.pluralsight.recipe.services.RecipeService;
import com.pluralsight.recipe.utils.ExceptionMessageConstants;
import com.querydsl.core.types.Predicate;

@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	private RecipeRepository recipeRepository;

	@Override
	public List<RecipeDTO> listRecipesByLang(String lang) {

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
	public RecipeDTO getRecipeById(Long id) throws EntityWasNotFoundException {

		Optional<Recipe> oRecipe = recipeRepository.findById(id);

		if (oRecipe.isPresent()) {
			return RecipeMapper.MAPPER.mapToDTO(oRecipe.get());
		} else {
			throw new EntityWasNotFoundException(ExceptionMessageConstants.RECIPE_NOT_FOUND);
		}

	}

	@Override
	public RecipeDTO saveRecipe(Recipe recipe) {
		
		Recipe result = recipeRepository.save(recipe);
		
		return RecipeMapper.MAPPER.mapToDTO(result);
	}

	@Override
	public RecipeDTO updateRecipe(RecipeDTO requestDTO, RecipeType recipeType) {

		Recipe recipe = new Recipe();

		if (requestDTO.getId() != null) {

			Optional<Recipe> oRecipe = recipeRepository.findById(requestDTO.getId());

			if (oRecipe.isPresent()) {
				recipe = oRecipe.get();
			} else {
				throw new EntityWasNotFoundException(ExceptionMessageConstants.RECIPE_NOT_FOUND);
			}
		} else {
			throw new InvalidParamException(" Id ::" + ExceptionMessageConstants.PARAMETER_NULL);
		}

		String name = requestDTO.getName();
		if (name != null && !name.isEmpty() && !name.isBlank()) {
			recipe.setName(name);
		}

		String description = requestDTO.getDescription();
		if (description != null && !description.isEmpty() && !description.isBlank()) {
			recipe.setDescription(description);
		}

		recipe.setType(recipeType);

		Recipe updatedRecipe = recipeRepository.save(recipe);

		return RecipeMapper.MAPPER.mapToDTO(updatedRecipe);
	}

	@Override
	public void deleteRecipe(Long id) {
		recipeRepository.deleteById(id);
	}

	@Override
	public Recipe buildRecipe(RecipeDTO requestDTO, RecipeType recipeType) {
		
		RecipeBuilder builder = new RecipeBuilder();
		Recipe recipe = builder
				.addName(requestDTO.getName())
				.addDescription(requestDTO.getDescription())
				.addLang(requestDTO.getLang())
				.addType(recipeType)
				.build();
		
		return recipe;
	}

}
