package com.pluralsight.recipe.dto.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.pluralsight.recipe.dto.UnitReferenceDTO;
import com.pluralsight.recipe.entities.UnitReference;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UnitReferenceMapper {

	UnitReferenceDTO mapToDTO(UnitReference entity);
}
