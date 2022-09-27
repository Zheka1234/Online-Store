package com.boss.configuration;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


import javax.sql.DataSource;

@Configuration
public class ConnectionPoolConfig {

//
//
//    @Bean
//    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//
//
//    @Bean
//    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
//        return new NamedParameterJdbcTemplate(dataSource);
//    }
//
//
//    @Bean
//    public DataSource hikariDatasource(DatabaseProperties databaseConfig) {
//        HikariDataSource hikariDataSource = new HikariDataSource();
//
//        hikariDataSource.setJdbcUrl(databaseConfig.getUrl());
//        hikariDataSource.setUsername(databaseConfig.getLogin());
//        hikariDataSource.setPassword(databaseConfig.getPassword());
//        hikariDataSource.setDriverClassName(databaseConfig.getDriverName());
//        hikariDataSource.setMaximumPoolSize(10);
//
//        return hikariDataSource;
//    }
}
