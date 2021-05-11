package com.prog.matoz.data.vo;


import java.util.Map;

import lombok.Data;

@Data
public class ErrorVO {

	private String client;
	private String product;
	private String service;
	private String transaction;
	private String UUID;
	private String key;
	private Map<String, String> requestHeaders;
	private String requestUri;
	private String requestBody;
	private Map<String, String> responseHeaders;
	private String responseUri;
	private Integer responseStatus;
	private String responseBody;
	private String date;
	public static final String INDEX = "hmt-error-handler";
}
