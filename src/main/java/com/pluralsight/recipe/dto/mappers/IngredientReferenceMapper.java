package com.pluralsight.recipe.dto.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.pluralsight.recipe.dto.IngredientReferenceDTO;
import com.pluralsight.recipe.entities.IngredientReference;

@Mapper(componentModel = "spring", uses = {
		IngredientTypeMapper.class }, injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface IngredientReferenceMapper {

	IngredientReferenceDTO mapToDTO(IngredientReference entity);

	IngredientReference mapToEntity(IngredientReferenceDTO dto);

}
