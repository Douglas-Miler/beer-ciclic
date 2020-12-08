package br.com.ciclic.beer_webservice.configuration;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

	@Bean
	public Docket getSwaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/v1/beer/**"))
				.apis(RequestHandlerSelectors.basePackage("br.com.ciclic.beer_webservice"))
				.build()
				.apiInfo(getApiInfo());
	}

	private ApiInfo getApiInfo() {
		return new ApiInfo("Beer API Documentation", 
							"Ciclic's Challenge", 
							"1.0.0", 
							"", 
							new Contact("Douglas Miler", "", "douglas.miler@outlook.com"), 
							"", 
							"", 
							new ArrayList<>());
	}
	
}
