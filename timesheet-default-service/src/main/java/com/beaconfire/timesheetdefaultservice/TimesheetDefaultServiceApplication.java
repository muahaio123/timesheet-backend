package com.beaconfire.timesheetdefaultservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TimesheetDefaultServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimesheetDefaultServiceApplication.class, args);
	}

}
