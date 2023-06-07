package com.pluralsight.recipe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.pluralsight.recipe.entities.UnitReference;

public interface UnitReferenceRepository
		extends JpaRepository<UnitReference, Long>, QuerydslPredicateExecutor<UnitReference> {

}
