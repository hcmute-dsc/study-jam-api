package com.gdsc.studyjamapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
public class StudyJamApiApplication {
  public static void main(String[] args) {
    SpringApplication.run(StudyJamApiApplication.class, args);
  }
}
