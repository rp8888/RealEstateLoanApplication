package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	  @Bean
	    public Docket api() { 
	        return new Docket(DocumentationType.SWAGGER_2)  
	          .apiInfo(metaData())
	          .select()
	          .apis(RequestHandlerSelectors.basePackage("com.example.demo"))
	          .paths(PathSelectors.any())                          
	          .build();                                           
	    }
	 
	 private ApiInfo metaData() {
		 return new ApiInfoBuilder()
				 .title("Real Estate Loan Application")
		            .description("Provides real estate loan to the customers")
		            .license("DNB")
		            .version("1.0.0")
		            .build();
	 }

}
