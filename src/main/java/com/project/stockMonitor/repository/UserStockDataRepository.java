package com.project.stockMonitor.repository;

import com.project.stockMonitor.entity.UserStockData;
import org.springframework.data.repository.CrudRepository;


public interface UserStockDataRepository extends CrudRepository<UserStockData, Long> {
    UserStockData findByName(String name);

    Boolean existsByName(String name);

}


