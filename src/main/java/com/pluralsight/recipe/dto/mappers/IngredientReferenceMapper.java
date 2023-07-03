package com.pluralsight.recipe.dto.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import com.pluralsight.recipe.dto.IngredientReferenceDTO;
import com.pluralsight.recipe.entities.IngredientReference;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface IngredientReferenceMapper {

	@Mapping(source = "type.id", target = "typeId")
	IngredientReferenceDTO mapToDTO(IngredientReference entity);

}
