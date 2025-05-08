package com.gorila.gorilafitpass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gorila.gorilafitpass.model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado,Integer> {
    
}
