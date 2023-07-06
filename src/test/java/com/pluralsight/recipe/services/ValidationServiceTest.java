package com.pluralsight.recipe.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pluralsight.recipe.dto.IngredientDTO;
import com.pluralsight.recipe.dto.IngredientReferenceDTO;
import com.pluralsight.recipe.dto.RecipeDTO;
import com.pluralsight.recipe.dto.StepDTO;
import com.pluralsight.recipe.dto.ToolDTO;
import com.pluralsight.recipe.dto.ToolReferenceDTO;
import com.pluralsight.recipe.entities.Recipe;
import com.pluralsight.recipe.exceptions.InvalidParamException;
import com.pluralsight.recipe.services.impl.ValidationServiceImpl;
import com.pluralsight.recipe.utils.ExceptionMessageConstants;
import com.pluralsight.recipe.utils.TestUtils;

@ExtendWith(MockitoExtension.class)
class ValidationServiceTest {

	@InjectMocks
	private ValidationServiceImpl service;

	@Mock
	private RecipeService recipeService;

	@Mock
	private ReferencesService referenceService;

	@Test
	void validateRecipeDTO_withGoodDTO_thenAllSucceed() {

		RecipeDTO dto = TestUtils.buildRecipeDTO(true, true);

		boolean result = service.validateRecipeDTO(dto, false);

		assertTrue(result);
	}

	@Test
	void validateRecipeDTO_withGoodDTOAndCheckCode_thenAllSucceed() {

		RecipeDTO dto = TestUtils.buildRecipeDTO(true, true);
		String code = dto.getLang() + dto.getName();

		given(recipeService.findByCode(code)).willReturn(null);

		boolean result = service.validateRecipeDTO(dto, true);

		assertTrue(result);
	}

	@Test
	void validateRecipeDTO_withGoodDTO_thenThrowException() {

		RecipeDTO dto = TestUtils.buildRecipeDTO(true, true);
		Recipe recipe = TestUtils.buildRecipe(true);
		String code = dto.getLang() + dto.getName();

		given(recipeService.findByCode(code)).willReturn(recipe);

		Throwable exception = assertThrows(InvalidParamException.class, () -> service.validateRecipeDTO(dto, true));
		assertEquals("Recipe" + " :: " + ExceptionMessageConstants.CODE_ALREADY_EXISTS_IN_DB, exception.getMessage());
	}

	@Test
	void validateRecipeDTO_withNullBlankEmptyName_thenThrowsException() {

		RecipeDTO nullName = TestUtils.buildRecipeDTO(false, true);
		nullName.setName(null);
		RecipeDTO empty = TestUtils.buildRecipeDTO(false, true);
		empty.setName("");
		RecipeDTO blank = TestUtils.buildRecipeDTO(false, true);
		blank.setName("  ");

		Throwable nullException = assertThrows(InvalidParamException.class,
				() -> service.validateRecipeDTO(nullName, false));
		assertEquals("RecipeDTO.name" + " :: " + ExceptionMessageConstants.PARAMETER_NULL, nullException.getMessage());

		Throwable emptyException = assertThrows(InvalidParamException.class,
				() -> service.validateRecipeDTO(empty, false));
		assertEquals("RecipeDTO.name" + " :: " + ExceptionMessageConstants.PARAMETER_BLANK_EMPTY,
				emptyException.getMessage());

		Throwable blankException = assertThrows(InvalidParamException.class,
				() -> service.validateRecipeDTO(blank, false));
		assertEquals("RecipeDTO.name" + " :: " + ExceptionMessageConstants.PARAMETER_BLANK_EMPTY,
				blankException.getMessage());

	}

