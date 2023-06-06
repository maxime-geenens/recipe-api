package com.pluralsight.recipe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pluralsight.recipe.entities.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
