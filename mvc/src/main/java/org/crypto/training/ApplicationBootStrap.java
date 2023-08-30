package org.crypto.training;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@ServletComponentScan(basePackages = {"org.crypto.training.filter"})
@SpringBootApplication(scanBasePackages = {"org.crypto.training"})
public class ApplicationBootStrap extends SpringBootServletInitializer {
    public static void  main(String[] args) {
        SpringApplication.run(ApplicationBootStrap.class, args);
    }
}
