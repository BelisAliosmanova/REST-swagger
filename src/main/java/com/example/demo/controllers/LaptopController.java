package com.example.demo.controllers;

import com.example.demo.entities.Laptop;
import com.example.demo.services.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class LaptopController {
    @Autowired
    LaptopService laptopService;

    @GetMapping("/laptops/all")
    public ResponseEntity<List<Laptop>> getAllManufacturers() {
        return laptopService.getAllLaptops();
    }

    @PostMapping("/laptops/add")
    public ResponseEntity<Laptop> addLaptop(@RequestBody Laptop laptop, UriComponentsBuilder uriComponentsBuilder) {
        return laptopService.addLaptop(laptop, uriComponentsBuilder);
    }
    @GetMapping("/getLaptopMyManufacturerName/{name}")
    public ResponseEntity<List<Laptop>> getLaptopByManufacturerName(@PathVariable("name") String manufacturerName){
        return laptopService.getLaptopByManufacturerName(manufacturerName);
    }

    @GetMapping("/getLaptop/{id}")
    public ResponseEntity<Laptop> getLaptopById(@PathVariable("id") int id) {
        return laptopService.getLaptopById(id);
    }

    @DeleteMapping("/deleteLaptop/{id}")
    public ResponseEntity<Laptop> deleteLaptopById(@PathVariable("id") int id) {
        return laptopService.deleteLaptopById(id);
    }

    @PutMapping("/updateLaptop{id}")
    public ResponseEntity<Laptop> updateLaptop(@PathVariable(value = "id") int id, @RequestBody Laptop updatedLaptop) throws ChangeSetPersister.NotFoundException {
        return laptopService.updateLaptop(id, updatedLaptop);
    }
}
