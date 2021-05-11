package com.prog.matoz.security.exception;

import java.io.IOException;
import java.time.Instant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class AccessTokenAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException e) throws IOException {

		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(createErrorBody(e));
	}

	private String createErrorBody(AuthenticationException exception) {
		JsonObject exceptionMessage = new JsonObject();
		exceptionMessage.addProperty("code", HttpStatus.UNAUTHORIZED.value());
		exceptionMessage.addProperty("reason", HttpStatus.UNAUTHORIZED.getReasonPhrase());
		exceptionMessage.addProperty("timestamp", Instant.now().toString());
		exceptionMessage.addProperty("message", exception.getMessage());
		return new Gson().toJson(exceptionMessage);
	}
}