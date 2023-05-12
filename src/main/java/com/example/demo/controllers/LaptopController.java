package com.example.demo.controllers;

import com.example.demo.dtos.LaptopDTO;
import com.example.demo.entities.Laptop;
import com.example.demo.services.LaptopService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/laptops")
public class LaptopController {
    private final LaptopService laptopService;

    @GetMapping("/all")
    public ResponseEntity<List<LaptopDTO>> getAllLaptops() {
        List<LaptopDTO> laptops = laptopService.getAllLaptops();
        var status = laptops.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return ResponseEntity.status(status).body(laptops);
    }

    @PostMapping("/laptops")
    public ResponseEntity<Void> addLaptop(@RequestBody Laptop laptop, UriComponentsBuilder uriComponentsBuilder) {
        URI location = uriComponentsBuilder.path("/laptops/{id}").buildAndExpand(laptopService.addLaptop(laptop).getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/getLaptop/{id}")
    public ResponseEntity<LaptopDTO> getLaptopById(@PathVariable("id") int id) {
        return ResponseEntity.ok(laptopService.getLaptopById(id));
    }

    @GetMapping("/getLaptopByManufacturerName/{name}")
    public ResponseEntity<List<LaptopDTO>> getLaptopByManufacturerName(@PathVariable("name") String manufacturerName) {
        List<LaptopDTO> laptops = laptopService.getLaptopByManufacturerName(manufacturerName);
        var status = laptops.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return ResponseEntity.status(status).body(laptops);
    }

    @GetMapping("/getLaptopByProcessorModel/{model}")
    public ResponseEntity<List<LaptopDTO>> getLaptopByProcessorModel(@PathVariable("model") String processorModel) {
        List<LaptopDTO> laptops = laptopService.getLaptopByProcessorModel(processorModel);
        var status = laptops.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return ResponseEntity.status(status).body(laptops);
    }

    @DeleteMapping("/deleteLaptop/{id}")
    public ResponseEntity<Laptop> deleteLaptopById(@PathVariable("id") int id) {
        laptopService.deleteLaptopById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/updateLaptop{id}")
    public ResponseEntity<LaptopDTO> updateLaptop(@PathVariable(value = "id") int id, @RequestBody Laptop updatedLaptop) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(laptopService.updateLaptop(id, updatedLaptop));
    }
}
