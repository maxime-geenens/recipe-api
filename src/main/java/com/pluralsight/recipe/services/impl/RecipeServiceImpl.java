package com.pluralsight.recipe.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pluralsight.recipe.builders.RecipeBuilder;
import com.pluralsight.recipe.dto.IngredientDTO;
import com.pluralsight.recipe.dto.RecipeDTO;
import com.pluralsight.recipe.dto.RecipeDetailDTO;
import com.pluralsight.recipe.dto.StepDTO;
import com.pluralsight.recipe.dto.mappers.RecipeMapper;
import com.pluralsight.recipe.entities.QRecipe;
import com.pluralsight.recipe.entities.Recipe;
import com.pluralsight.recipe.entities.RecipeType;
import com.pluralsight.recipe.exceptions.EntityNotFoundException;
import com.pluralsight.recipe.exceptions.InvalidParameterException;
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

		if (oRecipe.isPresent()) {
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
	public RecipeDTO createRecipe(RecipeDTO requestDTO) {

		RecipeType recipeType = referenceService.getRecipeTypeByCode(requestDTO.getTypeCode());

		RecipeBuilder builder = new RecipeBuilder();
		Recipe recipe = builder
				.addName(requestDTO.getName())
				.addDescription(requestDTO.getDescription())
				.addLang(requestDTO.getLang())
				.addType(recipeType)
				.build();

		Recipe recipeResult = recipeRepository.save(recipe);
		
		return RecipeMapper.MAPPER.mapToDTO(recipeResult);
	}

	@Override
	public RecipeDTO updateRecipe(RecipeDTO requestDTO) {

		Recipe recipe = new Recipe();

		if (requestDTO.getId() != null) {

			Optional<Recipe> oRecipe = recipeRepository.findById(requestDTO.getId());

			if (oRecipe.isPresent()) {
				recipe = oRecipe.get();
			} else {
				throw new EntityNotFoundException(ExceptionMessageConstants.RECIPE_NOT_FOUND);
			}
		} else {
			throw new InvalidParameterException(" Id ::" + ExceptionMessageConstants.PARAMETER_NULL);
		}

		String name = requestDTO.getName();
		if (name != null && !name.isEmpty() && !name.isBlank()) {
			recipe.setName(name);
		}

		String description = requestDTO.getDescription();
		if (description != null && !description.isEmpty() && !description.isBlank()) {
			recipe.setDescription(description);
		}

		String typeCode = requestDTO.getTypeCode();
		if (typeCode != null && !typeCode.isEmpty() && !typeCode.isBlank()) {
			recipe.setType(referenceService.getRecipeTypeByCode(typeCode));
		}

		Recipe updatedRecipe = recipeRepository.save(recipe);

		return RecipeMapper.MAPPER.mapToDTO(updatedRecipe);
	}

	@Override
	public void deleteRecipe(Long id) {
		recipeRepository.deleteById(id);
	}

}
