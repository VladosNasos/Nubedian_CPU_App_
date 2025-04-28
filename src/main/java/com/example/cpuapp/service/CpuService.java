package com.example.cpuapp.service;

import com.example.cpuapp.dto.CpuDto;
import java.util.List;

public interface CpuService {
    List<CpuDto> getAll();
    CpuDto getById(Long id);
    CpuDto create(CpuDto dto);
    CpuDto update(Long id, CpuDto dto);
    void delete(Long id);
}
