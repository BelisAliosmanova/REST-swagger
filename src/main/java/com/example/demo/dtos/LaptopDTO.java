package com.example.demo.dtos;

import com.example.demo.entities.Manufacturer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LaptopDTO {
    private Manufacturer manufacturer;
    private String model;
    private String yearOfManufacture;
    private double price;
    private int RAM;
    private int HDD;
}
