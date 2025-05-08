package com.gorila.gorilafitpass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gorila.gorilafitpass.model.Inscripcion;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer> {
    List<Inscripcion> findByUsuarioIdUs(Integer idUs);
    Inscripcion findTopByUsuarioIdUsOrderByFechaInscripcionDesc(Integer idUs);


}
