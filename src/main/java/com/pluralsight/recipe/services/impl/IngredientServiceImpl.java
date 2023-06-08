package com.pluralsight.recipe.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pluralsight.recipe.dto.IngredientDTO;
import com.pluralsight.recipe.dto.mappers.IngredientMapper;
import com.pluralsight.recipe.entities.Ingredient;
import com.pluralsight.recipe.entities.QIngredient;
import com.pluralsight.recipe.repositories.IngredientRepository;
import com.pluralsight.recipe.services.IngredientService;
import com.querydsl.core.types.Predicate;

@Service
public class IngredientServiceImpl implements IngredientService {

	@Autowired
	private IngredientRepository ingredientRepository;

	@Override
	public List<IngredientDTO> listIngredientsByRecipe(Long id) {

		List<Ingredient> ingredientList = new ArrayList<>();

		QIngredient qIngredient = QIngredient.ingredient;
		Predicate predicate = qIngredient.recipe.id.eq(id);

		ingredientList = (List<Ingredient>) ingredientRepository.findAll(predicate);

		return ingredientList.stream().map((entity) -> IngredientMapper.MAPPER.mapToDTO(entity))
				.collect(Collectors.toList());
	}

}
