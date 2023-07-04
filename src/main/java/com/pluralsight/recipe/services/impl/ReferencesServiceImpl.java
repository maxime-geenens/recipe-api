package com.pluralsight.recipe.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pluralsight.recipe.builders.IngredientReferenceBuilder;
import com.pluralsight.recipe.dto.IngredientReferenceDTO;
import com.pluralsight.recipe.dto.UnitReferenceDTO;
import com.pluralsight.recipe.dto.mappers.IngredientReferenceMapper;
import com.pluralsight.recipe.dto.mappers.UnitReferenceMapper;
import com.pluralsight.recipe.entities.IngredientReference;
import com.pluralsight.recipe.entities.IngredientType;
import com.pluralsight.recipe.entities.QIngredientReference;
import com.pluralsight.recipe.entities.QRecipeType;
import com.pluralsight.recipe.entities.QUnitReference;
import com.pluralsight.recipe.entities.RecipeType;
import com.pluralsight.recipe.entities.UnitReference;
import com.pluralsight.recipe.exceptions.EntityWasNotFoundException;
import com.pluralsight.recipe.repositories.IngredientReferenceRepository;
import com.pluralsight.recipe.repositories.IngredientTypeRepository;
import com.pluralsight.recipe.repositories.RecipeTypeRepository;
import com.pluralsight.recipe.repositories.UnitReferenceRepository;
import com.pluralsight.recipe.services.ReferencesService;
import com.pluralsight.recipe.utils.ExceptionMessageConstants;
import com.querydsl.core.types.Predicate;

@Service
public class ReferencesServiceImpl implements ReferencesService {

	@Autowired
	private RecipeTypeRepository recipeTypeRepository;

	@Autowired
	private UnitReferenceRepository unitReferenceRepository;

	@Autowired
	private IngredientReferenceRepository ingredientReferenceRepository;

	@Autowired
	private IngredientTypeRepository ingredientTypeRepository;
	
	@Autowired
	private IngredientReferenceMapper ingredientRefMapper;
	
	@Autowired
	private UnitReferenceMapper unitRefMapper;

	@Override
	public RecipeType getRecipeTypeByCode(String typeCode) {

		QRecipeType qRecipeType = QRecipeType.recipeType;
		Predicate predicate = qRecipeType.code.eq(typeCode);

		Optional<RecipeType> oRecipeType = recipeTypeRepository.findOne(predicate);

		if (oRecipeType.isPresent()) {
			return oRecipeType.get();
		} else {
			throw new EntityWasNotFoundException(ExceptionMessageConstants.RECIPE_TYPE_CODE_NOT_FOUND);
		}
	}

	@Override
	public UnitReference getUnitReferenceById(Long unitRefId) {

		Optional<UnitReference> oUnitRef = unitReferenceRepository.findById(unitRefId);

		if (oUnitRef.isPresent()) {
			return oUnitRef.get();
		} else {
			throw new EntityWasNotFoundException(ExceptionMessageConstants.UNIT_REFERENCE_NOT_FOUND);
		}
	}

	@Override
	public IngredientReference getIngredientReferenceById(Long ingredientRefId) {

		Optional<IngredientReference> oIngredientRef = ingredientReferenceRepository.findById(ingredientRefId);

		if (oIngredientRef.isPresent()) {
			return oIngredientRef.get();
		} else {
			throw new EntityWasNotFoundException(ExceptionMessageConstants.INGREDIENT_REFERENCE_NOT_FOUND);
		}
	}

	@Override
	public List<IngredientReferenceDTO> listIngredientsRefByTypeAndLang(String type, String lang) {

		QIngredientReference qIngredientRef = QIngredientReference.ingredientReference;
		Predicate predicate = qIngredientRef.type.name.eq(type).and(qIngredientRef.lang.eq(lang));

		List<IngredientReference> ingredientList = (List<IngredientReference>) ingredientReferenceRepository
				.findAll(predicate);

		return ingredientList.stream().map(entity -> ingredientRefMapper.mapToDTO(entity)).toList();
	}

	@Override
	public List<UnitReferenceDTO> listUnitsByLang(String lang) {

		QUnitReference qUnitReference = QUnitReference.unitReference;
		Predicate predicate = qUnitReference.lang.eq(lang);

		List<UnitReference> unitList = (List<UnitReference>) unitReferenceRepository.findAll(predicate);

		return unitList.stream().map(entity -> unitRefMapper.mapToDTO(entity)).toList();
	}

	@Override
	public IngredientReferenceDTO addIngredientRef(IngredientReferenceDTO dto) {

		IngredientReferenceBuilder builder = new IngredientReferenceBuilder();

		IngredientType type = findIngredientTypeById(dto.getTypeId());

		IngredientReference ingredientRef = builder
				.addLang(dto.getLang())
				.addName(dto.getName())
				.addType(type)
				.build();

		IngredientReference savedIngredientRef = ingredientReferenceRepository.save(ingredientRef);

		return ingredientRefMapper.mapToDTO(savedIngredientRef);
	}

	private IngredientType findIngredientTypeById(Long id) {

		Optional<IngredientType> oType = ingredientTypeRepository.findById(id);

		if (oType.isPresent()) {
			return oType.get();
		} else {
			throw new EntityWasNotFoundException(ExceptionMessageConstants.INGREDIENT_TYPE_NOT_FOUND);
		}

	}

	@Override
	public IngredientReference findIngredientRefByCode(String code) {

		IngredientReference result = null;

		QIngredientReference qIngredientReference = QIngredientReference.ingredientReference;
		Predicate predicate = qIngredientReference.code.eq(code);

		Optional<IngredientReference> oIngredientRef = ingredientReferenceRepository.findOne(predicate);

		if (oIngredientRef.isPresent()) {
			result = oIngredientRef.get();
		}

		return result;
	}

}
