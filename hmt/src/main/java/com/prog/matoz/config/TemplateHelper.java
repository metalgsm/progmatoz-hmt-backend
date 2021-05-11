package com.prog.matoz.config;

import java.util.Optional;

public class TemplateHelper {

	public static Boolean isNull(String s) {
		return !Optional.ofNullable(s).isPresent();
	}
}
