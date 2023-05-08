package com.example.demo.controllers;
import com.example.demo.entities.Processor;
import com.example.demo.services.ProcessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/processors")
public class ProcessorController {
    private final ProcessorService processorService;
    @GetMapping("/all")
    public ResponseEntity<List<Processor>> getAllProcessors(){
        List<Processor> processors = processorService.getAllProcessors();
        var status = processors.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return ResponseEntity.status(status).body(processors);
    }
    @PostMapping("/add")
    public ResponseEntity<Processor> addProcessor(@RequestBody Processor processor, UriComponentsBuilder uriComponentsBuilder){
        URI location = uriComponentsBuilder.path("/processors/{id}").buildAndExpand(processorService.addProcessor(processor, uriComponentsBuilder).getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @GetMapping("/getProcessor/{id}")
    public ResponseEntity<Processor> getProcessorById(@PathVariable("id") int id) {
        return processorService.getProcessorById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/deleteProcessor/{id}")
    public ResponseEntity<Processor> deleteProcessorById(@PathVariable("id") int id) {
        processorService.deleteProcessorById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/updateProcessor{id}")
    public ResponseEntity<Processor> updateProcessor (int id, @RequestBody Processor processor) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(processorService.updateProcessor(id, processor));
    }
}
