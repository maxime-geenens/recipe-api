package com.pluralsight.recipe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.pluralsight.recipe.entities.LangReference;

@Repository
public interface LangReferenceRepository
		extends JpaRepository<LangReference, Long>, QuerydslPredicateExecutor<LangReference> {

}
