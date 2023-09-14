package com.apprentice.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ApprenticeTechApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApprenticeTechApplication.class, args);
    }

}
