package ru.zarwlad.distriblocationnormalizer.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class OkHttpConfig {
    @Bean
    OkHttpClient okHttpClient(){
        return new OkHttpClient.Builder()
                .connectTimeout(300000, TimeUnit.MILLISECONDS)
                .readTimeout(300000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .writeTimeout(300000, TimeUnit.MILLISECONDS)
                .build();
    }
}
