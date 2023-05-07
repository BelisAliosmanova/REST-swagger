package com.example.demo.services;

import com.example.demo.entities.Laptop;
import com.example.demo.repositories.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
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
    public ResponseEntity<List<Laptop>> getLaptopByProcessorModel(String model){
        List<Laptop> laptops = laptopRepository.getLaptopByProcessorModel(model);
        return ResponseEntity.ok(laptops);
    }

    public ResponseEntity<Laptop> deleteLaptopById(int id) {
        laptopRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    public ResponseEntity<Laptop> updateLaptop(int id, Laptop updatedLaptop) throws ChangeSetPersister.NotFoundException {
        Laptop laptopToUpdate = laptopRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        laptopToUpdate.setManufacturer(updatedLaptop.getManufacturer());
        laptopToUpdate.setModel(updatedLaptop.getModel());
        laptopToUpdate.setYearOfManufacture(updatedLaptop.getYearOfManufacture());
        laptopToUpdate.setPrice(updatedLaptop.getPrice());
        laptopToUpdate.setRAM(updatedLaptop.getRAM());
        laptopToUpdate.setHDD(updatedLaptop.getHDD());
        laptopToUpdate.setProcessor(updatedLaptop.getProcessor());
        Laptop savedLaptop = laptopRepository.save(laptopToUpdate);
        return ResponseEntity.ok(savedLaptop);
    }
}
