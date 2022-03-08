package com.zenhomes.Consumption;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan("com.zenhomes.Consumption.*")
public class ConsumptionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumptionApplication.class, args);
	}

}
