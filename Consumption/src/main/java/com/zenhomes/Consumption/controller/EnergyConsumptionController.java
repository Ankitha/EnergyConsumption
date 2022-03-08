package com.zenhomes.Consumption.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.zenhomes.Consumption.entity.ConsumptionData;
import com.zenhomes.Consumption.entity.Counter;
import com.zenhomes.Consumption.entity.Village;
import com.zenhomes.Consumption.exception.ConsumptionBadRequest;
import com.zenhomes.Consumption.exception.ConsumptionDataNotFound;
import com.zenhomes.Consumption.service.ConsumptionServiceImpl;
import com.zenhomes.Consumption.util.ValidationUtil;


@RestController
public class EnergyConsumptionController {
    private static Logger log = LoggerFactory.getLogger(EnergyConsumptionController.class);

    @Autowired
    public ConsumptionServiceImpl consumptionService;

    public void setConsumptionService(final ConsumptionServiceImpl consumptionService) {
        this.consumptionService = consumptionService;
    }

    @RequestMapping(value = "/counter_callback", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON,produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<Counter> handleCreateConsumption(@RequestBody @NotNull  Counter consumption){
        log.debug("EnergyConsumptionController - In handle create consumption ");

        if(consumption != null){
            boolean isValid = ValidationUtil.validateConsumptionData(consumption);
            if(isValid){
                Counter createdConsumptionCounter = consumptionService.saveEnergyConsumed(consumption);
                log.info("EnergyConsumptionController - In handle create consumption - "
                        + "Consumption data added successfully"+ createdConsumptionCounter);
                return new ResponseEntity<>(createdConsumptionCounter, HttpStatus.CREATED);
            } else {
                log.error("EnergyConsumptionController - In handle create consumption - "
                        + "Counter object provided is not valid");
                throw new ConsumptionBadRequest("Counter object provided is not valid");
            }
        } else {
            log.error("EnergyConsumptionController - In handle create consumption - Invalid input provided ");
            throw new ConsumptionBadRequest("Invalid input provided");
        }


    }

    @RequestMapping(value = "/counter", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON,produces = MediaType.APPLICATION_JSON)
    public ResponseEntity handleVillageDetailsForCounter(@RequestParam @NotNull int id) {
        log.debug("EnergyConsumptionController - In handle village details for counter ");

        boolean isValid = ValidationUtil.validateCounterId(id);
        if(isValid){
            Village villageDetails = consumptionService.getVillageDetailsForCounter(id);
            if(villageDetails != null){
                log.info("EnergyConsumptionController -  In handle village details for counter "+ villageDetails);
                return new ResponseEntity<>(villageDetails, HttpStatus.OK);
            } else {
                log.error("EnergyConsumptionController - In handle village details for counter - "
                        + "Counter Id does not exist");
                throw new ConsumptionDataNotFound("Counter id is not present");
            }
        } else {
            log.error("EnergyConsumptionController - In handle village details for counter - "
                    + "Invalid input provided");
            throw new ConsumptionBadRequest("Invalid input provided");
        }

    }

    @RequestMapping(value = "/consumption_report", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON,produces = MediaType.APPLICATION_JSON)
    public ResponseEntity handleConsumptionForADay(@RequestParam int duration){
        log.debug("EnergyConsumptionController - In handle consumption for a day ");
        List<ConsumptionData> consumptionData = new ArrayList<>();

        consumptionData = consumptionService.getConsumptionReportForDuration(duration);
        if(consumptionData.size() == 0){
            log.error("EnergyConsumptionController - In handle consumption for a day - Error in processing the "
                    + "consumption");
            throw new ConsumptionDataNotFound("Consumption data not available");
        } else {
            log.info("EnergyConsumptionController - In handle consumption for a day "+ consumptionData);
            return new ResponseEntity<>(consumptionData, HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/loadData", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON,produces = MediaType.APPLICATION_JSON)
    public ResponseEntity handleBulkData() {
        log.debug("EnergyConsumptionController - In handle bulk data ");

        consumptionService.saveBulkData();
        log.info("EnergyConsumptionController - In handle bulk data - Bulk data created");
        return new ResponseEntity("Bulk data created", HttpStatus.OK);

    }
}
