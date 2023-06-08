package com.pluralsight.recipe.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.pluralsight.recipe.dto.UnitReferenceDTO;
import com.pluralsight.recipe.entities.UnitReference;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UnitReferenceMapper {

	UnitReferenceMapper MAPPER = Mappers.getMapper(UnitReferenceMapper.class);

	UnitReferenceDTO mapToDTO(UnitReference entity);
}
