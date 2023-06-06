package com.pluralsight.recipe.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "LOCALIZED_RECIPE")
@Entity
@Data
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class LocalizedRecipe {

	@EmbeddedId
	private LocalizedRecipeId localizedRecipeId;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@ManyToOne
	@MapsId("id")
	@JoinColumn(name = "id")
	private Recipe recipe;

	@ManyToOne
	@JoinColumn(name = "id")
	private RecipeType type;
}
