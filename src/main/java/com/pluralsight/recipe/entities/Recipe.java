package com.pluralsight.recipe.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "RECIPE")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "lang")
	private String lang;

	@ManyToOne
	@JoinColumn(name = "type", referencedColumnName = "id")
	private RecipeType type;

}
