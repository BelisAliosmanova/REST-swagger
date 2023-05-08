package com.example.demo.services;

import com.example.demo.entities.Laptop;
import com.example.demo.repositories.LaptopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LaptopService {
    private final LaptopRepository laptopRepository;

    public List<Laptop> getAllLaptops() {
        return laptopRepository.findAll();
    }

    public Laptop addLaptop(Laptop laptop) {
        return laptopRepository.save(laptop);
    }

    public Optional<Laptop> getLaptopById(int id) {
        return laptopRepository.findById(id);
    }

    public List<Laptop> getLaptopByManufacturerName(String manufacturerName) {
        return laptopRepository.getLaptopByManufacturerName(manufacturerName);
    }

    public List<Laptop> getLaptopByProcessorModel(String model) {
        return laptopRepository.getLaptopByProcessorModel(model);
    }

    public void deleteLaptopById(int id) {
        laptopRepository.deleteById(id);
    }

    public Laptop updateLaptop(int id, Laptop updatedLaptop) throws ChangeSetPersister.NotFoundException {
        Laptop laptopToUpdate = laptopRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        laptopToUpdate.setManufacturer(updatedLaptop.getManufacturer());
        laptopToUpdate.setModel(updatedLaptop.getModel());
        laptopToUpdate.setYearOfManufacture(updatedLaptop.getYearOfManufacture());
        laptopToUpdate.setPrice(updatedLaptop.getPrice());
        laptopToUpdate.setRAM(updatedLaptop.getRAM());
        laptopToUpdate.setHDD(updatedLaptop.getHDD());
        laptopToUpdate.setProcessor(updatedLaptop.getProcessor());
        return laptopRepository.save(laptopToUpdate);
    }
}
