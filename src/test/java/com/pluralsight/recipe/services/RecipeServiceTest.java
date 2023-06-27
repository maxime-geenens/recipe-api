package com.pluralsight.recipe.services;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pluralsight.recipe.repositories.RecipeRepository;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceTest {
	
	@InjectMocks
	RecipeService service;
	
	@Mock
	RecipeRepository dao;
	
	
	

}
