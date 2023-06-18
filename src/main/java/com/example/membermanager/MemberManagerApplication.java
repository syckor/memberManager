package com.example.membermanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MemberManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberManagerApplication.class, args);
    }

}
