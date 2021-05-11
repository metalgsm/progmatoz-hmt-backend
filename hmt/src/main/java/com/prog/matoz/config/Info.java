package com.prog.matoz.config;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Info {

	private String status;
	
	private Map<String, String> dependencies;
}
