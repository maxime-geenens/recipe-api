package com.pluralsight.recipe.dto.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.pluralsight.recipe.dto.LabelTranslationDTO;
import com.pluralsight.recipe.entities.LabelTranslation;

@Mapper(componentModel = "spring", uses = {
		LangReferenceMapper.class }, injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface LabelTranslationMapper {

	LabelTranslationDTO mapToDTO(LabelTranslation entity);

	LabelTranslation maptoEntity(LabelTranslationDTO dto);

}
