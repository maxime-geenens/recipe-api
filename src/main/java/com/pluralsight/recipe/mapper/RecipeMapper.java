package com.pluralsight.recipe.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.pluralsight.recipe.dto.RecipeDTO;
import com.pluralsight.recipe.entities.Recipe;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, uses = { RecipeTypeMapper.class })
public interface RecipeMapper {

	RecipeMapper MAPPER = Mappers.getMapper(RecipeMapper.class);

	RecipeDTO mapToDTO(Recipe entity);

	Recipe mapToEntity(RecipeDTO dto);
}
