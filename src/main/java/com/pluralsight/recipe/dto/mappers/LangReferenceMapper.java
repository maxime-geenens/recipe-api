package com.pluralsight.recipe.dto.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.pluralsight.recipe.dto.LangReferenceDTO;
import com.pluralsight.recipe.entities.LangReference;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface LangReferenceMapper {

	LangReferenceDTO mapToDTO(LangReference entity);

	LangReference mapToEntity(LangReferenceDTO dto);

}
