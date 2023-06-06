package com.pluralsight.recipe.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "INGREDIENT")
@Entity
@Data
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "locale")
	private String locale;

	@Column(name = "quantity")
	private Double quantity;

	@ManyToOne
	@JoinColumn(name = "id")
	private UnitReference unitReference;

	@ManyToOne
	@JoinColumn(name = "id")
	private IngredientReference ingredientReference;

	@ManyToOne
	@JoinColumn(name = "id")
	private LocalizedRecipe localizedRecipe;
	
	

}
