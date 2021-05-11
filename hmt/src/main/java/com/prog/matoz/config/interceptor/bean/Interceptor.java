package com.prog.matoz.config.interceptor.bean;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Interceptor {

	private String type;
	private String method;
	private String path;
	private String headers;
	private String parameters;
	private Object body;
	private LocalDateTime time;

}
