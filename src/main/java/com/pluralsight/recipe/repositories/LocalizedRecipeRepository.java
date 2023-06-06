package com.pluralsight.recipe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pluralsight.recipe.entities.LocalizedRecipe;
import com.pluralsight.recipe.entities.LocalizedRecipeId;

public interface LocalizedRecipeRepository extends JpaRepository<LocalizedRecipe, LocalizedRecipeId> {

}
