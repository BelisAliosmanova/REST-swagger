package com.example.demo;

import com.example.demo.dtos.ManufacturerDTO;
import com.example.demo.dtos.ProcessorDTO;
import com.example.demo.entities.Manufacturer;
import com.example.demo.repositories.ManufacturerRepository;
import com.example.demo.services.ManufacturerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ManufacturerServiceTest {

    @Mock
    private ManufacturerRepository manufacturerRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private ManufacturerService manufacturerService;

    @Test
    void testGetAllManufacturers() {
        List<Manufacturer> manufacturers = Arrays.asList(
                new Manufacturer(),
                new Manufacturer()
        );
        Mockito.when(manufacturerRepository.findAll()).thenReturn(manufacturers);
        List<ManufacturerDTO> result = manufacturerService.getAllManufacturers();
        List<ManufacturerDTO> manufacturerDTOS = manufacturers.stream().map(prc -> modelMapper.map(prc, ManufacturerDTO.class)).collect(Collectors.toList());
        assertEquals(manufacturerDTOS, result);
        Mockito.verify(manufacturerRepository).findAll();
    }
    @Test
    void testAddManufacturer() {
        Manufacturer manufacturer = new Manufacturer();
        Mockito.when(manufacturerRepository.save(Mockito.any(Manufacturer.class))).thenReturn(manufacturer);
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();
        Manufacturer result = manufacturerService.addManufacturer(manufacturer, uriComponentsBuilder);
        assertEquals(manufacturer, result);
        Mockito.verify(manufacturerRepository).save(Mockito.any(Manufacturer.class));
    }
    @Test
    void testGetManufacturerById() {
        int manufacturerId = 1;
        Manufacturer manufacturer = new Manufacturer();
        Mockito.when(manufacturerRepository.findById(manufacturerId)).thenReturn(Optional.of(manufacturer));
        ManufacturerDTO result = manufacturerService.getManufacturerById(manufacturerId);
        ManufacturerDTO manufacturerDTO = modelMapper.map(manufacturer, ManufacturerDTO.class);
        assertEquals(manufacturerDTO, result);
        Mockito.verify(manufacturerRepository).findById(manufacturerId);
    }

    @Test
    void testDeleteManufacturerById() {
        int manufacturerId = 1;
        manufacturerService.deleteManufacturerById(manufacturerId);
        Mockito.verify(manufacturerRepository).deleteById(manufacturerId);
    }
    @Test
    void testUpdateManufacturer() throws ChangeSetPersister.NotFoundException {
        int manufacturerId = 1;
        Manufacturer originalManufacturer = new Manufacturer();
        Manufacturer updatedManufacturer = new Manufacturer();
        Mockito.when(manufacturerRepository.findById(manufacturerId)).thenReturn(Optional.of(originalManufacturer));
        Mockito.when(manufacturerRepository.save(Mockito.any(Manufacturer.class))).thenReturn(updatedManufacturer);
        ManufacturerDTO result = manufacturerService.updateManufacturer(manufacturerId, updatedManufacturer);
        ManufacturerDTO manufacturerDTO = modelMapper.map(originalManufacturer, ManufacturerDTO.class);
        assertEquals(manufacturerDTO, result);
        Mockito.verify(manufacturerRepository).findById(manufacturerId);
        Mockito.verify(manufacturerRepository).save(Mockito.any(Manufacturer.class));
    }
}
