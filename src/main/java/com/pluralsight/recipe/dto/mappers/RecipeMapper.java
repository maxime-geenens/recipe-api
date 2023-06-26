package com.pluralsight.recipe.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.pluralsight.recipe.dto.RecipeDTO;
import com.pluralsight.recipe.entities.Recipe;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface RecipeMapper {

	RecipeMapper MAPPER = Mappers.getMapper(RecipeMapper.class);

	@Mapping(source = "type.code", target = "typeCode")
	RecipeDTO mapToDTO(Recipe entity);
}
