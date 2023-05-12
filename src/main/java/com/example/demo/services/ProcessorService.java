package com.example.demo.services;

import com.example.demo.dtos.ProcessorDTO;
import com.example.demo.entities.Processor;
import com.example.demo.repositories.ProcessorRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProcessorService {
    private final ProcessorRepository processorRepository;
    private final ModelMapper modelMapper;

    public List<ProcessorDTO> getAllProcessors() {
        List<Processor> processors = processorRepository.findAll();
        return processors.stream().map(prc -> modelMapper.map(prc, ProcessorDTO.class)).collect(Collectors.toList());
    }

    public Processor addProcessor(Processor processor, UriComponentsBuilder uriComponentsBuilder) {
        return processorRepository.save(processor);
    }

    public ProcessorDTO getProcessorById(int id) {
        Optional<Processor> processor = processorRepository.findById(id);
        return modelMapper.map(processor, (Type) ProcessorDTO.class);
    }

    public void deleteProcessorById(int id) {
        processorRepository.deleteById(id);
    }

    public ProcessorDTO updateProcessor(int id, Processor updatedProcessor) throws ChangeSetPersister.NotFoundException {
        Processor processorUpdate = processorRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        processorUpdate.setModel(updatedProcessor.getModel());
        processorUpdate.setBrand(updatedProcessor.getBrand());
        processorUpdate.setProcessorFrequency(updatedProcessor.getProcessorFrequency());
        processorUpdate.setNumberOfCores(updatedProcessor.getNumberOfCores());
        processorRepository.save(processorUpdate);
        return modelMapper.map(processorUpdate, ProcessorDTO.class);
    }
}
