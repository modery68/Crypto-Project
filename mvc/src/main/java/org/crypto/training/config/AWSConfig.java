package org.crypto.training.config;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class AWSConfig {

    @Bean
    public AmazonS3 getAmazonS3() {
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .build();
        return s3Client;
    }

    @Bean
    public AmazonSQS getAmazonSQS() {
        return AmazonSQSClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .build();
    }
}
