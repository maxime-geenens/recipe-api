package com.pluralsight.recipe.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pluralsight.recipe.dto.IngredientReferenceDTO;
import com.pluralsight.recipe.dto.IngredientTypeDTO;
import com.pluralsight.recipe.dto.LangReferenceDTO;
import com.pluralsight.recipe.dto.RecipeTypeDTO;
import com.pluralsight.recipe.dto.ToolReferenceDTO;
import com.pluralsight.recipe.dto.ToolTypeDTO;
import com.pluralsight.recipe.dto.UnitReferenceDTO;
import com.pluralsight.recipe.dto.mappers.IngredientReferenceMapper;
import com.pluralsight.recipe.dto.mappers.IngredientTypeMapper;
import com.pluralsight.recipe.dto.mappers.LangReferenceMapper;
import com.pluralsight.recipe.dto.mappers.RecipeTypeMapper;
import com.pluralsight.recipe.dto.mappers.ToolReferenceMapper;
import com.pluralsight.recipe.dto.mappers.ToolTypeMapper;
import com.pluralsight.recipe.dto.mappers.UnitReferenceMapper;
import com.pluralsight.recipe.entities.IngredientReference;
import com.pluralsight.recipe.entities.IngredientType;
import com.pluralsight.recipe.entities.LangReference;
import com.pluralsight.recipe.entities.QIngredientReference;
import com.pluralsight.recipe.entities.QIngredientType;
import com.pluralsight.recipe.entities.QRecipeType;
import com.pluralsight.recipe.entities.QToolReference;
import com.pluralsight.recipe.entities.QToolType;
import com.pluralsight.recipe.entities.QUnitReference;
import com.pluralsight.recipe.entities.RecipeType;
import com.pluralsight.recipe.entities.ToolReference;
import com.pluralsight.recipe.entities.ToolType;
import com.pluralsight.recipe.entities.UnitReference;
import com.pluralsight.recipe.repositories.IngredientReferenceRepository;
import com.pluralsight.recipe.repositories.IngredientTypeRepository;
import com.pluralsight.recipe.repositories.LangReferenceRepository;
import com.pluralsight.recipe.repositories.RecipeTypeRepository;
import com.pluralsight.recipe.repositories.ToolReferenceRepository;
import com.pluralsight.recipe.repositories.ToolTypeRepository;
import com.pluralsight.recipe.repositories.UnitReferenceRepository;
import com.pluralsight.recipe.services.impl.ReferencesServiceImpl;
import com.pluralsight.recipe.utils.TestUtils;
import com.querydsl.core.types.Predicate;

@ExtendWith(MockitoExtension.class)
class ReferencesServiceTest {

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

	@Mock
	private RecipeTypeMapper recipeTypeMapper;

	@Mock
	private IngredientTypeMapper ingredientTypeMapper;

	@Mock
	private ToolReferenceRepository toolRefRepository;

	@Mock
	private ToolTypeRepository toolTypeRepository;

	@Mock
	private ToolReferenceMapper toolRefMapper;

	@Mock
	private ToolTypeMapper toolTypeMapper;
	
	@Mock
	private LangReferenceRepository langRefRepository;

	@Mock
	private LangReferenceMapper langRefMapper;

	@DisplayName("JUnit Test for getRecipeTypeById method")
	@Test
	void givenTypeCode_whenGetRecipeTypeById_thenReturnRecipeType() {

		RecipeType entity = TestUtils.buildRecipeType();
		Long id = 1l;

		given(recipeTypeRepository.findById(id)).willReturn(Optional.of(entity));

		RecipeType result = service.getRecipeTypeById(id);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(id);
		assertThat(result.getName()).isEqualTo(entity.getName());
		assertThat(result.getLang()).isEqualTo(entity.getLang());
	}

