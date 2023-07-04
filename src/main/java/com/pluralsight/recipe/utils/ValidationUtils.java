package com.pluralsight.recipe.utils;

import com.pluralsight.recipe.enums.Lang;
import com.pluralsight.recipe.exceptions.InvalidParamException;

public class ValidationUtils {
	
	private ValidationUtils() {
	}

	public static final boolean stringParamNotNullNotBlankNotEmpty(String param, String paramName) {

		boolean isValid = false;

		if (param != null) {
			if (param.isBlank() || param.isEmpty()) {
				throw new InvalidParamException(
						buildExceptionMessage(paramName, ExceptionMessageConstants.PARAMETER_BLANK_EMPTY));
			} else {
				isValid = true;
			}
		} else {
			throw new InvalidParamException(buildExceptionMessage(paramName, ExceptionMessageConstants.PARAMETER_NULL));
		}

		return isValid;
	}

	public static final boolean validateLang(String value, String object) {

		boolean isValid = false;

		for (Lang lang : Lang.values()) {
			if (lang.getCode().equalsIgnoreCase(value)) {
				isValid = true;
				break;
			}
		}

		if (!isValid) {
			throw new InvalidParamException(
					buildExceptionMessage(object + ".lang", ExceptionMessageConstants.PARAMETER_INVALID));
		}

		return isValid;
	}

	public static final String buildExceptionMessage(String paramName, String message) {

		StringBuilder builder = new StringBuilder();
		return builder.append(paramName).append(" :: ").append(message).toString();
	}

}
