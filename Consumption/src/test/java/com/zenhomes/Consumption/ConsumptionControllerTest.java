package com.zenhomes.Consumption;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.zenhomes.Consumption.controller.EnergyConsumptionController;
import com.zenhomes.Consumption.entity.ConsumptionData;
import com.zenhomes.Consumption.entity.Counter;
import com.zenhomes.Consumption.entity.Village;
import com.zenhomes.Consumption.service.ConsumptionServiceImpl;


public class ConsumptionControllerTest {

    @Mock
    ConsumptionServiceImpl consumptionService = Mockito.mock(ConsumptionServiceImpl.class);

    private Counter counterData(){
        Counter counter = new Counter();
        counter.setCntId(1);
        counter.setCounterId(1);
        counter.setAmount(1000.0);
        Village v = new Village();
        v.setVillageName("Mitte");
        v.setVillageId(1);
        counter.setVillage(v);
        return  counter;
    }

    private Village villageData(){
        Village v = new Village();
        v.setVillageId(1);
        v.setVillageName("Mitte");
        return v;
    }

    private List<ConsumptionData> consumptionReport(){
        List<ConsumptionData> consumptionDataList = new ArrayList<>();

        ConsumptionData c1 = new ConsumptionData();
        c1.setConsumption(10000.0);
        c1.setVillageName("Mitte");
        consumptionDataList.add(c1);

        ConsumptionData c2 = new ConsumptionData();
        c2.setConsumption(20000.0);
        c2.setVillageName("Berlin");
        consumptionDataList.add(c2);

        ConsumptionData c3 = new ConsumptionData();
        c3.setConsumption(30000.0);
        c3.setVillageName("Aalen");
        consumptionDataList.add(c3);

        ConsumptionData c4 = new ConsumptionData();
        c4.setConsumption(40000.0);
        c4.setVillageName("Bremen");
        consumptionDataList.add(c4);

        ConsumptionData c5 = new ConsumptionData();
        c5.setConsumption(50000.0);
        c5.setVillageName("Coburg");
        consumptionDataList.add(c5);

        return consumptionDataList;
    }

    @BeforeAll
    public static void init(){
        ConsumptionServiceImpl consumptionService = Mockito.mock(ConsumptionServiceImpl.class);
    }

    @Test
    public void testCreateCounter()
    {
        Counter counter = new Counter();
        counter.setCounterId(1);
        counter.setAmount(1000.0);

        EnergyConsumptionController energyConsumptionController = new EnergyConsumptionController();
        when(consumptionService.saveEnergyConsumed(any())).thenReturn(counterData());
        energyConsumptionController.setConsumptionService(consumptionService);
        ResponseEntity<Counter> returnResponse=energyConsumptionController.handleCreateConsumption(counter);
        ResponseEntity<Counter> responseEntity=ResponseEntity.status(HttpStatus.CREATED).body(counterData());
        Assertions.assertEquals(responseEntity.getStatusCode(),returnResponse.getStatusCode());
    }

    @Test
    public void testGetVillageData(){
        Village v = new Village();
        v.setVillageId(1);
        v.setVillageName("Mitte");

        EnergyConsumptionController energyConsumptionController = new EnergyConsumptionController();
        when(consumptionService.getVillageDetailsForCounter(1)).thenReturn(villageData());
        energyConsumptionController.setConsumptionService(consumptionService);
        ResponseEntity<Village> returnResponse=energyConsumptionController.handleVillageDetailsForCounter(1);
        ResponseEntity<Village> responseEntity=ResponseEntity.status(HttpStatus.OK).body(v);
        Assertions.assertEquals(responseEntity.getStatusCode(),returnResponse.getStatusCode());
    }

    @Test
    public void testHandleConsumptionForADay(){
        EnergyConsumptionController energyConsumptionController = new EnergyConsumptionController();
        when(consumptionService.getConsumptionReportForDuration(12)).thenReturn(consumptionReport());
        energyConsumptionController.setConsumptionService(consumptionService);
        ResponseEntity<List<ConsumptionData>> returnResponse=energyConsumptionController.handleConsumptionForADay(12);
        ResponseEntity<List<ConsumptionData>>responseEntity=
                ResponseEntity.status(HttpStatus.OK).body(consumptionReport());
        Assertions.assertEquals(responseEntity.getStatusCode(),returnResponse.getStatusCode());
    }
}
