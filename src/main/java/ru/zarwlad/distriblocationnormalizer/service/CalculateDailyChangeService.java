package ru.zarwlad.distriblocationnormalizer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.zarwlad.distriblocationnormalizer.entity.*;
import ru.zarwlad.distriblocationnormalizer.repository.*;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class CalculateDailyChangeService {
    private final BaseShortDistributionLineViewRepository baseShortDistributionLineViewRepository;
    private final LocationTurnoverRepository locationTurnoverRepository;
    private final BatchRepository batchRepository;
    private final TrueShortDistributionLocationRepository trueShortDistributionLocationRepository;
    private final TurnoverTaskRepository turnoverTaskRepository;

    @Scheduled(fixedRate = 1)
    public void strt(){
      calculateDailyChange(0);
    }

    @Scheduled(fixedRate = 1)
    public void start(){
      calculateDailyChange(1);
    }

    @Scheduled(fixedRate = 1)
    public void s(){
        calculateDailyChange(2);
    }

    @Scheduled(fixedRate = 1)
    public void se(){
        calculateDailyChange(3);
    }

    @Scheduled(fixedRate = 1)
    public void sc(){
        calculateDailyChange(4);
    }

    @Scheduled(fixedRate = 1)
    public void sdv(){
        calculateDailyChange(5);
    }

    @Scheduled(fixedRate = 1)
    public void sae(){
        calculateDailyChange(6);
    }

    @Scheduled(fixedRate = 1)
    public void sac(){
        calculateDailyChange(7);
    }

    @Scheduled(fixedRate = 1)
    public void saac(){
        calculateDailyChange(8);
    }

    @Scheduled(fixedRate = 1)
    public void sadc(){
        calculateDailyChange(9);
    }

    @Scheduled(fixedRate = 1)
    public void scac(){
        calculateDailyChange(10);
    }


    @Transactional
    //@Scheduled(fixedRate = 1)
    //@Async
    public void calculateDailyChange(int i){
        TurnoverTask turnoverTask = turnoverTaskRepository.findFirstByProcessedFalseAndNumOrderByDayAsc(i).orElse(null);
        log.info("Processing turnoverTask: {}", turnoverTask.toString());

            List<BaseShortDistributionLineView> baseShortDistributionLineViews = baseShortDistributionLineViewRepository
                    .findAllByBatchStatusAndBatchIdAndShortDistributionLocationIdOrderByCreatedAt(
                            turnoverTask.getBatchStatus(),
                            turnoverTask.getBatchId(),
                            turnoverTask.getShortDistributionLocationId());

            BaseShortDistributionLineView line = baseShortDistributionLineViews.stream()
                    .filter(x -> x.getDay().equals(turnoverTask.getDay())).findFirst().orElseThrow();

            BaseShortDistributionLineView prevLine = null;
            if (!baseShortDistributionLineViews.get(0).equals(line)) {
                int l = baseShortDistributionLineViews.indexOf(line) - 1;
                prevLine = baseShortDistributionLineViews
                        .get(l);
            }

        LocationTurnover locationTurnover = new LocationTurnover();
        locationTurnover.setBatchId(turnoverTask.getBatchId());
        locationTurnover.setQty(line.getBalanceAmount());
        locationTurnover.setShortDistributionLocationId(turnoverTask.getShortDistributionLocationId());
        locationTurnover.setDay(turnoverTask.getDay());
        locationTurnover.setStatus(turnoverTask.getBatchStatus());

        if (prevLine == null)
            locationTurnover.setChangeToPrev(0);
        else
            locationTurnover.setChangeToPrev(line.getBalanceAmount() - prevLine.getBalanceAmount());

        log.info("Turnover calculated: {}", locationTurnover.toString());
        locationTurnoverRepository.save(locationTurnover);
        turnoverTask.setProcessed(true);
        turnoverTaskRepository.save(turnoverTask);
    }

    /*
    @Transactional
    @Scheduled(fixedRate = 1)
    @Async
    public void calculateDailyChangeInMemory(){
        List<LocationTurnover> locationTurnovers = locationTurnoverRepository.findAll();
        log.info("Calculated locationTurnovers {} rows", locationTurnovers.size());

        List<BaseShortDistributionLineView> baseShortDistributionLineViews = baseShortDistributionLineViewRepository
                .findAllByOrderByCreatedAtDesc()
                .stream()
                .filter(x -> locationTurnovers
                        .parallelStream().noneMatch(locationTurnover -> locationTurnover.getBatchId().equals(x.getBatchId())
                                && locationTurnover.getDay().equals(x.getDay())
                                && locationTurnover.getShortDistributionLocationId().equals(x.getShortDistributionLocationId())))
                .collect(Collectors.toList());
        log.info("Selected baseShortDistributionLineViews {} rows", baseShortDistributionLineViews.size());

        List<LocationTurnover> newLocationTurnovers = baseShortDistributionLineViews
                .stream()
                .map(x -> {
                    LocationTurnover locationTurnover = new LocationTurnover();
                    locationTurnover.setDay(x.getDay());
                    locationTurnover.setQty(x.getBalanceAmount());
                    locationTurnover.setStatus(x.getBatchStatus());
                    locationTurnover.setShortDistributionLocationId(x.getShortDistributionLocationId());
                    locationTurnover.setBatchId(x.getBatchId());


                    BaseShortDistributionLineView prevLine = baseShortDistributionLineViews
                            .stream()
                            .filter(pl -> pl.getDay().isBefore(x.getDay())
                                    && pl.getBatchId().equals(x.getBatchId())
                                    && pl.getShortDistributionLocationId().equals(x.getShortDistributionLocationId()))
                            .findFirst()
                            .orElse(null);
                    log.info("Prev line for line {} is {}", x.toString(), prevLine != null ? toString() : null);

                    if (prevLine != null){
                        locationTurnover.setChangeToPrev(x.getBalanceAmount() - prevLine.getBalanceAmount());
                    } else {
                        locationTurnover.setChangeToPrev(0);
                    }
                    return locationTurnover;
                })
                .collect(Collectors.toList());
        log.info("Calculated size of turnovers {}", newLocationTurnovers.size());
        locationTurnoverRepository.saveAll(newLocationTurnovers);
    }
     */
}