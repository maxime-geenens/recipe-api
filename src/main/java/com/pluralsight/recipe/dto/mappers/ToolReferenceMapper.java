package com.pluralsight.recipe.dto.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.pluralsight.recipe.dto.ToolReferenceDTO;
import com.pluralsight.recipe.entities.ToolReference;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ToolReferenceMapper {

	@Mapping(source = "type.id", target = "typeId")
	ToolReferenceDTO mapToDTO(ToolReference entity);

	@Mapping(target = "type.id", source = "typeId")
	ToolReference mapToEntity(ToolReferenceDTO dto);

}
