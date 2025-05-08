package com.example.cpuapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter @Setter @NoArgsConstructor
public class Cpu {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String model;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "socket_id", nullable = false)
    private Socket socket;

    private BigDecimal clockspeed;
    private int cores;
    private int threads;
    private Integer tdp;
    private BigDecimal priceEur;
    private boolean available = true;
}
