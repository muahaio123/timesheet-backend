package com.beaconfire.timesheetcompositeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TimesheetCompositeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimesheetCompositeServiceApplication.class, args);
	}

}
