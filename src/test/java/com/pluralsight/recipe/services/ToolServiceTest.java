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

import com.pluralsight.recipe.dto.ToolDTO;
import com.pluralsight.recipe.dto.mappers.ToolMapper;
import com.pluralsight.recipe.entities.QTool;
import com.pluralsight.recipe.entities.Tool;
import com.pluralsight.recipe.repositories.RecipeRepository;
import com.pluralsight.recipe.repositories.ToolRepository;
import com.pluralsight.recipe.services.impl.ReferencesServiceImpl;
import com.pluralsight.recipe.services.impl.ToolServiceImpl;
import com.pluralsight.recipe.utils.TestUtils;
import com.querydsl.core.types.Predicate;

@ExtendWith(MockitoExtension.class)
class ToolServiceTest {

	@InjectMocks
	private ToolServiceImpl service;

	@InjectMocks
	private ReferencesServiceImpl refService;

	@Mock
	private ToolRepository dao;

	@Mock
	private RecipeRepository recipeDao;

	@Mock
	private ToolMapper mapper;

	@DisplayName("JUnit Test for listToolsByRecipe method")
	@Test
	void givenRecipeId_whenListToolsByRecipe_thenReturnToolDTOList() {

		List<Tool> list = TestUtils.buildToolList(5);
		Long recipeId = 1l;

		QTool qTool = QTool.tool;
		Predicate predicate = qTool.recipe.id.eq(recipeId);

		given(dao.findAll(predicate)).willReturn(list);

		List<ToolDTO> result = service.listToolsByRecipe(recipeId);

		assertThat(result)
			.isNotNull()
			.isNotEmpty()
			.hasSize(5);
	}

	@DisplayName("JUnit Test for listToolsByRecipe method (negative scenario)")
	@Test
	void givenRecipeId_whenListToolsByRecipe_thenReturnEmptyToolDTOList() {

		List<Tool> list = new ArrayList<>();
		Long recipeId = 1l;

		QTool qTool = QTool.tool;
		Predicate predicate = qTool.recipe.id.eq(recipeId);

		given(dao.findAll(predicate)).willReturn(list);

		List<ToolDTO> result = service.listToolsByRecipe(recipeId);

		assertThat(result)
			.isNotNull()
			.isEmpty();
	}

	@DisplayName("JUnit Test for addTool method")
	@Test
	void givenToolDTO_whenAddTool_thenReturnToolDTO() {

		ToolDTO dto = TestUtils.buildToolDTO(true);
		Tool entity = TestUtils.buildTool();

		given(mapper.mapToEntity(dto)).willReturn(entity);
		given(dao.save(entity)).willReturn(entity);
		given(mapper.mapToDTO(entity)).willReturn(dto);

		ToolDTO result = service.saveTool(dto);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(entity.getId());
		assertThat(result.getLang()).isEqualTo(entity.getLang());
		assertThat(result.getQuantity()).isEqualTo(entity.getQuantity());
		assertThat(result.getToolRef().getId()).isEqualTo(entity.getToolReference().getId());
		assertThat(result.getRecipeId()).isEqualTo(entity.getRecipe().getId());
	}

	@DisplayName("JUnit Test for saveToolList method")
	@Test
	void givenToolDTOList_whenSaveToolList_thenReturnToolDTOList() {

		List<ToolDTO> dtoList = TestUtils.buildToolDTOList(5, true);
		List<Tool> entityList = TestUtils.buildToolList(5);

		for (int i = 0; i < dtoList.size(); i++) {
			given(mapper.mapToEntity(dtoList.get(i))).willReturn(entityList.get(i));
		}
		given(dao.saveAll(entityList)).willReturn(entityList);

		List<ToolDTO> result = service.saveToolList(dtoList);

		assertThat(result)
			.isNotNull()
			.isNotEmpty()
			.hasSize(5);
	}

	@DisplayName("JUnit Test for deleteTool method")
	@Test
	void givenRecipeId_whenDeleteRecipe_thenNothing() {

		Long id = 1l;

		willDoNothing().given(dao).deleteById(id);

		service.deleteTool(id);

		verify(dao, times(1)).deleteById(id);
	}

}
