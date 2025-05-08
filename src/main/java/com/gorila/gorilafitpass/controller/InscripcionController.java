package com.gorila.gorilafitpass.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gorila.gorilafitpass.model.Inscripcion;
import com.gorila.gorilafitpass.service.InscripcionService;


@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

    private final InscripcionService inscripcionService;

    public InscripcionController(InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }

    @PostMapping
    public ResponseEntity<Inscripcion> crear(@RequestBody Inscripcion inscripcion) {
        return ResponseEntity.ok(inscripcionService.guardar(inscripcion));
    }

    @GetMapping
    public ResponseEntity<List<Inscripcion>> listar() {
        return ResponseEntity.ok(inscripcionService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inscripcion> obtenerPorId(@PathVariable Integer id) {
        Inscripcion inscripcion = inscripcionService.obtenerPorId(id);
        return inscripcion != null ? ResponseEntity.ok(inscripcion) : ResponseEntity.notFound().build();
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Inscripcion>> porUsuario(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(inscripcionService.obtenerPorUsuario(idUsuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inscripcion> actualizar(@PathVariable Integer id, @RequestBody Inscripcion nueva) {
        Inscripcion actualizada = inscripcionService.actualizar(id, nueva);
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        boolean eliminado = inscripcionService.eliminar(id);
        return eliminado ? ResponseEntity.ok("Inscripción eliminada") : ResponseEntity.notFound().build();
    }

}
