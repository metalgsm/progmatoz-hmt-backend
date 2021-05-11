package com.prog.matoz.exception;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.prog.matoz.config.HMTConfig;

import lombok.AllArgsConstructor;

@ControllerAdvice
@RestController
@AllArgsConstructor
public class TemplateExceptionHandler extends ResponseEntityExceptionHandler{

	private HMTConfig config;
	
	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handlerBadRequestException(Exception e, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				LocalDateTime.now(ZoneId.of(config.getTimeZone())), e.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
}
