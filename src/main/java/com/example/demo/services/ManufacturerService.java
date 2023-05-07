package com.example.demo.services;

import com.example.demo.entities.Manufacturer;
import com.example.demo.entities.Processor;
import com.example.demo.repositories.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerService {
    @Autowired
    ManufacturerRepository manufacturerRepository;

    public ResponseEntity<List<Manufacturer>> getAllManufacturers() {
        List<Manufacturer> manufacturers = manufacturerRepository.findAll();
        return ResponseEntity.ok(manufacturers);
    }

    public ResponseEntity<Manufacturer> addManufacturer(Manufacturer manufacturer, UriComponentsBuilder uriComponentsBuilder) {
        Manufacturer savedManufacturers = manufacturerRepository.save(manufacturer);
        URI location = uriComponentsBuilder.path("/manufacturers/{id}").buildAndExpand(savedManufacturers.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<Manufacturer> getManufacturerById(int id) {
        Optional<Manufacturer> manufacturer = manufacturerRepository.findById(id);
        return manufacturer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Manufacturer> deleteManufacturerById(int id) {
        manufacturerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Manufacturer> updateManufacturer(int id, Manufacturer manufacturer) {
        Optional<Manufacturer> optionalManufacturer = manufacturerRepository.findById(id);
        if (optionalManufacturer.isPresent()) {
            Manufacturer newManufacturer = optionalManufacturer.get();
            if (manufacturer.getName() != null) {
                newManufacturer.setName(manufacturer.getName());
            }
            if (manufacturer.getYearOfFoundation() != null) {
                newManufacturer.setYearOfFoundation(manufacturer.getYearOfFoundation());
            }
            if (manufacturer.getAnnualBudget() != 0) {
                newManufacturer.setAnnualBudget(manufacturer.getAnnualBudget());
            }
            if (manufacturer.getWorkersCount() != 0) {
                newManufacturer.setWorkersCount(manufacturer.getWorkersCount());
            }
            manufacturerRepository.save(newManufacturer);
            return ResponseEntity.ok(newManufacturer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
