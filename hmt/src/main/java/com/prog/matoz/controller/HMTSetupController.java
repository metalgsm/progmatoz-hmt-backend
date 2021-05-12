package com.prog.matoz.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prog.matoz.service.HMTService;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
@RequestMapping("/setup")
@Api(tags = "HMT - SETUP")
@Data
@AllArgsConstructor
public class HMTSetupController {

	private HMTService service;
	
	@DeleteMapping
	public void delete(@RequestParam String indexName) {
		service.deleteIndex(indexName);
	}
	
//	@PostMapping(value = "/error-index", produces = { "application/json", "application/xml", "application/x-yaml" })
//	public void create() {
//		service.createIndexError();
//	}
}
