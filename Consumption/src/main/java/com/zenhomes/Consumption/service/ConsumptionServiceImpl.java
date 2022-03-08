package com.zenhomes.Consumption.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zenhomes.Consumption.entity.ConsumptionData;
import com.zenhomes.Consumption.entity.Counter;
import com.zenhomes.Consumption.entity.Village;
import com.zenhomes.Consumption.exception.ConsumptionInternalServerError;
import com.zenhomes.Consumption.repository.CounterRepository;
import com.zenhomes.Consumption.repository.VillageRepository;


@Service
public class ConsumptionServiceImpl implements ConsumptionService {

    @Autowired
    CounterRepository counterRepository;

    @Autowired
    VillageRepository villageRepository;

    @Override
    public Counter saveEnergyConsumed(final Counter counter){
        try{
            LocalDateTime now = LocalDateTime.now();
            counter.setConsumptionDate(LocalDateTime.now());
            Optional<Village> village = villageRepository.findById(counter.getCounterId());
            if(village.isPresent()){
                counter.setVillage(village.get());
            }
            return counterRepository.save(counter);
        } catch(Exception e){
            throw new ConsumptionInternalServerError("Error in saving the consumption data");
        }

    }

    @Override
    public Village getVillageDetailsForCounter(final int id) {
        Village village = null;
        try{
            List<Counter> counterList = counterRepository.getCounterDetailsForCounterId(id);
            if(counterList.size()>0){
                village = counterList.get(0).getVillage();
            }
            return village;
        } catch(Exception e){
            throw new ConsumptionInternalServerError("Error in fetching the village details");
        }
    }

    @Override
    public List<ConsumptionData> getConsumptionReportForDuration(int duration) {
        List<ConsumptionData> consumptionReportList = new ArrayList<>();
        try{
            LocalDateTime endTime = LocalDateTime.now();
            LocalDateTime startTime = endTime.minusHours(duration);
            List<Object[]> consumptionDataList =  counterRepository.getConsumptionReportForPast24h(startTime,endTime);

            if(consumptionDataList.size() > 0){
                for(int i=0; i<consumptionDataList.size(); i++){
                    ConsumptionData consumptionData = new ConsumptionData();
                    int villageId = (int) consumptionDataList.get(i)[1];
                    BigInteger amount = (BigInteger) consumptionDataList.get(i)[0];
                    Optional<Village> villageObject = villageRepository.findById(villageId);
                    consumptionData.setVillageName(villageObject.get().getVillageName());
                    consumptionData.setConsumption(Double.valueOf(String.valueOf(amount)));
                    consumptionReportList.add(consumptionData);
                }
            }
        } catch(Exception e){
            throw new ConsumptionInternalServerError("Error in processing the consumption report");
        }
        return consumptionReportList;

    }


    @Override public void saveBulkData(){
        try{
            Random rand = new Random();
            for (int i = 1; i <= 5; i++) {
                Counter counter = new Counter();
                counter.setCounterId(i);
                double val = (Math.random()*((2000-1000)+1))+1000;
                counter.setAmount(val);
                LocalDateTime now = LocalDateTime.now().minus(12, ChronoUnit.HOURS);
                counter.setConsumptionDate(now);
                Optional<Village> village = villageRepository.findById(counter.getCounterId());
                counter.setVillage(village.get());
                counterRepository.save(counter);
            }
        } catch(Exception e){
            throw new ConsumptionInternalServerError("Error in saving the consumption data");
        }
    }

}
