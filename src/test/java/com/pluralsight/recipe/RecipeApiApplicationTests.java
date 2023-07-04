package com.pluralsight.recipe;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pluralsight.recipe.controllers.RecipeController;

@SpringBootTest
class RecipeApiApplicationTests {
	
	@Autowired
	private RecipeController recipeController;

	@Test
	void contextLoads() {
		assertThat(recipeController).isNotNull();
	}

}
