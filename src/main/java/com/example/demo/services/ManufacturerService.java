package com.example.demo.services;

import com.example.demo.entities.Manufacturer;
import com.example.demo.repositories.ManufacturerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;

    public List<Manufacturer> getAllManufacturers() {
        return manufacturerRepository.findAll();
    }

    public Manufacturer addManufacturer(Manufacturer manufacturer, UriComponentsBuilder uriComponentsBuilder) {
        return manufacturerRepository.save(manufacturer);
    }

    public Optional<Manufacturer> getManufacturerById(int id) {
        return manufacturerRepository.findById(id);
    }

    public void deleteManufacturerById(int id) {
        manufacturerRepository.deleteById(id);
    }

    public Manufacturer updateManufacturer(int id, Manufacturer updatedManufacturer) throws ChangeSetPersister.NotFoundException {
        Manufacturer manufacturerUpdate = manufacturerRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        manufacturerUpdate.setName(updatedManufacturer.getName());
        manufacturerUpdate.setWorkersCount(updatedManufacturer.getWorkersCount());
        manufacturerUpdate.setAnnualBudget(updatedManufacturer.getAnnualBudget());
        manufacturerUpdate.setYearOfFoundation(updatedManufacturer.getYearOfFoundation());
        return manufacturerRepository.save(manufacturerUpdate);
    }

}
