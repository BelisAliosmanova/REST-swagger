package com.example.demo.entities;

import jakarta.persistence.*;

@Entity
public class Processor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String brand;
    private String model;
    private int numberOfCores;
    @Column(name = "frequency")
    private int processorFrequency;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getNumberOfCores() {
        return numberOfCores;
    }

    public void setNumberOfCores(int numberOfCores) {
        this.numberOfCores = numberOfCores;
    }

    public int getProcessorFrequency() {
        return processorFrequency;
    }

    public void setProcessorFrequency(int processorFrequency) {
        this.processorFrequency = processorFrequency;
    }
}
