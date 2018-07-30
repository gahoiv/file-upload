package com.manipal.pics;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.manipal.pics.file.FileStorageProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
@EnableScheduling
public class PicsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PicsApplication.class, args);
		System.out.println("Initializing Application");
	}
}
