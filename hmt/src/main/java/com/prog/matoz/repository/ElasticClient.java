package com.prog.matoz.repository;

import com.prog.matoz.data.vo.ErrorVO;
import com.prog.matoz.entity.Elastic;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

@Headers("Content-Type: application/json")
public interface ElasticClient {

	@RequestLine("PUT /{indexName}")
	void create(@Param("indexName") String indexName, Elastic body);
	
	@RequestLine("POST /{indexName}")
	void handlerError(@Param("indexName") String indexName, ErrorVO body);
}
