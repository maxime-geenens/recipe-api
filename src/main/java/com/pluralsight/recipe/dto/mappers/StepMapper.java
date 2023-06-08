package com.pluralsight.recipe.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.pluralsight.recipe.dto.StepDTO;
import com.pluralsight.recipe.entities.Step;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface StepMapper {

	StepMapper MAPPER = Mappers.getMapper(StepMapper.class);

	@Mapping(source = "recipe.id", target = "recipeId")
	StepDTO mapToDTO(Step entity);

}
