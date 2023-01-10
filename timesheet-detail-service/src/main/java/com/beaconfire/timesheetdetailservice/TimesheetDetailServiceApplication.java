package com.beaconfire.timesheetdetailservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TimesheetDetailServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimesheetDetailServiceApplication.class, args);
	}

}
