package ru.zarwlad.distriblocationnormalizer.connector;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
@Slf4j
@Component
public class RerunBatches {
    @Autowired
    OkHttpClient okHttpClient;

    public void rerun(MultipartFile multipartFile){
        byte[] bytes = new byte[0];
        try {
            bytes = multipartFile.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> s = Arrays.asList(new String(bytes).split("\n"));

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), "");

        s.forEach(x -> {
            Request request = new Request.Builder()
                    .get()
                    .url("https://demo-sanofi.digital.utrace.ru/api/v1/short-distribution-process/batch/" + x.trim())
                    .build();
            try {
                Response response = okHttpClient.newCall(request).execute();
                response.close();
                log.info("Отправлен запрос {}", x);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }
}
