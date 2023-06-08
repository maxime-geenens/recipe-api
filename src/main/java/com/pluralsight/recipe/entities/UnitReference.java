package com.pluralsight.recipe.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "UNIT_REFERENCE")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class UnitReference {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "lang")
	private String lang;

	@Column(name = "symbol")
	private String symbol;

	@Column(name = "name")
	private String name;

	/**
	 * Conversion in metric system if needed
	 */
	@Column(name = "description")
	private String description;

	/**
	 * To ensure it stays a reference and no duplicates are created in the table
	 * 
	 * Has to be set as lang + name
	 */
	@Column(name = "code", unique = true)
	private String code;

}
