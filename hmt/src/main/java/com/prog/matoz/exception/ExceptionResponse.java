package com.prog.matoz.exception;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse implements Serializable{
	
	private static final long serialVersionUID = 8778112362435228976L;

	private LocalDateTime dateTime;
	private String message;
	private String details;
	
}