	@Test
	void validateRecipeDTO_withNullBlankEmptyDescription_thenThrowsException() {

		RecipeDTO nullDescription = TestUtils.buildRecipeDTO(false, true);
		nullDescription.setDescription(null);
		RecipeDTO empty = TestUtils.buildRecipeDTO(false, true);
		empty.setDescription("");
		RecipeDTO blank = TestUtils.buildRecipeDTO(false, true);
		blank.setDescription("  ");

		Throwable nullException = assertThrows(InvalidParamException.class,
				() -> service.validateRecipeDTO(nullDescription, false));
		assertEquals("RecipeDTO.description" + " :: " + ExceptionMessageConstants.PARAMETER_NULL,
				nullException.getMessage());

		Throwable emptyException = assertThrows(InvalidParamException.class,
				() -> service.validateRecipeDTO(empty, false));
		assertEquals("RecipeDTO.description" + " :: " + ExceptionMessageConstants.PARAMETER_BLANK_EMPTY,
				emptyException.getMessage());

		Throwable blankException = assertThrows(InvalidParamException.class,
				() -> service.validateRecipeDTO(blank, false));
		assertEquals("RecipeDTO.description" + " :: " + ExceptionMessageConstants.PARAMETER_BLANK_EMPTY,
				blankException.getMessage());

	}

	@Test
	void validateRecipeDTO_withNullTypeId_thenThrowsException() {

		RecipeDTO nullType = TestUtils.buildRecipeDTO(false, false);

		Throwable nullException = assertThrows(InvalidParamException.class,
				() -> service.validateRecipeDTO(nullType, false));
		assertEquals("RecipeDTO.typeId" + " :: " + ExceptionMessageConstants.PARAMETER_NULL,
				nullException.getMessage());
	}

	@Test
	void validateRecipeDTO_withNullBlankEmptyLang_thenThrowsException() {

		RecipeDTO nullLang = TestUtils.buildRecipeDTO(false, true);
		nullLang.setLang(null);
		RecipeDTO empty = TestUtils.buildRecipeDTO(false, true);
		empty.setLang("");
		RecipeDTO blank = TestUtils.buildRecipeDTO(false, true);
		blank.setLang("  ");

		Throwable nullException = assertThrows(InvalidParamException.class,
				() -> service.validateRecipeDTO(nullLang, false));
		assertEquals("RecipeDTO.lang" + " :: " + ExceptionMessageConstants.PARAMETER_NULL, nullException.getMessage());

		Throwable emptyException = assertThrows(InvalidParamException.class,
				() -> service.validateRecipeDTO(empty, false));
		assertEquals("RecipeDTO.lang" + " :: " + ExceptionMessageConstants.PARAMETER_BLANK_EMPTY,
				emptyException.getMessage());

		Throwable blankException = assertThrows(InvalidParamException.class,
				() -> service.validateRecipeDTO(blank, false));
		assertEquals("RecipeDTO.lang" + " :: " + ExceptionMessageConstants.PARAMETER_BLANK_EMPTY,
				blankException.getMessage());

	}

	@Test
	void validateRecipeDTO_withWrongLang_thenThrowsException() {

		RecipeDTO wrongLang = TestUtils.buildRecipeDTO(false, true);
		wrongLang.setLang("BREKZ");

		Throwable exception = assertThrows(InvalidParamException.class,
				() -> service.validateRecipeDTO(wrongLang, false));
		assertEquals("RecipeDTO.lang :: " + ExceptionMessageConstants.PARAMETER_INVALID, exception.getMessage());

	}

	@Test
	void validateIngredientDTO_withGoodDTO_thenAllSucceed() {

		IngredientDTO dto = TestUtils.buildIngredientDTO(false);

		boolean result = service.validateIngredientDTO(dto);

		assertTrue(result);
	}

	@Test
	void validateIngredientDTO_withNullBlankEmptyLang_thenThrowsException() {

		IngredientDTO nullLang = TestUtils.buildIngredientDTO(false);
		nullLang.setLang(null);
		IngredientDTO empty = TestUtils.buildIngredientDTO(false);
		empty.setLang("");
		IngredientDTO blank = TestUtils.buildIngredientDTO(false);
		blank.setLang("  ");

		Throwable nullException = assertThrows(InvalidParamException.class,
				() -> service.validateIngredientDTO(nullLang));
		assertEquals("IngredientDTO.lang" + " :: " + ExceptionMessageConstants.PARAMETER_NULL,
				nullException.getMessage());

		Throwable emptyException = assertThrows(InvalidParamException.class,
				() -> service.validateIngredientDTO(empty));
		assertEquals("IngredientDTO.lang" + " :: " + ExceptionMessageConstants.PARAMETER_BLANK_EMPTY,
				emptyException.getMessage());

		Throwable blankException = assertThrows(InvalidParamException.class,
				() -> service.validateIngredientDTO(blank));
		assertEquals("IngredientDTO.lang" + " :: " + ExceptionMessageConstants.PARAMETER_BLANK_EMPTY,
				blankException.getMessage());
	}

