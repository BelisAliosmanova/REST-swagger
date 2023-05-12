package com.example.demo;

import com.example.demo.dtos.ProcessorDTO;
import com.example.demo.entities.Processor;
import com.example.demo.repositories.ProcessorRepository;
import com.example.demo.services.ProcessorService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ProcessorServiceTest {
    @Mock
    private ProcessorRepository processorRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private ProcessorService processorService;

    @Test
    void testGetAllProcessors() {
        List<Processor> processors = Arrays.asList(
                new Processor(),
                new Processor()
        );
        Mockito.when(processorRepository.findAll()).thenReturn(processors);
        List<ProcessorDTO> result = processorService.getAllProcessors();
        List<ProcessorDTO>processors1 = processors.stream().map(prc -> modelMapper.map(prc, ProcessorDTO.class)).collect(Collectors.toList());
        assertEquals(processors1, result);
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
        ProcessorDTO result = processorService.getProcessorById(processorId);
        ProcessorDTO processor1 = modelMapper.map(processor, ProcessorDTO.class);
        assertEquals(processor1, result);
        Mockito.verify(processorRepository).findById(processorId);
    }

    @Test
    void testGetProcessorByIdNotFound() {
        int processorId = 1;
        Mockito.when(processorRepository.findById(processorId)).thenReturn(Optional.empty());
        ProcessorDTO result = processorService.getProcessorById(processorId);
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
        ProcessorDTO result = processorService.updateProcessor(processorId, updatedProcessor);
        ProcessorDTO updatedProcessor1 = modelMapper.map(originalProcessor, ProcessorDTO.class);
        assertEquals(updatedProcessor1, result);
        Mockito.verify(processorRepository).findById(processorId);
        Mockito.verify(processorRepository).save(Mockito.any(Processor.class));
    }
}
