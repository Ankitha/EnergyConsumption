package com.zenhomes.Consumption.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.zenhomes.Consumption.entity.Village;


@Repository
public interface VillageRepository extends CrudRepository<Village, Integer> {


}
