package com.gorila.gorilafitpass.service;

import com.gorila.gorilafitpass.model.Recepcion;
import com.gorila.gorilafitpass.model.Usuario;
import com.gorila.gorilafitpass.model.Inscripcion;
import com.gorila.gorilafitpass.repository.RecepcionRepository;
import com.gorila.gorilafitpass.repository.UsuarioRepository;
import com.gorila.gorilafitpass.repository.InscripcionRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RecepcionService {

    private final RecepcionRepository recepcionRepository;
    private final UsuarioRepository usuarioRepository;
    private final InscripcionRepository inscripcionRepository;

    public RecepcionService(RecepcionRepository recepcionRepository,
                            UsuarioRepository usuarioRepository,
                            InscripcionRepository inscripcionRepository) {
        this.recepcionRepository = recepcionRepository;
        this.usuarioRepository = usuarioRepository;
        this.inscripcionRepository = inscripcionRepository;
    }

    public Recepcion guardar(Recepcion recepcion) {
        return recepcionRepository.save(recepcion);
    }

    public Recepcion actualizar(Integer id, Recepcion nueva) {
        return recepcionRepository.findById(id).map(r -> {
            r.setHoraLlegada(nueva.getHoraLlegada());
            r.setNumeroLlegada(nueva.getNumeroLlegada());
            r.setUsuario(nueva.getUsuario());
            return recepcionRepository.save(r);
        }).orElse(null);
    }

    public boolean eliminar(Integer id) {
        if (recepcionRepository.existsById(id)) {
            recepcionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Recepcion obtenerPorId(Integer id) {
        return recepcionRepository.findById(id).orElse(null);
    }

    public List<Recepcion> obtenerTodas() {
        return recepcionRepository.findAll();
    }

    public List<Recepcion> obtenerPorUsuario(Integer idUsuario) {
        return recepcionRepository.findByUsuarioIdUs(idUsuario);
    }

    /**
     * Verifica la huella, valida la inscripción y registra la entrada.
     * @param huella byte[] (decodificado desde Base64)
     * @return mensaje de estado
     */
    public String verificarYRegistrarPorHuella(byte[] huella) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByHuella(huella);

        if (usuarioOpt.isEmpty()) {
            return "Huella no reconocida";
        }

        Usuario usuario = usuarioOpt.get();
        Inscripcion ultima = inscripcionRepository.findTopByUsuarioIdUsOrderByFechaInscripcionDesc(usuario.getIdUs());

        if (ultima == null) {
            return "No tiene inscripciones registradas";
        }

        int meses = switch (ultima.getTipoInscripcion().toLowerCase()) {
            case "mensual" -> 1;
            case "trimestral" -> 3;
            case "anual" -> 12;
            default -> 0; // o lanzar error si el tipo es inválido
        };

        LocalDate fechaLimite = ultima.getFechaInscripcion().plusMonths(meses); // Suponiendo mensualidad fija
        if (fechaLimite.isBefore(LocalDate.now())) {
            return "Inscripción vencida";
        }

        Recepcion r = new Recepcion();
        r.setUsuario(usuario);
        r.setHoraLlegada(LocalDateTime.now());
        r.setNumeroLlegada((int) (recepcionRepository.count() + 1));
        recepcionRepository.save(r);

        return "Entrada registrada correctamente";
    }
}