	@DisplayName("JUnit Test for getUnitReferenceById method")
	@Test
	void givenId_whenGetUnitReferenceById_thenReturnUnitReference() {

		UnitReference entity = TestUtils.buildUnitReference();

		given(unitRefRepository.findById(1l)).willReturn(Optional.of(entity));

		UnitReference result = service.getUnitReferenceById(1l);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(entity.getId());
		assertThat(result.getName()).isEqualTo(entity.getName());
		assertThat(result.getLang()).isEqualTo(entity.getLang());
		assertThat(result.getSymbol()).isEqualTo(entity.getSymbol());
		assertThat(result.getDescription()).isEqualTo(entity.getDescription());
	}

	@DisplayName("JUnit Test for getIngredientReferenceById method")
	@Test
	void givenId_whenGetIngredientReferenceById_thenReturnIngredientReference() {

		IngredientReference entity = TestUtils.buildIngredientReference();

		given(ingredientRefRepository.findById(1l)).willReturn(Optional.of(entity));

		IngredientReference result = service.getIngredientReferenceById(1l);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(entity.getId());
		assertThat(result.getName()).isEqualTo(entity.getName());
		assertThat(result.getLang()).isEqualTo(entity.getLang());
	}

	@DisplayName("JUnit Test for listIngredientsByTypeAndLang method")
	@Test
	void givenTypeAndLang_whenListIngredientsByTypeAndLang_thenReturnIngredientReferenceDTOList() {
		
		List<IngredientReference> ingredientList = TestUtils.buildIngredientReferenceList(5);

		QIngredientReference qIngredientRef = QIngredientReference.ingredientReference;
		Predicate predicate = qIngredientRef.type.name.eq("Name").and(qIngredientRef.lang.eq("FR"));

		given(ingredientRefRepository.findAll(predicate)).willReturn(ingredientList);

		List<IngredientReferenceDTO> result = service.listIngredientsRefByTypeAndLang("Name", "FR");

		assertThat(result)
			.isNotNull()
			.isNotEmpty()
			.hasSize(5);
	}

	@DisplayName("JUnit Test for listUnitsByLang method")
	@Test
	void givenLang_whenListUnitsByLang_thenReturnUnitReferenceDTOList() {

		List<UnitReference> list = TestUtils.buildUnitReferenceList(5);

		QUnitReference qUnitReference = QUnitReference.unitReference;
		Predicate predicate = qUnitReference.lang.eq("FR");

		given(unitRefRepository.findAll(predicate)).willReturn(list);

		List<UnitReferenceDTO> result = service.listUnitsByLang("FR");

		assertThat(result)
			.isNotNull()
			.isNotEmpty()
			.hasSize(5);
	}

	@DisplayName("JUnit Test for addIngredientRef method")
	@Test
	void givenIngredientReferenceDTO_whenAddIngredientRef_thenReturnIngredientReferenceDTO() {

		IngredientReferenceDTO dto = TestUtils.buildIngredientReferenceDTO(true);
		IngredientReference entity1 = TestUtils.buildIngredientReference();

		given(ingredientRefMapper.mapToEntity(dto)).willReturn(entity1);
		given(ingredientRefRepository.save(entity1)).willReturn(entity1);
		given(ingredientRefMapper.mapToDTO(entity1)).willReturn(dto);

		IngredientReferenceDTO result = service.addIngredientRef(dto);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(1l);
	}

	@DisplayName("JUnit Test for listRecipeTypesByLang method")
	@Test
	void givenTypeAndLang_whenListRecipeTypesByLang_thenReturnRecipeTypeDTOList() {

		List<RecipeType> list = TestUtils.buildRecipeTypeList(5);
		List<RecipeTypeDTO> dtoList = TestUtils.buildRecipeTypeDTOList(5); 

		QRecipeType qRecipeType = QRecipeType.recipeType;
		Predicate predicate = qRecipeType.lang.eq("FR");

		given(recipeTypeRepository.findAll(predicate)).willReturn(list);
		
		for (int i = 0; i < list.size(); i++) {
			given(recipeTypeMapper.mapToDTO(list.get(i))).willReturn(dtoList.get(i));
		}
		

		List<RecipeTypeDTO> result = service.listRecipeTypesByLang("FR");

		assertThat(result)
			.isNotNull()
			.isNotEmpty()
			.hasSize(5);
	}

