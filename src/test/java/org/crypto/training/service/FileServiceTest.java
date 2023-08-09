package org.crypto.training.service;


import org.crypto.training.util.ApplicationBootStrap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootStrap.class)
public class FileServiceTest {

    @Autowired
    private FileService fileService;

    @Test
    public void uploadFileTest() {
        File file = new File("/Users/zuoyuwei/Downloads/hjkj2.webp");
        fileService.uploadFile(file);
    }
}
