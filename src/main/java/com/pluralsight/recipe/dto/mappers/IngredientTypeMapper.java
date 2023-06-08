package com.pluralsight.recipe.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.pluralsight.recipe.dto.IngredientTypeDTO;
import com.pluralsight.recipe.entities.IngredientType;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface IngredientTypeMapper {

	IngredientTypeMapper MAPPER = Mappers.getMapper(IngredientTypeMapper.class);

	IngredientTypeDTO mapToDTO(IngredientType entity);

}
