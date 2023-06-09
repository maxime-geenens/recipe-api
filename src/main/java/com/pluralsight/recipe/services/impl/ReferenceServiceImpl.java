package com.pluralsight.recipe.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.pluralsight.recipe.entities.QRecipeType;
import com.pluralsight.recipe.entities.QUnitReference;
import com.pluralsight.recipe.entities.RecipeType;
import com.pluralsight.recipe.entities.UnitReference;
import com.pluralsight.recipe.exceptions.EntityNotFoundException;
import com.pluralsight.recipe.repositories.RecipeTypeRepository;
import com.pluralsight.recipe.repositories.UnitReferenceRepository;
import com.pluralsight.recipe.services.ReferenceService;
import com.pluralsight.recipe.utils.ExceptionMessageConstants;
import com.querydsl.core.types.Predicate;

public class ReferenceServiceImpl implements ReferenceService {

	@Autowired
	private RecipeTypeRepository recipeTypeRepository;

	@Autowired
	private UnitReferenceRepository unitReferenceRepository;

	@Override
	public RecipeType getRecipeTypeByCode(String typeCode) {

		RecipeType response = new RecipeType();

		QRecipeType qRecipeType = QRecipeType.recipeType;
		Predicate predicate = qRecipeType.code.eq(typeCode);

		Optional<RecipeType> oRecipeType = recipeTypeRepository.findOne(predicate);

		if (oRecipeType.isPresent()) {
			response = oRecipeType.get();
		} else {
			throw new EntityNotFoundException(ExceptionMessageConstants.RECIPE_TYPE_CODE_NOT_FOUND);
		}

		return response;
	}

	@Override
	public UnitReference getUnitReferenceById(Long unitRefId) {

		UnitReference response = new UnitReference();

		Optional<UnitReference> oUnitRef = unitReferenceRepository.findById(unitRefId);

		if (oUnitRef.isPresent()) {
			response = oUnitRef.get();
		} else {
			throw new EntityNotFoundException(ExceptionMessageConstants.UNIT_REFERENCE_NOT_FOUND);
		}

		return response;
	}

}
