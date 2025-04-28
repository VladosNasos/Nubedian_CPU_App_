package com.example.cpuapp.repository;

import com.example.cpuapp.model.Cpu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CpuRepository extends JpaRepository<Cpu, Long> {}