	@Test
	void validateIngredientDTO_withWrongLang_thenThrowsException() {

		IngredientDTO wrongLang = TestUtils.buildIngredientDTO(false);
		wrongLang.setLang("BREKZ");

		Throwable exception = assertThrows(InvalidParamException.class, () -> service.validateIngredientDTO(wrongLang));
		assertEquals("IngredientDTO.lang :: " + ExceptionMessageConstants.PARAMETER_INVALID, exception.getMessage());
	}

	@Test
	void validateIngredientDTO_withNullQuantity_thenThrowsException() {

		IngredientDTO dto = TestUtils.buildIngredientDTO(false);
		dto.setQuantity(null);

		Throwable exception = assertThrows(InvalidParamException.class, () -> service.validateIngredientDTO(dto));
		assertEquals("IngredientDTO.quantity :: " + ExceptionMessageConstants.PARAMETER_NULL, exception.getMessage());
	}

	@Test
	void validateIngredientDTO_withNegativeQuantity_thenThrowsException() {

		IngredientDTO dto = TestUtils.buildIngredientDTO(false);
		dto.setQuantity(-1.0);

		Throwable exception = assertThrows(InvalidParamException.class, () -> service.validateIngredientDTO(dto));
		assertEquals("IngredientDTO.quantity :: " + ExceptionMessageConstants.PARAMETER_NEGATIVE,
				exception.getMessage());
	}

	@Test
	void validateIngredientDTO_withNullUnitRefId_thenThrowsException() {

		IngredientDTO dto = TestUtils.buildIngredientDTO(false);
		dto.setUnitRefId(null);

		Throwable exception = assertThrows(InvalidParamException.class, () -> service.validateIngredientDTO(dto));
		assertEquals("IngredientDTO.unitRefId :: " + ExceptionMessageConstants.PARAMETER_NULL, exception.getMessage());
	}

	@Test
	void validateIngredientDTO_withNullIngredientRefId_thenThrowsException() {

		IngredientDTO dto = TestUtils.buildIngredientDTO(false);
		dto.setIngredientRefId(null);

		Throwable exception = assertThrows(InvalidParamException.class, () -> service.validateIngredientDTO(dto));
		assertEquals("IngredientDTO.ingredientRefId :: " + ExceptionMessageConstants.PARAMETER_NULL,
				exception.getMessage());
	}

	@Test
	void validateIngredientDTO_withNullRecipeId_thenThrowsException() {

		IngredientDTO dto = TestUtils.buildIngredientDTO(false);
		dto.setRecipeId(null);

		Throwable exception = assertThrows(InvalidParamException.class, () -> service.validateIngredientDTO(dto));
		assertEquals("IngredientDTO.recipeId :: " + ExceptionMessageConstants.PARAMETER_NULL, exception.getMessage());
	}

	@Test
	void validateStepDTO_withGoodDTO_thenAllSucceed() {

		StepDTO dto = TestUtils.buildStepDTO(false);

		boolean result = service.validateStepDTO(dto);

		assertTrue(result);
	}

