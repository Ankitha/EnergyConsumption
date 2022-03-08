package com.zenhomes.Consumption.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.zenhomes.Consumption.entity.Counter;


@Repository
public interface CounterRepository extends CrudRepository<Counter, Integer> {

     @Query(value = "SELECT sum(amount), villageId FROM Counter c WHERE c.consumptiondate >= :startDate "
             + "AND c.consumptiondate < :endDate group by villageId",nativeQuery=true)
     List<Object[]> getConsumptionReportForPast24h(@Param("startDate") LocalDateTime startDate,
             @Param("endDate") LocalDateTime endDate);

     @Query(value = "SELECT * FROM Counter c WHERE c.counterId = :counterId ",nativeQuery=true)
     List<Counter> getCounterDetailsForCounterId(@Param("counterId") int counterId);


}
