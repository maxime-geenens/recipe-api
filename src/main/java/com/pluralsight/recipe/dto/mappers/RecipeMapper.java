package com.pluralsight.recipe.dto.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.pluralsight.recipe.dto.RecipeDTO;
import com.pluralsight.recipe.entities.Recipe;

@Mapper(componentModel = "spring", uses = {
		RecipeTypeMapper.class }, injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface RecipeMapper {

	RecipeDTO mapToDTO(Recipe entity);

	Recipe mapToEntity(RecipeDTO dto);
}
