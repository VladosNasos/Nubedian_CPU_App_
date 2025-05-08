package com.example.cpuapp.controller;

import com.example.cpuapp.dto.CpuDto;
import com.example.cpuapp.service.CpuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cpus")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class CpuController {
    private final CpuService cpuService;

    @GetMapping
    public List<CpuDto> getAll() {
        return cpuService.getAll();
    }

    @GetMapping("/{id}")
    public CpuDto getById(@PathVariable Long id) {
        return cpuService.getById(id);
    }

    @PostMapping
    public ResponseEntity<CpuDto> create(@Valid @RequestBody CpuDto dto) {
        System.out.println("Received CPU: " + dto);
        CpuDto created = cpuService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public CpuDto update(
            @PathVariable Long id,
            @Valid @RequestBody CpuDto dto
    ) {
        return cpuService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cpuService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
