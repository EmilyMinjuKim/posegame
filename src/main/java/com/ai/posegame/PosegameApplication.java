package com.ai.posegame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PosegameApplication {

    public static void main(String[] args) {
        SpringApplication.run(PosegameApplication.class, args);
    }

}
