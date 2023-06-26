package com.pluralsight.recipe.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.pluralsight.recipe.dto.IngredientDTO;
import com.pluralsight.recipe.entities.Ingredient;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface IngredientMapper {

	IngredientMapper MAPPER = Mappers.getMapper(IngredientMapper.class);

	@Mapping(source = "ingredientReference.name", target = "name")
	@Mapping(source = "ingredientReference.type.name", target = "type")
	@Mapping(source = "unitReference.symbol", target = "unit")
	@Mapping(source = "unitReference.id", target = "unitRefId")
	@Mapping(source = "ingredientReference.id", target = "ingredientRefId")
	@Mapping(source = "recipe.id", target = "recipeId")
	IngredientDTO mapToDTO(Ingredient entity);

}
