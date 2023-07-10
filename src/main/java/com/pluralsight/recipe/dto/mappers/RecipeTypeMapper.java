package com.pluralsight.recipe.dto.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.pluralsight.recipe.dto.RecipeTypeDTO;
import com.pluralsight.recipe.entities.RecipeType;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface RecipeTypeMapper {

	RecipeTypeDTO mapToDTO(RecipeType entity);
	
	RecipeType mapToEntity(RecipeTypeDTO dto);
}
