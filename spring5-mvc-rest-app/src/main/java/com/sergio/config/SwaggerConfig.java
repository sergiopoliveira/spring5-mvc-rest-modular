package com.sergio.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig { //} extends WebMvcConfigurationSupport {

	// to find the json generated
	// http://localhost:8080/v2/api-docs
	
	// to find Swagger UI
	// http://localhost:8080/swagger-ui.html
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.pathMapping("/")
				.apiInfo(metaData());
	}
	
	private ApiInfo metaData() {
		
		Contact contact = new Contact("Sérgio Oliveira", "https://github.com/sergiopoliveira", "sergio@oliveira.com");
		
		return new ApiInfo(
			"Spring Boot REST API",
			"Learning Spring Boot and REST web services",
			"1.0",
			"Terms of Service",
			contact,
			"Apache License Version 2.0",
			"http://www.apache.org/license/LICENSE-2.0",
			new ArrayList<>());
		
	}
	
//  @Override
//  protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//      registry.addResourceHandler("swagger-ui.html")
//              .addResourceLocations("classpath:/META-INF/resources/");
//
//      registry.addResourceHandler("/webjars/**")
//              .addResourceLocations("classpath:/META-INF/resources/webjars/");
//  }
}
