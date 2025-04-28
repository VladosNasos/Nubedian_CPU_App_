package com.example.cpuapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class SocketDto {
    private Long id;

    @NotBlank
    private String name;
}
