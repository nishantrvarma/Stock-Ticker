package com.project.stockMonitor.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.HashSet;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserStockData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String name;
    private HashSet<String> stockList;

    public UserStockData(String name, HashSet<String> stockList) {
        this.name = name;
        this.stockList = stockList;
    }

}
