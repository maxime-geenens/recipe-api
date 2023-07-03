package com.pluralsight.recipe.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pluralsight.recipe.dto.IngredientDTO;
import com.pluralsight.recipe.dto.mappers.IngredientMapper;
import com.pluralsight.recipe.entities.Ingredient;
import com.pluralsight.recipe.entities.QIngredient;
import com.pluralsight.recipe.repositories.IngredientRepository;
import com.pluralsight.recipe.repositories.RecipeRepository;
import com.pluralsight.recipe.repositories.UnitReferenceRepository;
import com.pluralsight.recipe.services.impl.IngredientServiceImpl;
import com.pluralsight.recipe.services.impl.ReferencesServiceImpl;
import com.pluralsight.recipe.utils.TestUtils;
import com.querydsl.core.types.Predicate;

@ExtendWith(MockitoExtension.class)
public class IngredientServiceTest {

	@InjectMocks
	private IngredientServiceImpl service;

	@InjectMocks
	private ReferencesServiceImpl refService;

	@Mock
	private IngredientRepository dao;

	@Mock
	private RecipeRepository recipeDao;

	@Mock
	private UnitReferenceRepository refDao;

	@Mock
	private IngredientMapper mapper;

	@DisplayName("JUnit Test for listIngredientsByRecipe method")
	@Test
	public void givenRecipeId_whenListIngredientsByRecipe_thenReturnIngredientDTOList() {

		List<Ingredient> list = TestUtils.buildIngredientList(5);
		Long recipeId = 1l;

		QIngredient qIngredient = QIngredient.ingredient;
		Predicate predicate = qIngredient.recipe.id.eq(recipeId);

		given(dao.findAll(predicate)).willReturn(list);

		List<IngredientDTO> result = service.listIngredientsByRecipe(recipeId);

		assertThat(result).isNotNull();
		assertThat(result).isNotEmpty();
		assertThat(result.size()).isEqualTo(5);
	}

	@DisplayName("JUnit Test for listIngredientsByRecipe method (negative scenario)")
	@Test
	public void givenRecipeId_whenListIngredientsByRecipe_thenReturnEmptyIngredientDTOList() {

		List<Ingredient> list = new ArrayList<>();
		Long recipeId = 1l;

		QIngredient qIngredient = QIngredient.ingredient;
		Predicate predicate = qIngredient.recipe.id.eq(recipeId);

		given(dao.findAll(predicate)).willReturn(list);

		List<IngredientDTO> result = service.listIngredientsByRecipe(recipeId);

		assertThat(result).isNotNull();
		assertThat(result).isEmpty();
		assertThat(result.size()).isEqualTo(0);
	}

	@DisplayName("JUnit Test for addIngredient method")
	@Test
	public void givenIngredientDTO_whenAddIngredient_thenReturnIngredientDTO() {

		IngredientDTO dto = TestUtils.buildIngredientDTO(true);
		Ingredient entity = TestUtils.buildIngredient();

		given(mapper.mapToEntity(dto)).willReturn(entity);
		given(dao.save(entity)).willReturn(entity);
		given(mapper.mapToDTO(entity)).willReturn(dto);

		IngredientDTO result = service.saveIngredient(dto);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(entity.getId());
		assertThat(result.getLang()).isEqualTo(entity.getLang());
		assertThat(result.getName()).isEqualTo(entity.getIngredientReference().getName());
		assertThat(result.getQuantity()).isEqualTo(entity.getQuantity());
		assertThat(result.getUnitRefId()).isEqualTo(entity.getUnitReference().getId());
		assertThat(result.getIngredientRefId()).isEqualTo(entity.getIngredientReference().getId());
		assertThat(result.getRecipeId()).isEqualTo(entity.getRecipe().getId());
	}

	@DisplayName("JUnit Test for saveIngredientList method")
	@Test
	public void givenIngredientDTOList_whenSaveIngredientList_thenReturnIngredientDTOList() {

		List<IngredientDTO> dtoList = TestUtils.buildIngredientDTOList(5, true);
		List<Ingredient> entityList = TestUtils.buildIngredientList(5);

		for (int i = 0; i < dtoList.size(); i++) {
			given(mapper.mapToEntity(dtoList.get(i))).willReturn(entityList.get(i));
		}
		given(dao.saveAll(entityList)).willReturn(entityList);

		List<IngredientDTO> result = service.saveIngredientList(dtoList);

		assertThat(result).isNotNull();
		assertThat(result).isNotEmpty();
		assertThat(result.size()).isEqualTo(5);
	}

	@DisplayName("JUnit Test for deleteIngredient method")
	@Test
	public void givenRecipeId_whenDeleteRecipe_thenNothing() {

		Long id = 1l;

		willDoNothing().given(dao).deleteById(id);

		service.deleteIngredient(id);

		verify(dao, times(1)).deleteById(id);
	}

}
