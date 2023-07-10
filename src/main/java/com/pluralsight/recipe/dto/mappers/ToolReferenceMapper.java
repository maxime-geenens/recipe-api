package com.pluralsight.recipe.dto.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.pluralsight.recipe.dto.ToolReferenceDTO;
import com.pluralsight.recipe.entities.ToolReference;

@Mapper(componentModel = "spring", uses = {
		ToolTypeMapper.class }, injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ToolReferenceMapper {

	ToolReferenceDTO mapToDTO(ToolReference entity);

	ToolReference mapToEntity(ToolReferenceDTO dto);

}
