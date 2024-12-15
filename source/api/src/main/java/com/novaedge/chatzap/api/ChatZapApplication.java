package com.novaedge.chatzap.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("/**")
@SpringBootApplication
//public class ChatZapApplication implements CommandLineRunner {
public class ChatZapApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(ChatZapApplication.class, args);
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    	return builder.sources(ChatZapApplication.class);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        startAngularApp();
//    }
//
//    private void startAngularApp() {
//        ProcessBuilder processBuilder = new ProcessBuilder();
//        processBuilder.command("cmd.exe", "/c", "cd ChatZap_web && npm start");
//        processBuilder.inheritIO();
//        try {
//            Process process = processBuilder.start();
//            System.out.println("2024-07-14 14:25:54.247  INFO 14600 --- [           main] Angular project is running");
//    
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
