package com.befriend.befriend;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class BefriendApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(BefriendApplication.class);

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.username}")
    private String datasourceUsername;

    public static void main(String[] args) {
        SpringApplication.run(BefriendApplication.class, args);
    }

    @Override
    public void run(String... args) {
        logger.info("Datasource URL: {}", datasourceUrl);
        logger.info("Datasource Username: {}", datasourceUsername);
    }
}