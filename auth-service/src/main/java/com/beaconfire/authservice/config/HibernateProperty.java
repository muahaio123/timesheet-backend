package com.beaconfire.authservice.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Getter
@Configuration
@PropertySource("classpath:application.properties")
public class HibernateProperty {
    @Value("${database.jdbc.url}")
    private String hibernateUrl;

    @Value("${database.jdbc.username}")
    private String hibernateUsername;

    @Value("${database.jdbc.password}")
    private String hibernatePassword;

    @Value("${database.hibernate.dialect}")
    private String dialect;

    @Value("${database.hibernate.showsql}")
    private String showSql;
}
