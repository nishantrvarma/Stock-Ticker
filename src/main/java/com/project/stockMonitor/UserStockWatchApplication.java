package com.project.stockMonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UserStockWatchApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(UserStockWatchApplication.class, args);
    }
}