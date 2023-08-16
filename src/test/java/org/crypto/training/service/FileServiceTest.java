/*package org.crypto.training.service;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.crypto.training.util.ApplicationBootStrap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootStrap.class)
public class FileServiceTest {

    @Autowired
    private FileService fileService;

    @Autowired
    private AmazonS3 s3Client;

//    @Mock
//   private File file;
    @Test
    public void uploadFileTest_happyPath() {
        File file = new File("/file.txt");
        fileService.uploadFile(file);
        verify(s3Client, times(1)).putObject(any(PutObjectRequest.class));
    }

    @Test
    public void uploadFileTest_fileIsNull() {}
}*/