	@Test
	void validateStepDTO_withNullBlankEmptyLang_thenThrowsException() {

		StepDTO nullLang = TestUtils.buildStepDTO(false);
		nullLang.setLang(null);
		StepDTO empty = TestUtils.buildStepDTO(false);
		empty.setLang("");
		StepDTO blank = TestUtils.buildStepDTO(false);
		blank.setLang("  ");

		Throwable nullException = assertThrows(InvalidParamException.class, () -> service.validateStepDTO(nullLang));
		assertEquals("StepDTO.lang" + " :: " + ExceptionMessageConstants.PARAMETER_NULL, nullException.getMessage());

		Throwable emptyException = assertThrows(InvalidParamException.class, () -> service.validateStepDTO(empty));
		assertEquals("StepDTO.lang" + " :: " + ExceptionMessageConstants.PARAMETER_BLANK_EMPTY,
				emptyException.getMessage());

		Throwable blankException = assertThrows(InvalidParamException.class, () -> service.validateStepDTO(blank));
		assertEquals("StepDTO.lang" + " :: " + ExceptionMessageConstants.PARAMETER_BLANK_EMPTY,
				blankException.getMessage());
	}

	@Test
	void validateStepDTO_withWrongLang_thenThrowsException() {

		StepDTO wrongLang = TestUtils.buildStepDTO(false);
		wrongLang.setLang("BREKZ");

		Throwable exception = assertThrows(InvalidParamException.class, () -> service.validateStepDTO(wrongLang));
		assertEquals("StepDTO.lang :: " + ExceptionMessageConstants.PARAMETER_INVALID, exception.getMessage());
	}

	@Test
	void validateStepDTO_withNullBlankEmptyDescription_thenThrowsException() {

		StepDTO nullDescription = TestUtils.buildStepDTO(false);
		nullDescription.setDescription(null);
		StepDTO empty = TestUtils.buildStepDTO(false);
		empty.setDescription("");
		StepDTO blank = TestUtils.buildStepDTO(false);
		blank.setDescription("  ");

		Throwable nullException = assertThrows(InvalidParamException.class,
				() -> service.validateStepDTO(nullDescription));
		assertEquals("StepDTO.description" + " :: " + ExceptionMessageConstants.PARAMETER_NULL,
				nullException.getMessage());

		Throwable emptyException = assertThrows(InvalidParamException.class, () -> service.validateStepDTO(empty));
		assertEquals("StepDTO.description" + " :: " + ExceptionMessageConstants.PARAMETER_BLANK_EMPTY,
				emptyException.getMessage());

		Throwable blankException = assertThrows(InvalidParamException.class, () -> service.validateStepDTO(blank));
		assertEquals("StepDTO.description" + " :: " + ExceptionMessageConstants.PARAMETER_BLANK_EMPTY,
				blankException.getMessage());

	}

	@Test
	void validateStepDTO_withNullRecipeId_thenThrowsException() {

		StepDTO dto = TestUtils.buildStepDTO(false);
		dto.setRecipeId(null);

		Throwable exception = assertThrows(InvalidParamException.class, () -> service.validateStepDTO(dto));
		assertEquals("StepDTO.recipeId :: " + ExceptionMessageConstants.PARAMETER_NULL, exception.getMessage());
	}

	@Test
	void validateStepDTO_withNullPosition_thenThrowsException() {

		StepDTO dto = TestUtils.buildStepDTO(false);
		dto.setPosition(null);

		Throwable exception = assertThrows(InvalidParamException.class, () -> service.validateStepDTO(dto));
		assertEquals("StepDTO.position :: " + ExceptionMessageConstants.PARAMETER_NULL, exception.getMessage());
	}

	@Test
	void validateStepDTO_withNegativePosition_thenThrowsException() {

		StepDTO dto = TestUtils.buildStepDTO(false);
		dto.setPosition(-1);

		Throwable exception = assertThrows(InvalidParamException.class, () -> service.validateStepDTO(dto));
		assertEquals("StepDTO.position :: " + ExceptionMessageConstants.PARAMETER_NEGATIVE, exception.getMessage());
	}

	@Test
	void validateIngredientReferenceDTO_withGoodDTO_thenAllSucceed() {

		IngredientReferenceDTO dto = TestUtils.buildIngredientReferenceDTO(false);

		boolean result = service.validateIngredientReferenceDTO(dto);

		assertTrue(result);
	}

