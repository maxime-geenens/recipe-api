package com.pluralsight.recipe.dto.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.pluralsight.recipe.dto.StepDTO;
import com.pluralsight.recipe.entities.Step;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface StepMapper {

	@Mapping(source = "recipe.id", target = "recipeId")
	StepDTO mapToDTO(Step entity);

	@Mapping(target = "recipe.id", source = "recipeId")
	Step mapToEntity(StepDTO dto);

}
