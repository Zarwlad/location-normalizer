package ru.zarwlad.distriblocationnormalizer.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.zarwlad.distriblocationnormalizer.service.CalculateDailyChangeService;

@Controller
@RequestMapping("/calculate-daily-change")
@RequiredArgsConstructor
public class CalculateDailyChangeController {
    private final CalculateDailyChangeService calculateDailyChangeService;

    @PostMapping("/calc")
    public void calculateDailyChange(){
        calculateDailyChangeService.calculateDailyChange(1);
    }

//    @PostMapping("/multi-calc")
//    public ResponseEntity multiCalc(){
//        calculateDailyChangeService.start();
//        return ResponseEntity.ok().build();
//    }
}
