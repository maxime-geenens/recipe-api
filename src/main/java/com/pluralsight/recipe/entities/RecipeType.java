package com.pluralsight.recipe.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "RECIPE_TYPE")
@Entity
@Data
public class RecipeType {

	@Column(name = "id")
	private Long id;

	@Column(name = "locale")
	private String locale;

	@Column(name = "name")
	private String name;

}
