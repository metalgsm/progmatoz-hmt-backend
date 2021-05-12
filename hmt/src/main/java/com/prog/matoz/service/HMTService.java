package com.prog.matoz.service;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.prog.matoz.config.HMTConfig;
import com.prog.matoz.data.vo.ErrorVO;
import com.prog.matoz.entity.Elastic;
import com.prog.matoz.repository.ElasticClient;
import com.prog.matoz.security.exception.ObjectErrorException;

import feign.Feign;
import feign.FeignException;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class HMTService {

	private HMTConfig config;

	public void handlerError(ErrorVO vo) {
		var separator = "-";
		var path = new StringBuilder();

		path.append(vo.getClient());
		path.append(separator);
		path.append(vo.getProduct());
		path.append(separator);
		path.append(vo.getService());

		createIndexError(path.toString());

		ElasticClient client = elasticBuilder();

		client.handlerError(ErrorVO.INDEX + path + "/_doc", vo);
	}
	
	public void deleteIndex(String index) {
		ElasticClient client = elasticBuilder();
		
		client.delete(index);
	}

	private void createIndexError(String path) {
		ElasticClient client = elasticBuilder();
		Elastic elastic = new Gson().fromJson(config.getElasticIndexError(), Elastic.class);
		try {
			client.create(ErrorVO.INDEX + path, elastic);
		} catch (FeignException e) {
			if (400 != e.status()) {
				throw new ObjectErrorException(500, e);
			}
		}
	}

	private ElasticClient elasticBuilder() {
		return Feign.builder().client(new OkHttpClient()).encoder(new GsonEncoder()).decoder(new GsonDecoder())
				.logger(new Slf4jLogger(ElasticClient.class)).logLevel(Logger.Level.FULL)
				.target(ElasticClient.class, config.getElasticUri());
	}
}