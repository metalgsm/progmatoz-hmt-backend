package com.prog.matoz.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "prog.matoz.config")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HMTConfig {

	private String timeZone;
	private String elasticIndexError;
	private String elasticUri;
	
}
