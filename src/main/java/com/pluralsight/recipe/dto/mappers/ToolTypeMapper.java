package com.pluralsight.recipe.dto.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.pluralsight.recipe.dto.ToolTypeDTO;
import com.pluralsight.recipe.entities.ToolType;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ToolTypeMapper {

	ToolTypeDTO mapToDTO(ToolType entity);

	ToolType mapToEntity(ToolTypeDTO dto);

}
