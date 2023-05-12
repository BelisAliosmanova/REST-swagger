package com.example.demo.controllers;
import com.example.demo.dtos.ProcessorDTO;
import com.example.demo.entities.Processor;
import com.example.demo.services.ProcessorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/processors")
public class ProcessorController {
    private final ProcessorService processorService;
    private final ModelMapper modelMapper;
    @GetMapping("/all")
    public ResponseEntity<List<ProcessorDTO>> getAllProcessors(){
        List<ProcessorDTO> processors = processorService.getAllProcessors();
        var status = processors.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return ResponseEntity.status(status).body(processors);
    }
    @PostMapping("/add")
    public ResponseEntity<Processor> addProcessor(@RequestBody Processor processor, UriComponentsBuilder uriComponentsBuilder){
        URI location = uriComponentsBuilder.path("/processors/{id}").buildAndExpand(processorService.addProcessor(processor, uriComponentsBuilder).getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @GetMapping("/getProcessor/{id}")
    public ResponseEntity<ProcessorDTO> getProcessorById(@PathVariable("id") int id) {
        return ResponseEntity.ok(processorService.getProcessorById(id));
    }
    @DeleteMapping("/deleteProcessor/{id}")
    public ResponseEntity<Processor> deleteProcessorById(@PathVariable("id") int id) {
        processorService.deleteProcessorById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/updateProcessor{id}")
    public ResponseEntity<ProcessorDTO> updateProcessor (int id, @RequestBody Processor processor) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(processorService.updateProcessor(id, processor));
    }
}
