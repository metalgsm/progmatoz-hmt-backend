package com.prog.matoz.config.interceptor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prog.matoz.config.HMTConfig;
import com.prog.matoz.config.interceptor.bean.Interceptor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.java.Log;

@Component
@Log
@Data
@AllArgsConstructor
public class LoggingServiceImpl implements LoggingService {

	private HMTConfig config;

	@Override
	public void logRequest(HttpServletRequest httpServletRequest, Object body) {

		Interceptor request = new Interceptor();
		ObjectMapper mapper = new ObjectMapper();

		Map<String, String> parameters = buildParametersMap(httpServletRequest);
		try {
			request.setType("Request");
			request.setTime(LocalDateTime.now(ZoneId.of(config.getTimeZone())));
			request.setMethod(httpServletRequest.getMethod());
			request.setPath(httpServletRequest.getRequestURI());
			request.setHeaders(mapper.writeValueAsString(buildHeadersMap(httpServletRequest).toString()));

			if (!parameters.isEmpty()) {
				request.setParameters(mapper.writeValueAsString(parameters.toString()));
			}

			if (body != null) {
				request.setBody(body);
			}

			log.info(mapper.writeValueAsString(request));
		} catch (JsonProcessingException e) {
			log.info("fail at log: " + e.getMessage());
		}
	}

	@Override
	public void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object body) {
		try {
			ObjectMapper mapper = new ObjectMapper();

			Interceptor response = new Interceptor();

			response.setType("Response");
			response.setTime(LocalDateTime.now(ZoneId.of(config.getTimeZone())));
			response.setMethod(httpServletRequest.getMethod());
			response.setPath(httpServletRequest.getRequestURI());
			response.setHeaders(mapper.writeValueAsString(buildHeadersMap(httpServletRequest).toString()));
			response.setBody(body);

			log.info(mapper.writeValueAsString(response));
		} catch (JsonProcessingException e) {
			log.info("fail at log: " + e.getMessage());
		}
	}

	private Map<String, String> buildParametersMap(HttpServletRequest httpServletRequest) {
		Map<String, String> resultMap = new HashMap<>();
		Enumeration<String> parameterNames = httpServletRequest.getParameterNames();

		while (parameterNames.hasMoreElements()) {
			String key = parameterNames.nextElement();
			String value = httpServletRequest.getParameter(key);
			resultMap.put(key, value);
		}

		return resultMap;
	}

	private Map<String, String> buildHeadersMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<>();

		@SuppressWarnings("rawtypes")
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}

		return map;
	}
}
