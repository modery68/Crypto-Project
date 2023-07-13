package org.crypto.training.util;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.crypto.training"})
public class ApplicationBootStrap {
    public static void  main(String[] args) {
        SpringApplication.run(ApplicationBootStrap.class, args);
    }
}
