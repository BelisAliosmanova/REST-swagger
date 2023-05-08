package com.example.demo;

import com.example.demo.entities.Processor;
import com.example.demo.repositories.ProcessorRepository;
import com.example.demo.services.ProcessorService;
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
public class ProcessorServiceTest {
    @Mock
    private ProcessorRepository processorRepository;

    @InjectMocks
    private ProcessorService processorService;

    @Test
    void testGetAllProcessors() {
        List<Processor> processors = Arrays.asList(
                new Processor(),
                new Processor()
        );
        Mockito.when(processorRepository.findAll()).thenReturn(processors);
        List<Processor> result = processorService.getAllProcessors();
        assertEquals(processors, result);
        Mockito.verify(processorRepository).findAll();
    }
    @Test
    void testAddProcessor() {
        Processor processor = new Processor();
        Mockito.when(processorRepository.save(Mockito.any(Processor.class))).thenReturn(processor);
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();
        Processor result = processorService.addProcessor(processor, uriComponentsBuilder);
        assertEquals(processor, result);
        Mockito.verify(processorRepository).save(Mockito.any(Processor.class));
    }
    @Test
    void testGetProcessorById() {
        int processorId = 1;
        Processor processor = new Processor();
        Mockito.when(processorRepository.findById(processorId)).thenReturn(Optional.of(processor));
        Optional<Processor> result = processorService.getProcessorById(processorId);
        assertTrue(result.isPresent());
        assertEquals(processor, result.get());
        Mockito.verify(processorRepository).findById(processorId);
    }

    @Test
    void testGetProcessorByIdNotFound() {
        int processorId = 1;
        Mockito.when(processorRepository.findById(processorId)).thenReturn(Optional.empty());
        Optional<Processor> result = processorService.getProcessorById(processorId);
        assertFalse(result.isPresent());
        Mockito.verify(processorRepository).findById(processorId);
    }
    @Test
    void testDeleteProcessorById() {
        int processorId = 1;
        processorService.deleteProcessorById(processorId);
        Mockito.verify(processorRepository).deleteById(processorId);
    }
    @Test
    void testUpdateProcessor() throws ChangeSetPersister.NotFoundException {
        int processorId = 1;
        Processor originalProcessor = new Processor();
        Processor updatedProcessor = new Processor();
        Mockito.when(processorRepository.findById(processorId)).thenReturn(Optional.of(originalProcessor));
        Mockito.when(processorRepository.save(Mockito.any(Processor.class))).thenReturn(updatedProcessor);
        Processor result = processorService.updateProcessor(processorId, updatedProcessor);
        assertEquals(updatedProcessor, result);
        Mockito.verify(processorRepository).findById(processorId);
        Mockito.verify(processorRepository).save(Mockito.any(Processor.class));
    }
}