	@Test
	void validateIngredientReferenceDTO_withNullBlankEmptyLang_thenThrowsException() {

		IngredientReferenceDTO nullLang = TestUtils.buildIngredientReferenceDTO(false);
		nullLang.setLang(null);
		IngredientReferenceDTO empty = TestUtils.buildIngredientReferenceDTO(false);
		empty.setLang("");
		IngredientReferenceDTO blank = TestUtils.buildIngredientReferenceDTO(false);
		blank.setLang("  ");

		Throwable nullException = assertThrows(InvalidParamException.class,
				() -> service.validateIngredientReferenceDTO(nullLang));
		assertEquals("IngredientReferenceDTO.lang" + " :: " + ExceptionMessageConstants.PARAMETER_NULL,
				nullException.getMessage());

		Throwable emptyException = assertThrows(InvalidParamException.class,
				() -> service.validateIngredientReferenceDTO(empty));
		assertEquals("IngredientReferenceDTO.lang" + " :: " + ExceptionMessageConstants.PARAMETER_BLANK_EMPTY,
				emptyException.getMessage());

		Throwable blankException = assertThrows(InvalidParamException.class,
				() -> service.validateIngredientReferenceDTO(blank));
		assertEquals("IngredientReferenceDTO.lang" + " :: " + ExceptionMessageConstants.PARAMETER_BLANK_EMPTY,
				blankException.getMessage());
	}

	@Test
	void validateIngredientReferenceDTO_withWrongLang_thenThrowsException() {

		IngredientReferenceDTO wrongLang = TestUtils.buildIngredientReferenceDTO(false);
		wrongLang.setLang("BREKZ");

		Throwable exception = assertThrows(InvalidParamException.class,
				() -> service.validateIngredientReferenceDTO(wrongLang));
		assertEquals("IngredientReferenceDTO.lang :: " + ExceptionMessageConstants.PARAMETER_INVALID,
				exception.getMessage());
	}

	@Test
	void validateIngredientReferenceDTO_withNullBlankEmptyName_thenThrowsException() {

		IngredientReferenceDTO nullName = TestUtils.buildIngredientReferenceDTO(false);
		nullName.setName(null);
		IngredientReferenceDTO empty = TestUtils.buildIngredientReferenceDTO(false);
		empty.setName("");
		IngredientReferenceDTO blank = TestUtils.buildIngredientReferenceDTO(false);
		blank.setName("  ");

		Throwable nullException = assertThrows(InvalidParamException.class,
				() -> service.validateIngredientReferenceDTO(nullName));
		assertEquals("IngredientReferenceDTO.name" + " :: " + ExceptionMessageConstants.PARAMETER_NULL,
				nullException.getMessage());

		Throwable emptyException = assertThrows(InvalidParamException.class,
				() -> service.validateIngredientReferenceDTO(empty));
		assertEquals("IngredientReferenceDTO.name" + " :: " + ExceptionMessageConstants.PARAMETER_BLANK_EMPTY,
				emptyException.getMessage());

		Throwable blankException = assertThrows(InvalidParamException.class,
				() -> service.validateIngredientReferenceDTO(blank));
		assertEquals("IngredientReferenceDTO.name" + " :: " + ExceptionMessageConstants.PARAMETER_BLANK_EMPTY,
				blankException.getMessage());

	}

	@Test
	void validateIngredientReferenceDTO_withNullTypeId_thenThrowsException() {

		IngredientReferenceDTO dto = TestUtils.buildIngredientReferenceDTO(false);
		dto.setTypeId(null);

		Throwable exception = assertThrows(InvalidParamException.class,
				() -> service.validateIngredientReferenceDTO(dto));
		assertEquals("IngredientReferenceDTO.typeId :: " + ExceptionMessageConstants.PARAMETER_NULL,
				exception.getMessage());
	}

	@Test
	void validateToolDTO_withGoodDTO_thenAllSucceed() {

		ToolDTO dto = TestUtils.buildToolDTO(false);

		boolean result = service.validateToolDTO(dto);

		assertTrue(result);
	}

	@Test
	void validateToolReferenceDTO_withGoodDTO_thenAllSucceed() {

		ToolReferenceDTO dto = TestUtils.buildToolReferenceDTO(false);

		boolean result = service.validateToolReferenceDTO(dto);

		assertTrue(result);
	}
}
