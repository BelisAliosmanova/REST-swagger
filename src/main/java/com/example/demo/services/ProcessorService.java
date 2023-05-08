package com.example.demo.services;

import com.example.demo.entities.Manufacturer;
import com.example.demo.entities.Processor;
import com.example.demo.repositories.ProcessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProcessorService {
    private final ProcessorRepository processorRepository;

    public List<Processor> getAllProcessors() {
        return processorRepository.findAll();
    }

    public Processor addProcessor(Processor processor, UriComponentsBuilder uriComponentsBuilder) {
        return processorRepository.save(processor);
    }

    public Optional<Processor> getProcessorById(int id) {
        return processorRepository.findById(id);
    }

    public void deleteProcessorById(int id) {
        processorRepository.deleteById(id);
    }

     public Processor updateProcessor (int id, Processor updatedProcessor) throws ChangeSetPersister.NotFoundException {
         Processor processorUpdate = processorRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
         processorUpdate.setModel(updatedProcessor.getModel());
         processorUpdate.setBrand(updatedProcessor.getBrand());
         processorUpdate.setProcessorFrequency(updatedProcessor.getProcessorFrequency());
         processorUpdate.setNumberOfCores(updatedProcessor.getNumberOfCores());
         return processorRepository.save(processorUpdate);
    }
}
