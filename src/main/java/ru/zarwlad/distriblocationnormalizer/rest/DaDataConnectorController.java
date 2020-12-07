package ru.zarwlad.distriblocationnormalizer.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zarwlad.distriblocationnormalizer.connector.DaDataConnector;

@RestController
@RequestMapping("/da-data-connector")
public class DaDataConnectorController {
    @Autowired
    private DaDataConnector daDataConnector;

    @PostMapping("/proccess-locations")
    public ResponseEntity<String> processLocation(){
        daDataConnector.processLocations();
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
}
