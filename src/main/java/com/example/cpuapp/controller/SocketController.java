package com.example.cpuapp.controller;

import com.example.cpuapp.dto.SocketDto;
import com.example.cpuapp.service.SocketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sockets")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class SocketController {
    private final SocketService socketService;

    @GetMapping
    public List<SocketDto> getAll() {
        return socketService.getAll();
    }
}
