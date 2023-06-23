package com.pluralsight.recipe.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.pluralsight.recipe.dto.IngredientReferenceDTO;
import com.pluralsight.recipe.entities.IngredientReference;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface IngredientReferenceMapper {

	IngredientReferenceMapper MAPPER = Mappers.getMapper(IngredientReferenceMapper.class);

	@Mapping(source = "type.name", target = "type")
	@Mapping(source = "type.id", target = "typeId")
	IngredientReferenceDTO mapToDTO(IngredientReference entity);

}
