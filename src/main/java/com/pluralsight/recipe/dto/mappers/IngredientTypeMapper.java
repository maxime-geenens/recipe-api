package com.pluralsight.recipe.dto.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.pluralsight.recipe.dto.IngredientTypeDTO;
import com.pluralsight.recipe.entities.IngredientType;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface IngredientTypeMapper {

	IngredientTypeDTO mapToDTO(IngredientType entity);

	IngredientType mapToEntity(IngredientTypeDTO dto);

}
