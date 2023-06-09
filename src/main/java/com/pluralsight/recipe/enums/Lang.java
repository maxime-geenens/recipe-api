package com.pluralsight.recipe.enums;

public enum Lang {

	ENGLISH("EN"), FRANCAIS("FR"), PORTUGUES("PT");

	private final String code;

	Lang(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
