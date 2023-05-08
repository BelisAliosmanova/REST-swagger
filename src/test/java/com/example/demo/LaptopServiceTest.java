package com.example.demo;

import com.example.demo.entities.Laptop;
import com.example.demo.repositories.LaptopRepository;
import com.example.demo.services.LaptopService;
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
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class LaptopServiceTest {
    @Mock
    private LaptopRepository laptopRepository;

    @InjectMocks
    private LaptopService laptopService;

    @Test
    void testGetAllLaptops() {
        List<Laptop> laptops = Arrays.asList(
                new Laptop(),
                new Laptop()
        );
        Mockito.when(laptopRepository.findAll()).thenReturn(laptops);
        List<Laptop> result = laptopService.getAllLaptops();
        assertEquals(laptops, result);
        Mockito.verify(laptopRepository).findAll();
    }
    @Test
    void testAddLaptop() {
        Laptop laptop = new Laptop();
        Mockito.when(laptopRepository.save(Mockito.any(Laptop.class))).thenReturn(laptop);
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();
        Laptop result = laptopService.addLaptop(laptop);
        assertEquals(laptop, result);
        Mockito.verify(laptopRepository).save(Mockito.any(Laptop.class));
    }
    @Test
    void testGetLaptopById() {
        int laptopId = 1;
        Laptop laptop = new Laptop();
        Mockito.when(laptopRepository.findById(laptopId)).thenReturn(Optional.of(laptop));
        Optional<Laptop> result = laptopService.getLaptopById(laptopId);
        assertTrue(result.isPresent());
        assertEquals(laptop, result.get());
        Mockito.verify(laptopRepository).findById(laptopId);
    }

    @Test
    void testGetLaptopByIdNotFound() {
        int laptopId = 1;
        Mockito.when(laptopRepository.findById(laptopId)).thenReturn(Optional.empty());
        Optional<Laptop> result = laptopService.getLaptopById(laptopId);
        assertFalse(result.isPresent());
        Mockito.verify(laptopRepository).findById(laptopId);
    }
    @Test
    void testDeleteLaptopById() {
        int laptopId = 1;
        laptopService.deleteLaptopById(laptopId);
        Mockito.verify(laptopRepository).deleteById(laptopId);
    }
    @Test
    void testUpdateLaptop() throws ChangeSetPersister.NotFoundException {
        int laptopId = 1;
        Laptop originalLaptop = new Laptop();
        Laptop updatedLaptop = new Laptop();
        Mockito.when(laptopRepository.findById(laptopId)).thenReturn(Optional.of(originalLaptop));
        Mockito.when(laptopRepository.save(Mockito.any(Laptop.class))).thenReturn(updatedLaptop);
        Laptop result = laptopService.updateLaptop(laptopId, updatedLaptop);
        assertEquals(updatedLaptop, result);
        Mockito.verify(laptopRepository).findById(laptopId);
        Mockito.verify(laptopRepository).save(Mockito.any(Laptop.class));
    }
}
