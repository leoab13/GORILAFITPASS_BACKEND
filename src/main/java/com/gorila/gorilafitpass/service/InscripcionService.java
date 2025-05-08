package com.gorila.gorilafitpass.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.gorila.gorilafitpass.model.Inscripcion;
import com.gorila.gorilafitpass.repository.InscripcionRepository;

@Service
public class InscripcionService {
    private final InscripcionRepository inscripcionRepository;

    public InscripcionService(InscripcionRepository inscripcionRepository) {
        this.inscripcionRepository = inscripcionRepository;
    }

    public Inscripcion guardar(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    public Inscripcion actualizar(Integer id, Inscripcion nueva) {
        return inscripcionRepository.findById(id).map(inscripcion -> {
            inscripcion.setTipoInscripcion(nueva.getTipoInscripcion());
            inscripcion.setFechaInscripcion(nueva.getFechaInscripcion());
            inscripcion.setUsuario(nueva.getUsuario());
            return inscripcionRepository.save(inscripcion);
        }).orElse(null);
    }

    public boolean eliminar(Integer id) {
        if (inscripcionRepository.existsById(id)) {
            inscripcionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Inscripcion obtenerPorId(Integer id) {
        return inscripcionRepository.findById(id).orElse(null);
    }

    public List<Inscripcion> obtenerTodas() {
        return inscripcionRepository.findAll();
    }

    public List<Inscripcion> obtenerPorUsuario(Integer idUsuario) {
        return inscripcionRepository.findByUsuarioIdUs(idUsuario);
    }

}
