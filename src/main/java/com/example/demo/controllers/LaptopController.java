package com.example.demo.controllers;

import com.example.demo.entities.Laptop;
import com.example.demo.services.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/getLaptop/{id}")
    public ResponseEntity<Laptop> getLaptopById(@PathVariable("id") int id) {
        return laptopService.getLaptopById(id);
    }

    @DeleteMapping("/deleteLaptop/{id}")
    public ResponseEntity<Laptop> deleteLaptopById(@PathVariable("id") int id) {
        return laptopService.deleteLaptopById(id);
    }

    @PutMapping("/updateLaptop{id}")
    public ResponseEntity<Laptop> updateLaptop(int id, @RequestBody Laptop laptop) {
        return laptopService.updateLaptop(id,laptop);
    }
}
