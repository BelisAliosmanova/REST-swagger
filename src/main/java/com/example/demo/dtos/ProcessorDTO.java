package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessorDTO {
    private String brand;
    private String model;
    private int numberOfCores;
    private int processorFrequency;
}
