package com.pluralsight.recipe.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pluralsight.recipe.dto.RecipeDTO;
import com.pluralsight.recipe.dto.mappers.RecipeMapper;
import com.pluralsight.recipe.entities.QRecipe;
import com.pluralsight.recipe.entities.Recipe;
import com.pluralsight.recipe.exceptions.EntityWasNotFoundException;
import com.pluralsight.recipe.repositories.RecipeRepository;
import com.pluralsight.recipe.services.impl.RecipeServiceImpl;
import com.pluralsight.recipe.utils.ExceptionMessageConstants;
import com.pluralsight.recipe.utils.TestUtils;
import com.querydsl.core.types.Predicate;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {

	@InjectMocks
	private RecipeServiceImpl service;

	@Mock
	private RecipeRepository dao;

	@Mock
	private RecipeMapper mapper;

	@DisplayName("JUnit Test for listRecipesByLang method")
	@Test
	void givenLang_whenListRecipesByLang_thenReturnRecipeDTOList() {

		List<Recipe> list = TestUtils.buildRecipeList(5);

		QRecipe qRecipe = QRecipe.recipe;
		Predicate predicate = qRecipe.lang.eq("FR");

		given(dao.findAll(predicate)).willReturn(list);

		List<RecipeDTO> result = service.listRecipesByLang("FR");

		assertThat(result)
			.isNotNull()
			.isNotEmpty()
			.hasSize(5);
	}

	@DisplayName("JUnit Test for listRecipesByLang method (negative scenario)")
	@Test
	void givenLang_whenListRecipesByLang_thenReturnEmptyRecipeDTOList() {

		List<Recipe> list = new ArrayList<>();

		QRecipe qRecipe = QRecipe.recipe;
		Predicate predicate = qRecipe.lang.eq("FR");

		given(dao.findAll(predicate)).willReturn(list);

		List<RecipeDTO> result = service.listRecipesByLang("FR");

		assertThat(result)
			.isNotNull()
			.isEmpty();
	}

	@DisplayName("JUnit Test for getRecipeDTOById method")
	@Test
	void givenRecipeId_whenGetRecipeDTOById_thenReturnRecipe() {

		Recipe entity = TestUtils.buildRecipe();
		RecipeDTO dto = TestUtils.buildRecipeDTO(true);

		given(dao.findById(entity.getId())).willReturn(Optional.of(entity));
		given(mapper.mapToDTO(entity)).willReturn(dto);
		
		RecipeDTO result = service.getRecipeDTOById(entity.getId());

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(entity.getId());
		assertThat(result.getDescription()).isEqualTo("Description1");
		assertThat(result.getLang()).isEqualTo(entity.getLang());
		assertThat(result.getName()).isEqualTo("Name1");
		assertThat(result.getType().getId()).isEqualTo(entity.getType().getId());
	}

	@DisplayName("JUnit Test for getRecipeDTOById method which throws exception")
	@Test
	void givenRecipeId_whenGetRecipeDTOById_thenThrowException() {

		Throwable exception = assertThrows(EntityWasNotFoundException.class, () -> service.getRecipeDTOById(1l));
		assertThat(exception.getMessage()).isEqualTo(ExceptionMessageConstants.RECIPE_NOT_FOUND);
	}

	@DisplayName("JUnit Test for getRecipeById method")
	@Test
	void givenRecipeId_whenGetRecipeById_thenReturnRecipe() {

		Recipe entity = TestUtils.buildRecipe();

		given(dao.findById(entity.getId())).willReturn(Optional.of(entity));

		Recipe result = service.getRecipeById(entity.getId());

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(entity.getId());
		assertThat(result.getDescription()).isEqualTo(entity.getDescription());
		assertThat(result.getLang()).isEqualTo(entity.getLang());
		assertThat(result.getName()).isEqualTo(entity.getName());
		assertThat(result.getType().getId()).isEqualTo(entity.getType().getId());
	}

	@DisplayName("JUnit Test for getRecipeById method which throws exception")
	@Test
	void givenRecipeId_whenGetRecipeById_thenThrowException() {

		Throwable exception = assertThrows(EntityWasNotFoundException.class, () -> service.getRecipeById(1l));
		assertThat(exception.getMessage()).isEqualTo(ExceptionMessageConstants.RECIPE_NOT_FOUND);
	}

	@DisplayName("JUnit Test for saveRecipe method")
	@Test
	void givenRecipeDTO_whenSaveRecipe_theReturnRecipeDTO() {

		Recipe entity = TestUtils.buildRecipe();
		Recipe savedEntity = TestUtils.buildRecipe();
		RecipeDTO dto = TestUtils.buildRecipeDTO(true);

		given(mapper.mapToEntity(dto)).willReturn(entity);
		given(dao.save(entity)).willReturn(savedEntity);
		given(mapper.mapToDTO(savedEntity)).willReturn(dto);

		RecipeDTO result = service.saveRecipe(dto);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(savedEntity.getId());
		assertThat(result.getDescription()).isEqualTo("Description1");
		assertThat(result.getLang()).isEqualTo(savedEntity.getLang());
		assertThat(result.getName()).isEqualTo("Name1");
		assertThat(result.getType().getId()).isEqualTo(savedEntity.getType().getId());
	}

	@DisplayName("JUnit Test for deleteRecipe method")
	@Test
	void givenRecipeId_whenDeleteRecipe_thenNothing() {

		Long recipeId = 1l;

		willDoNothing().given(dao).deleteById(recipeId);

		service.deleteRecipe(recipeId);

		verify(dao, times(1)).deleteById(recipeId);
	}

}
