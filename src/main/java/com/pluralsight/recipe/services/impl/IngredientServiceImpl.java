package com.pluralsight.recipe.services.impl;

import java.util.ArrayList;
import java.util.List;

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
	
	@Autowired
	private IngredientMapper mapper;

	@Override
	public List<IngredientDTO> listIngredientsByRecipe(Long id) {

		QIngredient qIngredient = QIngredient.ingredient;
		Predicate predicate = qIngredient.recipe.id.eq(id);

		List<Ingredient> ingredientList = (List<Ingredient>) ingredientRepository.findAll(predicate);

		return ingredientList.stream().map(entity -> mapper.mapToDTO(entity)).toList();
	}

	@Override
	public IngredientDTO saveIngredient(IngredientDTO dto) {

		Ingredient savedIngredient = ingredientRepository.save(mapper.mapToEntity(dto));

		return mapper.mapToDTO(savedIngredient);
	}

	@Override
	public List<IngredientDTO> saveIngredientList(List<IngredientDTO> ingredientDTOList) {

		List<Ingredient> ingredientList = new ArrayList<>();

		for (IngredientDTO dto : ingredientDTOList) {
			ingredientList.add(mapper.mapToEntity(dto));
		}

		List<Ingredient> ingredientListResult = ingredientRepository.saveAll(ingredientList);

		return ingredientListResult.stream().map(entity -> mapper.mapToDTO(entity)).toList();
	}

	@Override
	public void deleteIngredient(Long id) {
		ingredientRepository.deleteById(id);
	}
}
