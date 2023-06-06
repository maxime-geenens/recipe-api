package com.pluralsight.recipe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pluralsight.recipe.entities.RecipeType;

public interface RecipeTypeRepository extends JpaRepository<RecipeType, Long> {

}
