package com.pluralsight.recipe.dto.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.pluralsight.recipe.dto.ToolDTO;
import com.pluralsight.recipe.entities.Tool;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ToolMapper {

	@Mapping(source = "toolReference.id", target = "toolRefId")
	@Mapping(source = "recipe.id", target = "recipeId")
	ToolDTO mapToDTO(Tool entity);

	@Mapping(target = "toolReference.id", source = "toolRefId")
	@Mapping(target = "recipe.id", source = "recipeId")
	Tool mapToEntity(ToolDTO dto);
}
