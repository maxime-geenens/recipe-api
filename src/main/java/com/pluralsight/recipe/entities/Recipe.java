package com.pluralsight.recipe.entities;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapKey;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "RECIPE")
@Entity
@Data
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@OneToMany(mappedBy = "recipe", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, orphanRemoval = true)
	@MapKey(name = "localizedRecipeId.locale")
	private Map<String, LocalizedRecipe> localizations = new HashMap<>();

	public String getName(String locale) {
		return localizations.get(locale).getName();
	}

	public String getDescription(String locale) {
		return localizations.get(locale).getDescription();
	}

	public RecipeType getType(String locale) {
		return localizations.get(locale).getType();
	}
}
