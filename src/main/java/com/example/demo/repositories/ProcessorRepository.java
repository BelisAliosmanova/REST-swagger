package com.example.demo.repositories;

import com.example.demo.entities.Processor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessorRepository extends JpaRepository<Processor, Integer> {
}
