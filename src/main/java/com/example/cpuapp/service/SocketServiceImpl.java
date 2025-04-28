package com.example.cpuapp.service;

import com.example.cpuapp.dto.SocketDto;
import com.example.cpuapp.model.Socket;
import com.example.cpuapp.repository.SocketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SocketServiceImpl implements SocketService {
    private final SocketRepository socketRepository;

    @Override
    public List<SocketDto> getAll() {
        return socketRepository.findAll().stream()
                .map(s -> new SocketDto(s.getId(), s.getName()))
                .collect(Collectors.toList());
    }
}
