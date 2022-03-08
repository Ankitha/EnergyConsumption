package com.zenhomes.Consumption.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.zenhomes.Consumption.entity.ConsumptionData;
import com.zenhomes.Consumption.entity.Counter;
import com.zenhomes.Consumption.entity.Village;


@Service
public interface ConsumptionService {
    public Counter saveEnergyConsumed(Counter consumption) ;

    public Village getVillageDetailsForCounter(int id) ;

    public List<ConsumptionData> getConsumptionReportForDuration(int duration);

    public void saveBulkData() ;
}
