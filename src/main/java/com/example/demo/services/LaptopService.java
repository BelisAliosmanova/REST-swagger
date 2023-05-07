package com.example.demo.services;

import com.example.demo.entities.Laptop;
import com.example.demo.repositories.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class LaptopService {
    @Autowired
    LaptopRepository laptopRepository;

    public ResponseEntity<List<Laptop>> getAllLaptops() {
        List<Laptop> laptops = laptopRepository.findAll();
        return ResponseEntity.ok(laptops);
    }

    public ResponseEntity<Laptop> addLaptop(Laptop laptop, UriComponentsBuilder uriComponentsBuilder) {
        Laptop savedLaptop = laptopRepository.save(laptop);
        URI location = uriComponentsBuilder.path("/laptops/{id}").buildAndExpand(savedLaptop.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<Laptop> getLaptopById(int id) {
        Optional<Laptop> laptop = laptopRepository.findById(id);
        return laptop.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    public ResponseEntity<List<Laptop>> getLaptopByManufacturerName(String manufacturerName){
        List<Laptop> laptops = laptopRepository.getLaptopByManufacturerName(manufacturerName);
        return ResponseEntity.ok(laptops);
    }

    public ResponseEntity<Laptop> deleteLaptopById(int id) {
        laptopRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Laptop> updateLaptop(int id, Laptop laptop) {
        Optional<Laptop> optionalLaptop = laptopRepository.findById(id);
        if (optionalLaptop.isPresent()) {
            Laptop newLaptop = optionalLaptop.get();
            if (laptop.getModel() != null) {
                newLaptop.setModel(laptop.getModel());
            }
            if (laptop.getYearOfManufacture() != null) {
                newLaptop.setModel(laptop.getYearOfManufacture());
            }
            if (laptop.getPrice() != 0) {
                newLaptop.setPrice(laptop.getPrice());
            }
            if (laptop.getRAM() != 0) {
                newLaptop.setRAM(laptop.getRAM());
            }
            if (laptop.getHDD() != 0) {
                newLaptop.setHDD(laptop.getHDD());
            }
            laptopRepository.save(newLaptop);
            return ResponseEntity.ok(newLaptop);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
