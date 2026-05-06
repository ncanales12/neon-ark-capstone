package com.neonark.neonarkcapstone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// turning off database for now so app can start clean
@SpringBootApplication(excludeName = {
        "org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration",
        "org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration"
})
public class NeonArkCapstoneApplication {

    public static void main(String[] args) {
        SpringApplication.run(NeonArkCapstoneApplication.class, args);
    }
}