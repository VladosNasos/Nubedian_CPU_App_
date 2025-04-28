package com.example.cpuapp.service;

import com.example.cpuapp.dto.CpuDto;
import com.example.cpuapp.exception.ResourceNotFoundException;
import com.example.cpuapp.model.Cpu;
import com.example.cpuapp.model.Socket;
import com.example.cpuapp.repository.CpuRepository;
import com.example.cpuapp.repository.SocketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CpuServiceImpl implements CpuService {
    private final CpuRepository cpuRepository;
    private final SocketRepository socketRepository;

    @Override
    public List<CpuDto> getAll() {
        return cpuRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CpuDto getById(Long id) {
        return cpuRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("CPU not found: " + id));
    }

    @Override
    public CpuDto create(CpuDto dto) {
        Cpu entity = toEntity(dto);
        return toDto(cpuRepository.save(entity));
    }

    @Override
    public CpuDto update(Long id, CpuDto dto) {
        Cpu cpu = cpuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CPU not found: " + id));
        cpu.setBrand(dto.getBrand());
        cpu.setModel(dto.getModel());
        Socket socket = socketRepository.findById(dto.getSocketId())
                .orElseThrow(() -> new ResourceNotFoundException("Socket not found: " + dto.getSocketId()));
        cpu.setSocket(socket);
        cpu.setClockspeed(dto.getClockspeed());
        cpu.setCores(dto.getCores());
        cpu.setThreads(dto.getThreads());
        cpu.setTdp(dto.getTdp());
        cpu.setPriceEur(dto.getPriceEur());
        return toDto(cpuRepository.save(cpu));
    }

    @Override
    public void delete(Long id) {
        if (!cpuRepository.existsById(id)) {
            throw new ResourceNotFoundException("CPU not found: " + id);
        }
        cpuRepository.deleteById(id);
    }

    private CpuDto toDto(Cpu cpu) {
        return new CpuDto(
                cpu.getId(),
                cpu.getBrand(),
                cpu.getModel(),
                cpu.getSocket().getId(),
                cpu.getClockspeed(),
                cpu.getCores(),
                cpu.getThreads(),
                cpu.getTdp(),
                cpu.getPriceEur()
        );
    }

    private Cpu toEntity(CpuDto dto) {
        Socket socket = socketRepository.findById(dto.getSocketId())
                .orElseThrow(() -> new ResourceNotFoundException("Socket not found: " + dto.getSocketId()));
        Cpu cpu = new Cpu();
        cpu.setBrand(dto.getBrand());
        cpu.setModel(dto.getModel());
        cpu.setSocket(socket);
        cpu.setClockspeed(dto.getClockspeed());
        cpu.setCores(dto.getCores());
        cpu.setThreads(dto.getThreads());
        cpu.setTdp(dto.getTdp());
        cpu.setPriceEur(dto.getPriceEur());
        return cpu;
    }
}
