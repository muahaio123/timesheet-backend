package com.beaconfire.documentservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@Configuration
@PropertySource("classpath:application.properties")
public class HibernateProperty {

    @Value("${database.jdbc.url}")
    private String hibernateUrl;

    @Value("${database.hibernate.driver}")
    private String driver;

    @Value("${database.jdbc.username}")
    private String hibernateUsername;

    @Value("${database.jdbc.password}")
    private String hibernatePassword;

    @Value("${database.hibernate.dialect}")
    private String dialect;

    @Value("${database.hibernate.showsql}")
    private String showSql;

    @Value("${database.hibernate.hbm2ddl.auto}")
    private String hbm2ddl;

}
