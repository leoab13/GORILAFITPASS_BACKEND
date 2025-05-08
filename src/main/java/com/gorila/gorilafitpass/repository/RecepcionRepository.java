package com.gorila.gorilafitpass.repository;

import com.gorila.gorilafitpass.model.Recepcion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface RecepcionRepository extends JpaRepository<Recepcion, Integer> {
    List<Recepcion> findByUsuarioIdUs(Integer idUs);
}
