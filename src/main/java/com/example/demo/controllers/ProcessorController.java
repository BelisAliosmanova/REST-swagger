package com.example.demo.controllers;
import com.example.demo.entities.Processor;
import com.example.demo.services.ProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class ProcessorController {
    @Autowired
    ProcessorService processorService;
    @GetMapping("/processors/all")
    public ResponseEntity<List<Processor>> getAllProcessors(){
        return processorService.getAllProcessors();
    }
    @PostMapping("processors/add")
    public ResponseEntity<Processor> addProcessor(@RequestBody Processor processor, UriComponentsBuilder uriComponentsBuilder){
        return processorService.addProcessor(processor, uriComponentsBuilder);
    }
    @GetMapping("/getProcessor/{id}")
    public ResponseEntity<Processor> getProcessorById(@PathVariable("id") int id) {
        return processorService.getProcessorById(id);
    }
    @DeleteMapping("/deleteProcessor/{id}")
    public ResponseEntity<Processor> deleteProcessorById(@PathVariable("id") int id) {
        return processorService.deleteProcessorById(id);
    }
    @PutMapping("/updateProcessor{id}")
    public ResponseEntity<Processor> updateProcessor (int id, @RequestBody Processor processor) {
        return processorService.updateProcessor(id, processor);
    }
}
