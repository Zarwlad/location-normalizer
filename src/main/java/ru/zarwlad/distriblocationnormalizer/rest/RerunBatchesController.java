package ru.zarwlad.distriblocationnormalizer.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.zarwlad.distriblocationnormalizer.connector.RerunBatches;

@RestController
@RequestMapping("/rerun-batches")
public class RerunBatchesController {
    @Autowired
    private RerunBatches rerunBatches;

    @PostMapping("/rerun")
    public void rerun(@RequestParam("file") MultipartFile multipartFile){
        rerunBatches.rerun(multipartFile);
    }
}
