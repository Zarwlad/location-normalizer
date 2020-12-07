package ru.zarwlad.distriblocationnormalizer.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.zarwlad.distriblocationnormalizer.connector.DistributionHandler;

@RestController
@RequestMapping("/short-distribution")
public class ShortDistributionController {
    @Autowired
    private DistributionHandler distributionHandler;
    
    @PostMapping("/upload-csv")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
        distributionHandler.createLocationsFromFile(file);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.ACCEPTED);
    }
}
