package ru.zarwlad.distriblocationnormalizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DistribLocationNormalizerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistribLocationNormalizerApplication.class, args);
    }

}
