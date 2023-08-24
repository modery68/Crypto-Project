package org.crypto.training.service;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.crypto.training.ApplicationBootStrap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootStrap.class)
public class FileServiceTest {

    @InjectMocks
    private FileService fileService;

    @Mock
    private AmazonS3 s3Client;

    @Mock
    private MultipartFile file;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        reset(s3Client, file);
    }
    @Test
    public void uploadFileTest_happyPath() throws IOException {

        fileService.uploadFile("s3-test-bu", file);
        verify(s3Client, times(1)).putObject(any(PutObjectRequest.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void uploadFileTest_fileIsNull() throws IOException{
        fileService.uploadFile("s3-test-bu", null);
    }
}
