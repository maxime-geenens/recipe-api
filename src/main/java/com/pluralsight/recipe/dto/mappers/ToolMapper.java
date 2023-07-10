package com.pluralsight.recipe.dto.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.pluralsight.recipe.dto.ToolDTO;
import com.pluralsight.recipe.entities.Tool;

@Mapper(componentModel = "spring", uses = {
		ToolReferenceMapper.class }, injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ToolMapper {

	@Mapping(source = "recipe.id", target = "recipeId")
	@Mapping(source = "toolReference", target = "toolRef")
	ToolDTO mapToDTO(Tool entity);

	@Mapping(target = "recipe.id", source = "recipeId")
	@Mapping(target = "toolReference", source = "toolRef")
	Tool mapToEntity(ToolDTO dto);
}
