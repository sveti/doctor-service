package com.example.doctorservice;

import controller.DoctorController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import service.AppointmentService;
import service.DoctorService;

@ComponentScan(basePackageClasses = DoctorController.class)
@ComponentScan(basePackageClasses = DoctorService.class)
@ComponentScan(basePackageClasses = AppointmentService.class)
@EnableEurekaClient
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class DoctorServiceApplication {


    @Bean
    @LoadBalanced
    WebClient.Builder getWebclientBuilder() {
        return WebClient.builder();
    }

    public static void main(String[] args) {
        SpringApplication.run(DoctorServiceApplication.class, args);
    }

}
