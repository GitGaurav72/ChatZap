package com.novaedge.chatzap.ui;

import java.io.IOException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class UiApplication extends SpringBootServletInitializer implements CommandLineRunner {
//public class UiApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(UiApplication.class, args);
	}
	
  @Override
  public void run(String... args) throws Exception {
      startAngularApp();
  }

  private void startAngularApp() {
      ProcessBuilder processBuilder = new ProcessBuilder();
      processBuilder.command("cmd.exe", "/c", "cd web && npm start");
      processBuilder.inheritIO();
      try {
          Process process = processBuilder.start();
          System.out.println("2024-07-14 14:25:54.247  INFO 14600 --- [           main] Angular project is running");
  
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
}
