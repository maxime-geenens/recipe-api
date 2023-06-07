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

@Table(name = "STEP")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Step {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "lang")
	private String lang;

	@Column(name = "position")
	private Integer position;

	@Column(name = "description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "recipe_id", referencedColumnName = "id")
	private Recipe Recipe;

}
