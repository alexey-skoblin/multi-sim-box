package com.graduate.work;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MultiSimBoxApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiSimBoxApplication.class, args);
	}

}
