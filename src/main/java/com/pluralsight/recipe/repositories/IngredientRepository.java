package com.pluralsight.recipe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.pluralsight.recipe.entities.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long>, QuerydslPredicateExecutor<Ingredient> {

}
