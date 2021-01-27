package ru.zarwlad.distriblocationnormalizer.connector;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

@Component
@Slf4j
public class TradeItemAndBatchToCsv {
    public void toCsv(MultipartFile multipartFile){
        byte[] bytes = new byte[0];
        try {
            bytes = multipartFile.getBytes();
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
        }

        List<String> s = Arrays.asList(new String(bytes).split("\n"));

        try {
            Path csvTradeItem = Files.createFile(Paths.get("src/data/trade_item.csv"));
            Path csvBatch = Files.createFile(Paths.get("src/data/batch.csv"));

            String legalEntityId = UUID.randomUUID().toString();

            writeLineToFile(csvTradeItem, "id;gtin;name;activity_status;legal_entity_id");
            writeLineToFile(csvBatch, "id;trade_item_id;date_mdf;date_exp;batch_or_lot;activity_status;processed_at");

            Set<TradeItemCsv> tradeItemCsvs = new HashSet<>();

            s.stream().skip(1).forEach(x -> {
                List<String> line = Arrays.asList(x.split(";"));

                TradeItemCsv tradeItemCsv = tradeItemCsvs.stream()
                        .filter(setLine -> setLine.getTradeItemGtin().equals(line.get(2)))
                        .findFirst()
                        .orElse(null);

                if (tradeItemCsv == null){
                    tradeItemCsv = new TradeItemCsv(
                            UUID.randomUUID().toString(),
                            line.get(2),
                            line.get(3)
                    );
                    tradeItemCsvs.add(tradeItemCsv);
                }

                String batchId = UUID.randomUUID().toString();
                String gtinForBatch = line.get(2);
                String batchOrLot = line.get(6);
                String expDate = line.get(7);
                String processedAt = "NOW()";
                String activityStatus = "ACTIVE";
                String dateMdf = "";

                writeLineToFile(csvBatch,
                        batchId +
                                ";" +
                                Objects.requireNonNull(tradeItemCsvs.stream()
                                        .filter(a -> a.getTradeItemGtin().equals(gtinForBatch))
                                        .findFirst().orElse(null)).getTradeItemId() +
                                ";" +
                                dateMdf +
                                ";" +
                                expDate +
                                ";" +
                                batchOrLot +
                                ";" +
                                activityStatus +
                                ";" +
                                processedAt
                        );

            });

            tradeItemCsvs.forEach( x -> {
                writeLineToFile(csvTradeItem,
                        x.getTradeItemId() +
                                ";" +
                                x.getTradeItemGtin() +
                                ";" +
                                x.getTradeItemName() +
                                ";" +
                                "ACTIVE" +
                                ";" +
                                legalEntityId);
            });

        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
        }

    }

    private static void writeLineToFile(Path path, String string){
        try {
            Files.writeString(path, string + "\n", StandardOpenOption.APPEND);
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode(of = "tradeItemGtin")
    private class TradeItemCsv{
        String tradeItemId;
        String tradeItemGtin;
        String tradeItemName;

    }
}
