package com.pluralsight.recipe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pluralsight.recipe.entities.IngredientType;

public interface IngredientTypeRepository extends JpaRepository<IngredientType, Long> {

}
