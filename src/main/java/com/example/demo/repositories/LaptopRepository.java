package com.example.demo.repositories;

import com.example.demo.entities.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LaptopRepository extends JpaRepository<Laptop,Integer> {
    @Query("SELECT l FROM Laptop l WHERE l.manufacturer.name = :name")
    List<Laptop> getLaptopByManufacturerName(@Param("name") String name);
    @Query("SELECT l FROM Laptop l WHERE l.processor.model = :model")
    List<Laptop> getLaptopByProcessorModel(@Param("model") String model);
}
