package com.example.demo.services;

import com.example.demo.entities.Processor;
import com.example.demo.repositories.ProcessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ProcessorService {
    @Autowired
    ProcessorRepository processorRepository;

    public ResponseEntity<List<Processor>> getAllProcessors() {
        List<Processor> processors = processorRepository.findAll();
        return ResponseEntity.ok(processors);
    }

    public ResponseEntity<Processor> addProcessor(Processor processor, UriComponentsBuilder uriComponentsBuilder) {
        Processor savedProcessor = processorRepository.save(processor);
        URI location = uriComponentsBuilder.path("/processors/{id}").buildAndExpand(savedProcessor.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<Processor> getProcessorById(int id) {
        Optional<Processor> processor = processorRepository.findById(id);
        return processor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Processor> deleteProcessorById(int id) {
        processorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

     public ResponseEntity<Processor> updateProcessor(int id, Processor processor) {
        Optional<Processor> optionalProcessor = processorRepository.findById(id);
        if (optionalProcessor.isPresent()) {
            Processor newProcessor = optionalProcessor.get();
            if (processor.getBrand() != null) {
                newProcessor.setBrand(processor.getBrand());
            }
            if (processor.getModel() != null) {
                newProcessor.setModel(processor.getModel());
            }
            if (processor.getProcessorFrequency() != 0) {
                newProcessor.setProcessorFrequency(processor.getProcessorFrequency());
            }
            if (processor.getNumberOfCores() != 0) {
                newProcessor.setNumberOfCores(processor.getNumberOfCores());
            }
            processorRepository.save(newProcessor);
            return ResponseEntity.ok(newProcessor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
