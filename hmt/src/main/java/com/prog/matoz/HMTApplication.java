package com.prog.matoz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
public class HMTApplication {

	public static void main(String[] args) {
		SpringApplication.run(HMTApplication.class, args);
	}
	
	@Bean
	  public Docket docket() {
	    return new Docket(DocumentationType.OAS_30)
	        .apiInfo(new ApiInfoBuilder()
	            .title("Help My Ticket API")
	            .description("HMT Backend API")
	            .version("1.0.1-SNAPSHOT")
	            .license("MIT")
	            .licenseUrl("https://opensource.org/licenses/MIT")
	            .build())
	        .select().apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
	        .build();
	  }

}
