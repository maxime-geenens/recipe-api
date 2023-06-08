package com.pluralsight.recipe.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.pluralsight.recipe.dto.RecipeTypeDTO;
import com.pluralsight.recipe.entities.RecipeType;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface RecipeTypeMapper {

	RecipeTypeMapper MAPPER = Mappers.getMapper(RecipeTypeMapper.class);

	RecipeTypeDTO mapToDTO(RecipeType entity);
}
