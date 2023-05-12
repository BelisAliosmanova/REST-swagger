package com.example.demo.services;

import com.example.demo.dtos.LaptopDTO;
import com.example.demo.entities.Laptop;
import com.example.demo.repositories.LaptopRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LaptopService {
    private final LaptopRepository laptopRepository;
    private final ModelMapper modelMapper;

    public List<LaptopDTO> getAllLaptops() {
        List<Laptop> laptops = laptopRepository.findAll();
        return laptops.stream().map(lpt -> modelMapper.map(lpt, LaptopDTO.class)).collect(Collectors.toList());
    }

    public Laptop addLaptop(Laptop laptop) {
        return laptopRepository.save(laptop);
    }

    public LaptopDTO getLaptopById(int id) {
        Laptop laptop = laptopRepository.findById(id).orElseThrow(RuntimeException::new);
        return modelMapper.map(laptop, LaptopDTO.class);
    }

    public List<LaptopDTO> getLaptopByManufacturerName(String manufacturerName) {
        List<Laptop> laptops = laptopRepository.getLaptopByManufacturerName(manufacturerName);
        return laptops.stream().map(lpt -> modelMapper.map(lpt, LaptopDTO.class)).collect(Collectors.toList());
    }

    public List<LaptopDTO> getLaptopByProcessorModel(String model) {
        List<Laptop> laptops = laptopRepository.getLaptopByProcessorModel(model);
        return laptops.stream().map(lpt -> modelMapper.map(lpt, LaptopDTO.class)).collect(Collectors.toList());
    }

    public void deleteLaptopById(int id) {
        laptopRepository.deleteById(id);
    }

    public LaptopDTO updateLaptop(int id, Laptop updatedLaptop) throws ChangeSetPersister.NotFoundException {
        Laptop laptopToUpdate = laptopRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        laptopToUpdate.setManufacturer(updatedLaptop.getManufacturer());
        laptopToUpdate.setModel(updatedLaptop.getModel());
        laptopToUpdate.setYearOfManufacture(updatedLaptop.getYearOfManufacture());
        laptopToUpdate.setPrice(updatedLaptop.getPrice());
        laptopToUpdate.setRAM(updatedLaptop.getRAM());
        laptopToUpdate.setHDD(updatedLaptop.getHDD());
        laptopToUpdate.setProcessor(updatedLaptop.getProcessor());
        laptopRepository.save(laptopToUpdate);
        return modelMapper.map(laptopToUpdate, LaptopDTO.class);
    }
}
