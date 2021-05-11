package com.prog.matoz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prog.matoz.config.Info;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/")
@Api(tags = "Info")
public class InfoController {

	@GetMapping(value = { "resource-status" }, produces = { "application/json",
			"application/xml", "application/x-yaml" })
	public Info resourceStatus() {
		return new Info("up", null);
	}
	
	@GetMapping(value = { "info" }, produces = { "application/json",
			"application/xml", "application/x-yaml" })
	public String info() {
		return "1.0.1";
	}
	
}
