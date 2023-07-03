package com.pluralsight.recipe.dto.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import com.pluralsight.recipe.dto.IngredientDTO;
import com.pluralsight.recipe.entities.Ingredient;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface IngredientMapper {

	@Mapping(source = "ingredientReference.name", target = "name")
	@Mapping(source = "unitReference.id", target = "unitRefId")
	@Mapping(source = "ingredientReference.id", target = "ingredientRefId")
	@Mapping(source = "recipe.id", target = "recipeId")
	IngredientDTO mapToDTO(Ingredient entity);

	@Mapping(target = "unitReference.id", source = "unitRefId")
	@Mapping(target = "ingredientReference.id", source = "ingredientRefId")
	@Mapping(target = "recipe.id", source = "recipeId")
	Ingredient mapToEntity(IngredientDTO dto);

}
