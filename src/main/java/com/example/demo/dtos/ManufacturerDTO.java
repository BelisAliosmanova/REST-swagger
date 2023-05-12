package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManufacturerDTO {
    private String name;
    private String yearOfFoundation;
    private int workersCount;
    private double annualBudget;
}
