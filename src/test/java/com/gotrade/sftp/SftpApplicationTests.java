package com.gotrade.sftp;

import com.gotrade.sftp.service.FileSystemService;
import com.jcraft.jsch.ChannelSftp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SftpApplicationTests {
    
    @Autowired
    private FileSystemService fileSystemService;

    @Test
    public void sftpUpload() throws Exception {
        File file = new File("D:\\idea-project\\sftp\\src\\main\\resources\\static\\03709马克思-手机下载学习.pdf");
        InputStream inputStream = new FileInputStream(file);

        boolean uploadFile = fileSystemService.uploadFile("document/4c392-34wsd/34/ID/" + file.getName(), inputStream);
        if (uploadFile) {
            System.out.println("success.....");
        } else {
            System.out.println("failure.....");
        }

        inputStream.close();
    }

    @Test
    public void download() throws Exception {
        File file = fileSystemService.downloadFile("document/4c392-34wsd/95/ID/Vesting approval-tax.docx");
        if (file == null) {
            throw new FileNotFoundException("File not found!");
        }
        System.out.println(file.getName());

        file.delete();
    }

    @Test
    public void deleteFile() throws Exception {
        System.out.println(fileSystemService.deleteFile("document/4c392-34wsd/34/ID/03709马克思-手机下载学习.pdf"));
    }
}
