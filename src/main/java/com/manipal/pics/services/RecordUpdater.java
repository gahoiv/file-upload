package com.manipal.pics.services;

import com.manipal.pics.PicsQueue;
import com.manipal.pics.file.FileStorageProperties;
import com.manipal.pics.mongo.StudentRepository;
import com.manipal.pics.mongo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class RecordUpdater{

    @Autowired
    private PicsQueue picsQueue;

    @Autowired
    private FileStorageProperties fileStorageProperties;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private StudentRepository studentRepository;

    private String oldFileName = null;

    //Executes each 500 ms
    @Scheduled(fixedRate=500)
    public void checkRecords() {
        String fileName = picsQueue.getOutputFileName();
        if(fileName != null && !fileName.equals(oldFileName)) {
            oldFileName = fileName;
            readFile(fileName);
            picsQueue.getPicsFileFromQueue();
        }
    }

    private void readFile(String fileName) {
        String outputFileName = fileName;// fileStorageProperties.getOutputPrefix()+fileName;
        try {
            Path filePath = fileStorageService.getFilePath(outputFileName);
            try (BufferedReader br = Files.newBufferedReader(filePath,
                    StandardCharsets.US_ASCII)){
                // read the first line from the text file String line = br.readLine();

                String line = br.readLine();
                // loop until all lines are read
                while (line != null) {
                    String[] attributes = line.split(",");
                    insertUserDetails(attributes);
                    line = br.readLine();

                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            Files.delete(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void insertUserDetails(String[] arg) {
        Student s = new Student(arg[0],arg[1],arg[2],arg[3],arg[4]);
        studentRepository.insert(s);
    }
}
