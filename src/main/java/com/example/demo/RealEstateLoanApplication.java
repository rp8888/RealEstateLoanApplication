package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = "com.example.demo")
@Slf4j
public class RealEstateLoanApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealEstateLoanApplication.class, args);
	}

}
