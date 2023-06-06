package com.pluralsight.recipe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pluralsight.recipe.entities.UnitReference;

public interface UnitReferenceRepository extends JpaRepository<UnitReference, Long> {

}
