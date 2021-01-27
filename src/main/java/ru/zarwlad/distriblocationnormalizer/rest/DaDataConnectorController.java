package ru.zarwlad.distriblocationnormalizer.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.zarwlad.distriblocationnormalizer.connector.DaDataConnector;

@RestController
@RequestMapping("/da-data-connector")
@Slf4j
public class DaDataConnectorController {
    @Autowired
    private DaDataConnector daDataConnector;

    @PostMapping("/proccess-locations")
    public ResponseEntity<String> processLocation(){
        log.info("Request received");
        daDataConnector.processLocations();
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
    @PostMapping("/proccess-locations-by-address")
    public void rerun(@RequestParam("file") MultipartFile multipartFile){
        daDataConnector.addressesFromCsv(multipartFile);
    }
}
