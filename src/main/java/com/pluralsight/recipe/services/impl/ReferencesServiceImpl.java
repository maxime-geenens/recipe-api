package com.pluralsight.recipe.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pluralsight.recipe.dto.IngredientReferenceDTO;
import com.pluralsight.recipe.dto.IngredientTypeDTO;
import com.pluralsight.recipe.dto.RecipeTypeDTO;
import com.pluralsight.recipe.dto.ToolReferenceDTO;
import com.pluralsight.recipe.dto.ToolTypeDTO;
import com.pluralsight.recipe.dto.UnitReferenceDTO;
import com.pluralsight.recipe.dto.mappers.IngredientReferenceMapper;
import com.pluralsight.recipe.dto.mappers.IngredientTypeMapper;
import com.pluralsight.recipe.dto.mappers.RecipeTypeMapper;
import com.pluralsight.recipe.dto.mappers.ToolReferenceMapper;
import com.pluralsight.recipe.dto.mappers.ToolTypeMapper;
import com.pluralsight.recipe.dto.mappers.UnitReferenceMapper;
import com.pluralsight.recipe.entities.IngredientReference;
import com.pluralsight.recipe.entities.IngredientType;
import com.pluralsight.recipe.entities.QIngredientReference;
import com.pluralsight.recipe.entities.QIngredientType;
import com.pluralsight.recipe.entities.QRecipeType;
import com.pluralsight.recipe.entities.QToolReference;
import com.pluralsight.recipe.entities.QToolType;
import com.pluralsight.recipe.entities.QUnitReference;
import com.pluralsight.recipe.entities.RecipeType;
import com.pluralsight.recipe.entities.ToolReference;
import com.pluralsight.recipe.entities.ToolType;
import com.pluralsight.recipe.entities.UnitReference;
import com.pluralsight.recipe.exceptions.EntityWasNotFoundException;
import com.pluralsight.recipe.repositories.IngredientReferenceRepository;
import com.pluralsight.recipe.repositories.IngredientTypeRepository;
import com.pluralsight.recipe.repositories.RecipeTypeRepository;
import com.pluralsight.recipe.repositories.ToolReferenceRepository;
import com.pluralsight.recipe.repositories.ToolTypeRepository;
import com.pluralsight.recipe.repositories.UnitReferenceRepository;
import com.pluralsight.recipe.services.ReferencesService;
import com.pluralsight.recipe.utils.ExceptionMessageConstants;
import com.querydsl.core.types.Predicate;

import jakarta.transaction.Transactional;

@Service
@Transactional
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
	private ToolTypeRepository toolTypeRepository;

	@Autowired
	private ToolReferenceRepository toolReferenceRepository;
	
	@Autowired
	private IngredientReferenceMapper ingredientRefMapper;
	
	@Autowired
	private UnitReferenceMapper unitRefMapper;
	
	@Autowired
	private RecipeTypeMapper recipeTypeMapper;
	
	@Autowired
	private IngredientTypeMapper ingredientTypeMapper;
	
	@Autowired
	private ToolTypeMapper toolTypeMapper;
	
	@Autowired
	private ToolReferenceMapper toolReferenceMapper;

	@Override
	public RecipeType getRecipeTypeById(Long id) {

		Optional<RecipeType> oRecipeType = recipeTypeRepository.findById(id);

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

		IngredientReference savedIngredientRef = ingredientReferenceRepository
				.save(ingredientRefMapper.mapToEntity(dto));

		return ingredientRefMapper.mapToDTO(savedIngredientRef);
	}

	@Override
	public List<RecipeTypeDTO> listRecipeTypesByLang(String lang) {

		QRecipeType qRecipeType = QRecipeType.recipeType;
		Predicate predicate = qRecipeType.lang.eq(lang);

		List<RecipeType> list = (List<RecipeType>) recipeTypeRepository.findAll(predicate);

		return list.stream().map(entity -> recipeTypeMapper.mapToDTO(entity)).toList();
	}

	@Override
	public List<IngredientTypeDTO> listIngredientTypesByLang(String lang) {

		QIngredientType qIngredientType = QIngredientType.ingredientType;
		Predicate predicate = qIngredientType.lang.eq(lang);

		List<IngredientType> list = (List<IngredientType>) ingredientTypeRepository.findAll(predicate);

		return list.stream().map(entity -> ingredientTypeMapper.mapToDTO(entity)).toList();
	}

	@Override
	public ToolReferenceDTO addToolRef(ToolReferenceDTO dto) {

		ToolReference savedToolRef = toolReferenceRepository.save(toolReferenceMapper.mapToEntity(dto));

		return toolReferenceMapper.mapToDTO(savedToolRef);
	}

	@Override
	public List<ToolReferenceDTO> listToolsRefByTypeAndLang(String type, String lang) {

		QToolReference qRef = QToolReference.toolReference;
		Predicate predicate = qRef.type.name.eq(type).and(qRef.lang.eq(lang));

		List<ToolReference> list = (List<ToolReference>) toolReferenceRepository.findAll(predicate);

		return list.stream().map(entity -> toolReferenceMapper.mapToDTO(entity)).toList();
	}

	@Override
	public List<ToolTypeDTO> listToolTypesByLang(String lang) {

		QToolType qType = QToolType.toolType;
		Predicate predicate = qType.lang.eq(lang);

		List<ToolType> list = (List<ToolType>) toolTypeRepository.findAll(predicate);

		return list.stream().map(entity -> toolTypeMapper.mapToDTO(entity)).toList();
	}

}
