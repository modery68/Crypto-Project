package org.crypto.training.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@Service
public class FileService {

    private String clientRegion = "us-east-1";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    String bucketName = "s3-test-bu";

    String stringObjKeyName = "sampleFile.txt";

    @Autowired
    AmazonS3 s3Client;

    public String uploadFile(String bucketName, MultipartFile file) throws IOException {
        if (file == null) {
            logger.error("Cannot upload a null file");
            return bucketName;
        }
        PutObjectRequest request = new PutObjectRequest(bucketName,
                file.getOriginalFilename(), file.getInputStream(), null);
        s3Client.putObject(request);
        return getUrl(bucketName, file.getName());

    }

    private String getUrl(String bucketName, String s3Key) {
        URL url = s3Client.getUrl(bucketName, s3Key);

        if (url == null) {
            logger.info("unable to fetch URL");
            return null;
        }

        return url.toString();
    }
}