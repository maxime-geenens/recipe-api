package com.pluralsight.recipe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pluralsight.recipe.entities.Step;

public interface StepRepository extends JpaRepository<Step, Long> {

}
