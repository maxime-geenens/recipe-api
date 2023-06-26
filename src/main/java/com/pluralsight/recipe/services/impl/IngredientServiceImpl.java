package com.pluralsight.recipe.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pluralsight.recipe.builders.IngredientBuilder;
import com.pluralsight.recipe.dto.IngredientDTO;
import com.pluralsight.recipe.dto.mappers.IngredientMapper;
import com.pluralsight.recipe.entities.Ingredient;
import com.pluralsight.recipe.entities.IngredientReference;
import com.pluralsight.recipe.entities.QIngredient;
import com.pluralsight.recipe.entities.Recipe;
import com.pluralsight.recipe.entities.UnitReference;
import com.pluralsight.recipe.exceptions.EntityWasNotFoundException;
import com.pluralsight.recipe.exceptions.InvalidParamException;
import com.pluralsight.recipe.repositories.IngredientRepository;
import com.pluralsight.recipe.repositories.RecipeRepository;
import com.pluralsight.recipe.services.IngredientService;
import com.pluralsight.recipe.services.ReferencesService;
import com.pluralsight.recipe.utils.ExceptionMessageConstants;
import com.querydsl.core.types.Predicate;

@Service
public class IngredientServiceImpl implements IngredientService {
	
	@Autowired
	private ReferencesService referenceService;

	@Autowired
	private IngredientRepository ingredientRepository;

	@Autowired
	private RecipeRepository recipeRepository;

	@Override
	public List<IngredientDTO> listIngredientsByRecipe(Long id) {

		List<Ingredient> ingredientList = new ArrayList<>();

		QIngredient qIngredient = QIngredient.ingredient;
		Predicate predicate = qIngredient.recipe.id.eq(id);

		ingredientList = (List<Ingredient>) ingredientRepository.findAll(predicate);

		return ingredientList.stream().map((entity) -> IngredientMapper.MAPPER.mapToDTO(entity))
				.collect(Collectors.toList());
	}

	@Override
	public IngredientDTO addIngredient(IngredientDTO dto) {
		
		IngredientBuilder builder = new IngredientBuilder();

		Recipe recipe = new Recipe();
		Optional<Recipe> oRecipe = recipeRepository.findById(dto.getRecipeId());
		if (oRecipe.isPresent()) {
			recipe = oRecipe.get();
		} else {
			throw new EntityWasNotFoundException(ExceptionMessageConstants.RECIPE_NOT_FOUND);
		}
		
		UnitReference unitRef = referenceService.getUnitReferenceById(dto.getUnitRefId());
		IngredientReference ingredientRef = referenceService.getIngredientReferenceById(dto.getIngredientRefId());
		
		
		Ingredient ingredient = builder
				.addLang(dto.getLang())
				.addQuantity(dto.getQuantity())
				.addUnit(unitRef)
				.addIngredient(ingredientRef)
				.addRecipe(recipe)
				.build();
		
		Ingredient savedIngredient = ingredientRepository.save(ingredient);
		
		return IngredientMapper.MAPPER.mapToDTO(savedIngredient);
	}

	@Override
	public List<IngredientDTO> createIngredientList(List<IngredientDTO> ingredientDTOList) {

		List<Ingredient> ingredientList = new ArrayList<>();

		for (IngredientDTO dto : ingredientDTOList) {
			ingredientList.add(buildIngredient(dto));
		}

		List<Ingredient> ingredientListResult = ingredientRepository.saveAll(ingredientList);
		return ingredientListResult.stream().map((entity) -> IngredientMapper.MAPPER.mapToDTO(entity))
				.collect(Collectors.toList());

	}

	private Ingredient buildIngredient(IngredientDTO dto) {

		IngredientBuilder builder = new IngredientBuilder();

		UnitReference unit = referenceService.getUnitReferenceById(dto.getUnitRefId());
		IngredientReference ingredientRef = referenceService.getIngredientReferenceById(dto.getIngredientRefId());

		Recipe recipe = new Recipe();
		Optional<Recipe> oRecipe = recipeRepository.findById(dto.getRecipeId());

		if (oRecipe.isPresent()) {
			recipe = oRecipe.get();
		} else {
			throw new EntityWasNotFoundException(ExceptionMessageConstants.RECIPE_NOT_FOUND);
		}

		Ingredient ingredient = builder
				.addLang(dto.getLang())
				.addQuantity(dto.getQuantity())
				.addIngredient(ingredientRef)
				.addUnit(unit)
				.addRecipe(recipe)
				.build();

		return ingredient;
	}

	@Override
	public List<IngredientDTO> updateIngredientList(List<IngredientDTO> requestDTO) {

		List<Ingredient> ingredientList = new ArrayList<>();

		for (IngredientDTO dto : requestDTO) {

			Ingredient ingredient = new Ingredient();

			if (dto.getId() != null) {

				Optional<Ingredient> oIngredient = ingredientRepository.findById(dto.getId());

				if (oIngredient.isPresent()) {
					ingredient = oIngredient.get();
				} else {
					throw new EntityWasNotFoundException(ExceptionMessageConstants.INGREDIENT_NOT_FOUND);
				}
			} else {
				throw new InvalidParamException(" Id ::" + ExceptionMessageConstants.PARAMETER_NULL);
			}

			Double quantity = dto.getQuantity();
			if (quantity != null) {
				ingredient.setQuantity(quantity);
			}

			Long unitRefId = dto.getUnitRefId();
			if (unitRefId != null) {
				UnitReference unitRef = referenceService.getUnitReferenceById(unitRefId);
				ingredient.setUnitReference(unitRef);
			}

			ingredientList.add(ingredient);
		}

		List<Ingredient> updatedIngredientList = ingredientRepository.saveAll(ingredientList);

		return updatedIngredientList.stream().map((entity) -> IngredientMapper.MAPPER.mapToDTO(entity))
				.collect(Collectors.toList());
	}

	@Override
	public void deleteIngredient(Long id) {
		ingredientRepository.deleteById(id);
	}
}