	@DisplayName("JUnit Test for listIngredientTypesByLang method")
	@Test
	void givenTypeAndLang_whenListIngredientTypesByLang_thenReturnIngredientTypeDTOList() {

		List<IngredientType> list = TestUtils.buildIngredientTypeList(5);
		List<IngredientTypeDTO> dtoList = TestUtils.buildIngredientTypeDTOList(5);

		QIngredientType qIngredientType = QIngredientType.ingredientType;
		Predicate predicate = qIngredientType.lang.eq("FR");

		given(ingredientTypeRepository.findAll(predicate)).willReturn(list);
		
		for (int i = 0; i < list.size(); i++) {
			given(ingredientTypeMapper.mapToDTO(list.get(i))).willReturn(dtoList.get(i));
		}

		List<IngredientTypeDTO> result = service.listIngredientTypesByLang("FR");

		assertThat(result)
			.isNotNull()
			.isNotEmpty()
			.hasSize(5);
	}

	@DisplayName("JUnit Test for listToolsByTypeAndLang method")
	@Test
	void givenTypeAndLang_whenListToolsByTypeAndLang_thenReturnToolReferenceDTOList() {
		
		List<ToolReference> list = TestUtils.buildToolReferenceList(5);

		QToolReference qToolRef = QToolReference.toolReference;
		Predicate predicate = qToolRef.type.name.eq("Name").and(qToolRef.lang.eq("FR"));

		given(toolRefRepository.findAll(predicate)).willReturn(list);

		List<ToolReferenceDTO> result = service.listToolsRefByTypeAndLang("Name", "FR");

		assertThat(result)
			.isNotNull()
			.isNotEmpty()
			.hasSize(5);
	}

	@DisplayName("JUnit Test for listToolTypesByLang method")
	@Test
	void givenTypeAndLang_whenListToolTypesByLang_thenReturnToolTypeDTOList() {

		List<ToolType> list = TestUtils.buildToolTypeList(5);
		List<ToolTypeDTO> dtoList = TestUtils.buildToolTypeDTOList(5);

		QToolType qToolType = QToolType.toolType;
		Predicate predicate = qToolType.lang.eq("FR");

		given(toolTypeRepository.findAll(predicate)).willReturn(list);
		
		for (int i = 0; i < list.size(); i++) {
			given(toolTypeMapper.mapToDTO(list.get(i))).willReturn(dtoList.get(i));
		}

		List<ToolTypeDTO> result = service.listToolTypesByLang("FR");

		assertThat(result)
			.isNotNull()
			.isNotEmpty()
			.hasSize(5);
	}

	@DisplayName("JUnit Test for addToolRef method")
	@Test
	void givenToolReferenceDTO_whenAddToolRef_thenReturnToolReferenceDTO() {

		ToolReferenceDTO dto = TestUtils.buildToolReferenceDTO(true);
		ToolReference entity1 = TestUtils.buildToolReference();
		ToolReference entity2 = TestUtils.buildToolReference();

		given(toolRefMapper.mapToEntity(dto)).willReturn(entity1);
		given(toolRefRepository.save(entity1)).willReturn(entity2);
		given(toolRefMapper.mapToDTO(entity2)).willReturn(dto);

		ToolReferenceDTO result = service.addToolRef(dto);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(1l);
	}

	@DisplayName("JUnit Test for listLangReferences method")
	@Test
	void whenListLangReferences_thenReturnLangReferenceDTO() {

		List<LangReferenceDTO> dtoList = new ArrayList<>();
		List<LangReference> list = new ArrayList<>();

		LangReferenceDTO dto = new LangReferenceDTO(1l, "Français", "FR");
		LangReference entity1 = new LangReference(1l, "Français", "FR");

		dtoList.add(dto);
		list.add(entity1);

		given(langRefRepository.findAll()).willReturn(list);
		given(langRefMapper.mapToDTO(entity1)).willReturn(dto);

		List<LangReferenceDTO> result = service.listLangReferences();

		assertThat(result)
				.isNotNull()
				.isNotEmpty()
				.hasSize(1);
	}

}
