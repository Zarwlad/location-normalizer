package ru.zarwlad.distriblocationnormalizer.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.zarwlad.distriblocationnormalizer.connector.RerunBatches;
import ru.zarwlad.distriblocationnormalizer.connector.TradeItemAndBatchToCsv;

@RestController
@RequestMapping("/to-csv")
public class CsvTradeItemBatchController {
    @Autowired
    private TradeItemAndBatchToCsv tradeItemAndBatchToCsv;

    @PostMapping("/parse")
    public void rerun(@RequestParam("file") MultipartFile multipartFile){
        tradeItemAndBatchToCsv.toCsv(multipartFile);
    }
}
