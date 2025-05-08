package com.example.cpuapp.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data @NoArgsConstructor @AllArgsConstructor
public class CpuDto {
    private Long id;

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @NotNull
    private Long socketId;

    @NotNull @DecimalMin("0.1")
    private BigDecimal clockspeed;

    @Min(1)
    private int cores;

    @Min(1)
    private int threads;

    @Min(1)
    private Integer tdp;

    @NotNull @DecimalMin("0.0")
    private BigDecimal priceEur;

    private boolean available;
}
