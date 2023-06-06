package com.pluralsight.recipe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pluralsight.recipe.entities.IngredientReference;

public interface IngredientReferenceRepository extends JpaRepository<IngredientReference, Long> {

}
