package com.prog.matoz.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prog.matoz.data.vo.ErrorVO;
import com.prog.matoz.service.HMTService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
@RequestMapping("/error")
@Api(tags = "HMT - Error Handler")
@Data
@AllArgsConstructor
public class HMTErrorController {

private HMTService service;
	
	@PostMapping(value = "/error-handler", produces = { "application/json", "application/xml", "application/x-yaml" })
	public void create(@RequestBody ErrorVO vo) {
		service.handlerError(vo);
	}
}
