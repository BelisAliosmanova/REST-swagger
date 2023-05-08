package com.example.demo;

import com.example.demo.entities.Manufacturer;
import com.example.demo.repositories.ManufacturerRepository;
import com.example.demo.services.ManufacturerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ManufacturerServiceTest {

    @Mock
    private ManufacturerRepository manufacturerRepository;

    @InjectMocks
    private ManufacturerService manufacturerService;

    @Test
    void testGetAllManufacturers() {
        List<Manufacturer> manufacturers = Arrays.asList(
                new Manufacturer(),
                new Manufacturer()
        );
        Mockito.when(manufacturerRepository.findAll()).thenReturn(manufacturers);
        List<Manufacturer> result = manufacturerService.getAllManufacturers();
        assertEquals(manufacturers, result);
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
        Optional<Manufacturer> result = manufacturerService.getManufacturerById(manufacturerId);
        assertTrue(result.isPresent());
        assertEquals(manufacturer, result.get());
        Mockito.verify(manufacturerRepository).findById(manufacturerId);
    }

    @Test
    void testGetManufacturerByIdNotFound() {
        int manufacturerId = 1;
        Mockito.when(manufacturerRepository.findById(manufacturerId)).thenReturn(Optional.empty());
        Optional<Manufacturer> result = manufacturerService.getManufacturerById(manufacturerId);
        assertFalse(result.isPresent());
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
        Manufacturer result = manufacturerService.updateManufacturer(manufacturerId, updatedManufacturer);
        assertEquals(updatedManufacturer, result);
        Mockito.verify(manufacturerRepository).findById(manufacturerId);
        Mockito.verify(manufacturerRepository).save(Mockito.any(Manufacturer.class));
    }
}
