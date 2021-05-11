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

	public void createIndexError() {
		ElasticClient client = Feign.builder()
				.client(new OkHttpClient())
				.encoder(new GsonEncoder())
				.decoder(new GsonDecoder())
				.logger(new Slf4jLogger(ElasticClient.class))
				.logLevel(Logger.Level.FULL)
				.target(ElasticClient.class, config.getElasticUri());
		
		Elastic elastic = new Gson().fromJson(config.getElasticIndexError(), Elastic.class) ;
		
		try {
			client.create(ErrorVO.INDEX, elastic);
		}catch (FeignException e) {
			if(400 == e.status()) {
				throw new ObjectErrorException(409, null, "Index Already exist");
			}
			
			throw new ObjectErrorException(500, e);
		}
	}
	
	public void handlerError(ErrorVO vo) {
		ElasticClient client = Feign.builder()
				.client(new OkHttpClient())
				.encoder(new GsonEncoder())
				.decoder(new GsonDecoder())
				.logger(new Slf4jLogger(ElasticClient.class))
				.logLevel(Logger.Level.FULL)
				.target(ElasticClient.class, config.getElasticUri());
		
		client.handlerError(ErrorVO.INDEX + "/_doc", vo);
	}
	
}