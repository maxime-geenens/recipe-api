package com.pluralsight.recipe.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pluralsight.recipe.dto.IngredientReferenceDTO;
import com.pluralsight.recipe.dto.UnitReferenceDTO;
import com.pluralsight.recipe.dto.mappers.IngredientReferenceMapper;
import com.pluralsight.recipe.dto.mappers.UnitReferenceMapper;
import com.pluralsight.recipe.entities.IngredientReference;
import com.pluralsight.recipe.entities.IngredientType;
import com.pluralsight.recipe.entities.QIngredientReference;
import com.pluralsight.recipe.entities.QRecipeType;
import com.pluralsight.recipe.entities.QUnitReference;
import com.pluralsight.recipe.entities.RecipeType;
import com.pluralsight.recipe.entities.UnitReference;
import com.pluralsight.recipe.repositories.IngredientReferenceRepository;
import com.pluralsight.recipe.repositories.IngredientTypeRepository;
import com.pluralsight.recipe.repositories.RecipeTypeRepository;
import com.pluralsight.recipe.repositories.UnitReferenceRepository;
import com.pluralsight.recipe.services.impl.ReferencesServiceImpl;
import com.pluralsight.recipe.utils.TestUtils;
import com.querydsl.core.types.Predicate;

@ExtendWith(MockitoExtension.class)
public class ReferencesServiceTest {

	@InjectMocks
	private ReferencesServiceImpl service;

	@Mock
	private RecipeTypeRepository recipeTypeRepository;

	@Mock
	private UnitReferenceRepository unitRefRepository;

	@Mock
	private IngredientReferenceRepository ingredientRefRepository;

	@Mock
	private IngredientTypeRepository ingredientTypeRepository;

	@Mock
	private IngredientReferenceMapper ingredientRefMapper;

	@Mock
	private UnitReferenceMapper unitRefMapper;

	@DisplayName("JUnit Test for getRecipeTypeByCode method")
	@Test
	public void givenTypeCode_whenGetRecipeTypeByCode_thenReturnRecipeType() {

		RecipeType entity = TestUtils.buildRecipeType();

		QRecipeType qRecipeType = QRecipeType.recipeType;
		Predicate predicate = qRecipeType.code.eq("FRName");

		given(recipeTypeRepository.findOne(predicate)).willReturn(Optional.of(entity));

		RecipeType result = service.getRecipeTypeByCode("FRName");

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(entity.getId());
		assertThat(result.getName()).isEqualTo(entity.getName());
		assertThat(result.getLang()).isEqualTo(entity.getLang());
		assertThat(result.getCode()).isEqualTo(entity.getCode());
	}

	@DisplayName("JUnit Test for getUnitReferenceById method")
	@Test
	public void givenId_whenGetUnitReferenceById_thenReturnUnitReference() {

		UnitReference entity = TestUtils.buildUnitReference();

		given(unitRefRepository.findById(1l)).willReturn(Optional.of(entity));

		UnitReference result = service.getUnitReferenceById(1l);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(entity.getId());
		assertThat(result.getName()).isEqualTo(entity.getName());
		assertThat(result.getLang()).isEqualTo(entity.getLang());
		assertThat(result.getSymbol()).isEqualTo(entity.getSymbol());
		assertThat(result.getDescription()).isEqualTo(entity.getDescription());
		assertThat(result.getCode()).isEqualTo(entity.getCode());
	}

	@DisplayName("JUnit Test for getIngredientReferenceById method")
	@Test
	public void givenId_whenGetIngredientReferenceById_thenReturnIngredientReference() {

		IngredientType type = TestUtils.buildIngredientType();
		IngredientReference entity = TestUtils.buildIngredientReference(true, type);

		given(ingredientRefRepository.findById(1l)).willReturn(Optional.of(entity));

		IngredientReference result = service.getIngredientReferenceById(1l);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(entity.getId());
		assertThat(result.getName()).isEqualTo(entity.getName());
		assertThat(result.getLang()).isEqualTo(entity.getLang());
		assertThat(result.getCode()).isEqualTo(entity.getCode());
	}

	@DisplayName("JUnit Test for listIngredientsByTypeAndLang method")
	@Test
	public void givenTypeAndLang_whenListIngredientsByTypeAndLang_thenReturnIngredientReferenceDTOList() {
		
		List<IngredientReference> ingredientList = TestUtils.buildIngredientReferenceList(5);

		QIngredientReference qIngredientRef = QIngredientReference.ingredientReference;
		Predicate predicate = qIngredientRef.type.name.eq("Name").and(qIngredientRef.lang.eq("FR"));

		given(ingredientRefRepository.findAll(predicate)).willReturn(ingredientList);

		List<IngredientReferenceDTO> result = service.listIngredientsRefByTypeAndLang("Name", "FR");

		assertThat(result).isNotNull();
		assertThat(result).isNotEmpty();
		assertThat(result.size()).isEqualTo(5);
	}

	@DisplayName("JUnit Test for listUnitsByLang method")
	@Test
	public void givenLang_whenListUnitsByLang_thenReturnUnitReferenceDTOList() {

		List<UnitReference> list = TestUtils.buildUnitReferenceList(5);

		QUnitReference qUnitReference = QUnitReference.unitReference;
		Predicate predicate = qUnitReference.lang.eq("FR");

		given(unitRefRepository.findAll(predicate)).willReturn(list);

		List<UnitReferenceDTO> result = service.listUnitsByLang("FR");

		assertThat(result).isNotNull();
		assertThat(result).isNotEmpty();
		assertThat(result.size()).isEqualTo(5);
	}

	@DisplayName("JUnit Test for addIngredientRef method")
	@Test
	public void givenIngredientReferenceDTO_whenAddIngredientRef_thenReturnIngredientReferenceDTO() {

		IngredientType type = TestUtils.buildIngredientType();
		IngredientReferenceDTO dto = TestUtils.buildIngredientReferenceDTO(true);
		IngredientReference entity1 = TestUtils.buildIngredientReference(false, type);
		IngredientReference entity2 = TestUtils.buildIngredientReference(true, type);

		given(ingredientTypeRepository.findById(1l)).willReturn(Optional.of(type));
		given(ingredientRefRepository.save(entity1)).willReturn(entity2);
		given(ingredientRefMapper.mapToDTO(entity2)).willReturn(dto);

		IngredientReferenceDTO result = service.addIngredientRef(dto);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(1l);
	}

}
