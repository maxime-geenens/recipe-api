package com.pluralsight.recipe.dto.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import com.pluralsight.recipe.dto.IngredientDTO;
import com.pluralsight.recipe.entities.Ingredient;

@Mapper(componentModel = "spring", uses = { UnitReferenceMapper.class,
		IngredientReferenceMapper.class }, injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface IngredientMapper {

	@Mapping(source = "recipe.id", target = "recipeId")
	@Mapping(source = "unitReference", target = "unitRef")
	@Mapping(source = "ingredientReference", target = "ingredientRef")
	IngredientDTO mapToDTO(Ingredient entity);

	@Mapping(target = "recipe.id", source = "recipeId")
	@Mapping(target = "unitReference", source = "unitRef")
	@Mapping(target = "ingredientReference", source = "ingredientRef")
	Ingredient mapToEntity(IngredientDTO dto);

}
