package com.pluralsight.recipe.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "RECIPE_TYPE")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class RecipeType {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "lang")
	private String lang;

	@Column(name = "name")
	private String name;

}
