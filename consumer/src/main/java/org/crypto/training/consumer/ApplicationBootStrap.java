package org.crypto.training.consumer;


import org.crypto.training.consumer.service.SQSMessageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = {"org.crypto.training.consumer"})
public class  ApplicationBootStrap {
    public static void  main(String[] args) {

        SpringApplication.run(ApplicationBootStrap.class, args);

//        ConfigurableApplicationContext app = SpringApplication.run(ApplicationBootStrap.class, args);
//        SQSMessageService messageService = app.getBean(SQSMessageService.class);
//
//        messageService.receiveMessage();
    }
}
