package com.pluralsight.recipe.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pluralsight.recipe.dto.ToolDTO;
import com.pluralsight.recipe.dto.mappers.ToolMapper;
import com.pluralsight.recipe.entities.QTool;
import com.pluralsight.recipe.entities.Tool;
import com.pluralsight.recipe.repositories.ToolRepository;
import com.pluralsight.recipe.services.ToolService;
import com.querydsl.core.types.Predicate;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ToolServiceImpl implements ToolService {

	@Autowired
	private ToolRepository repository;

	@Autowired
	private ToolMapper mapper;

	@Override
	public List<ToolDTO> listToolsByRecipe(Long id) {

		QTool qTool = QTool.tool;
		Predicate predicate = qTool.recipe.id.eq(id);

		List<Tool> toolList = (List<Tool>) repository.findAll(predicate);

		return toolList.stream().map(entity -> mapper.mapToDTO(entity)).toList();
	}

	@Override
	public ToolDTO saveTool(ToolDTO dto) {

		Tool savedTool = repository.save(mapper.mapToEntity(dto));

		return mapper.mapToDTO(savedTool);
	}

	@Override
	public List<ToolDTO> saveToolList(List<ToolDTO> dtoList) {

		List<Tool> list = new ArrayList<>();

		for (ToolDTO dto : dtoList) {
			list.add(mapper.mapToEntity(dto));
		}

		List<Tool> savedList = repository.saveAll(list);

		return savedList.stream().map(entity -> mapper.mapToDTO(entity)).toList();
	}

	@Override
	public void deleteTool(Long id) {
		repository.deleteById(id);

	}

}
