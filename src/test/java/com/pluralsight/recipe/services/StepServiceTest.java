package com.pluralsight.recipe.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pluralsight.recipe.dto.StepDTO;
import com.pluralsight.recipe.dto.mappers.StepMapper;
import com.pluralsight.recipe.entities.QStep;
import com.pluralsight.recipe.entities.Step;
import com.pluralsight.recipe.exceptions.InvalidParamException;
import com.pluralsight.recipe.repositories.StepRepository;
import com.pluralsight.recipe.services.impl.StepServiceImpl;
import com.pluralsight.recipe.utils.ExceptionMessageConstants;
import com.pluralsight.recipe.utils.TestUtils;
import com.querydsl.core.types.Predicate;

@ExtendWith(MockitoExtension.class)
class StepServiceTest {

	@InjectMocks
	private StepServiceImpl service;

	@Mock
	private StepRepository dao;

	@Mock
	private StepMapper mapper;

	@DisplayName("JUnit Test for listStepsByRecipe method")
	@Test
	void givenRecipeId_whenListStepsByRecipe_thenReturnStepDTOList() {

		List<Step> list = TestUtils.buildStepList(5);
		List<StepDTO> dtoList = TestUtils.buildStepDTOList(5, true);
		Long recipeId = 1l;

		QStep qStep = QStep.step;
		Predicate predicate = qStep.recipe.id.eq(recipeId);

		given(dao.findAll(predicate)).willReturn(list);
		for (int i = 0; i < list.size(); i++) {
			given(mapper.mapToDTO(list.get(i))).willReturn(dtoList.get(i));
		}

		List<StepDTO> result = service.listStepsByRecipe(recipeId);

		assertThat(result)
			.isNotNull()
			.isNotEmpty()
			.hasSize(5);
	}

	@DisplayName("JUnit Test for addStep method")
	@Test
	void givenStepDTO_whenAddStep_thenReturnStepDTO() {

		int quantity = 5;

		StepDTO dto = TestUtils.buildStepDTO(true);
		Step entity = TestUtils.buildStep();
		List<Step> list = TestUtils.buildStepList(quantity);

		QStep qStep = QStep.step;
		Predicate predicate = qStep.recipe.id.eq(dto.getRecipeId());

		given(dao.findAll(predicate)).willReturn(list);
		given(mapper.mapToEntity(dto)).willReturn(entity);
		given(dao.save(entity)).willReturn(entity);
		given(mapper.mapToDTO(entity)).willReturn(dto);

		StepDTO result = service.addStep(dto);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(entity.getId());
		assertThat(result.getLang()).isEqualTo(entity.getLang());
		assertThat(result.getPosition()).isEqualTo(quantity + 1);
		assertThat(result.getRecipeId()).isEqualTo(entity.getRecipe().getId());
	}

	@DisplayName("JUnit Test for saveStepList method")
	@Test
	void givenStepDTOList_whenSaveStepList_thenReturnStepDTOList() {

		int quantity = 5;

		List<Step> list = TestUtils.buildStepList(quantity);
		List<StepDTO> dtoList = TestUtils.buildStepDTOList(quantity, false);

		for (int i = 0; i < list.size(); i++) {
			given(mapper.mapToEntity(dtoList.get(i))).willReturn(list.get(i));
		}
		given(dao.saveAll(list)).willReturn(list);

		for (int i = 0; i < list.size(); i++) {
			given(mapper.mapToDTO(list.get(i))).willReturn(dtoList.get(i));
		}

		List<StepDTO> result = service.saveStepList(dtoList);

		assertThat(result)
			.isNotNull()
			.isNotEmpty()
			.hasSize(quantity);
	}

	@DisplayName("JUnit Test for saveStepList method (with duplicate position)")
	@Test
	void givenStepDTOList_whenSaveStepList_withDuplicatePosition_thenThrowException() {

		int quantity = 4;

		List<StepDTO> dtoList = TestUtils.buildStepDTOList(quantity, false);
		StepDTO dto = TestUtils.buildStepDTO(false);
		dtoList.add(dto);

		Throwable exception = assertThrows(InvalidParamException.class, () -> service.saveStepList(dtoList));
		assertThat(exception.getMessage()).isEqualTo(" Position ::" + ExceptionMessageConstants.PARAMETER_UNIQUE);
	}

	@DisplayName("JUnit Test for updateStep method")
	@Test
	void givenStepDTO_whenUpdateStep_thenReturnStepDTO() {

		int quantity = 5;

		Step entity = TestUtils.buildStep();
		List<StepDTO> dtoList = TestUtils.buildStepDTOList(quantity, false);
		StepDTO dto = TestUtils.buildStepDTO(false);
		dto.setPosition(quantity + 1);
		List<Step> list = TestUtils.buildStepList(quantity);

		QStep qStep = QStep.step;
		Predicate predicate = qStep.recipe.id.eq(1l);

		given(dao.findAll(predicate)).willReturn(list);
		for (int i = 0; i < list.size(); i++) {
			given(mapper.mapToDTO(list.get(i))).willReturn(dtoList.get(i));
		}

		given(mapper.mapToEntity(dto)).willReturn(entity);
		given(dao.save(entity)).willReturn(entity);
		given(mapper.mapToDTO(entity)).willReturn(dto);

		StepDTO result = service.updateStep(dto);

		assertThat(result).isNotNull();
		assertThat(result.getRecipeId()).isEqualTo(1l);
		assertThat(result.getPosition()).isEqualTo(quantity + 1);
	}

}
