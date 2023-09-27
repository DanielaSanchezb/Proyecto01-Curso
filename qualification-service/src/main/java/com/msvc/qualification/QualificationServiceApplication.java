package com.msvc.qualification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class QualificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QualificationServiceApplication.class, args);
	}

}
