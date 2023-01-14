package com.beaconfire.apigateway.config;

import com.beaconfire.apigateway.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHystrix
public class GatewayConfig {
    AuthenticationFilter filter;

    @Autowired
    public GatewayConfig(AuthenticationFilter authenticationFilter) {
        this.filter = authenticationFilter;
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) { // add more routes to other microservices by adding to this one
        return builder.routes()
                .route("auth-service", r -> r.path("/auth-service/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://auth-service"))
                .route("contact-service", r -> r.path("/contact-service/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://contact-service"))
                .route("document-service", r -> r.path("/document-service/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://document-service"))
                .route("employee-service", r -> r.path("/employee-service/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://employee-service"))
                .route("profile-composite-service", r -> r.path("/profile-composite-service/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://profile-composite-service"))
                .route("timesheet-composite-service", r -> r.path("/timesheet-composite-service/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://timesheet-composite-service"))
                .route("timesheet-default-service", r -> r.path("/timesheet-default-service/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://timesheet-default-service"))
                .route("timesheet-detail-service", r -> r.path("/timesheet-detail-service/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://timesheet-detail-service"))
                .route("timesheet-summary-service", r -> r.path("/timesheet-summary-service/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://timesheet-summary-service"))
                .build();
    }
}
