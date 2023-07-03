package com.pluralsight.recipe.dto.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import com.pluralsight.recipe.dto.RecipeDTO;
import com.pluralsight.recipe.entities.Recipe;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface RecipeMapper {

	@Mapping(source = "type.id", target = "typeId")
	RecipeDTO mapToDTO(Recipe entity);

	@Mapping(target = "type.id", source = "typeId")
	@Mapping(target = "code", ignore = true)
	Recipe mapToEntity(RecipeDTO dto);
}
