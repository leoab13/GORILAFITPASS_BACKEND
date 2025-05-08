package com.gorila.gorilafitpass.controller;

import com.gorila.gorilafitpass.model.Recepcion;
import com.gorila.gorilafitpass.service.RecepcionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recepciones")
public class RecepcionController {

    private final RecepcionService recepcionService;

    public RecepcionController(RecepcionService recepcionService) {
        this.recepcionService = recepcionService;
    }

    @PostMapping
    public ResponseEntity<Recepcion> crear(@RequestBody Recepcion recepcion) {
        return ResponseEntity.ok(recepcionService.guardar(recepcion));
    }

    @GetMapping
    public ResponseEntity<List<Recepcion>> listar() {
        return ResponseEntity.ok(recepcionService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recepcion> obtenerPorId(@PathVariable Integer id) {
        Recepcion r = recepcionService.obtenerPorId(id);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Recepcion>> porUsuario(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(recepcionService.obtenerPorUsuario(idUsuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recepcion> actualizar(@PathVariable Integer id, @RequestBody Recepcion nueva) {
        Recepcion actualizada = recepcionService.actualizar(id, nueva);
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        boolean eliminado = recepcionService.eliminar(id);
        return eliminado ? ResponseEntity.ok("Recepción eliminada") : ResponseEntity.notFound().build();
    }

    @PostMapping("/verificar-huella")
public ResponseEntity<String> verificarPorHuella(@RequestBody Map<String, String> request) {
    String base64 = request.get("huella");
    byte[] huella = Base64.getDecoder().decode(base64);
    String resultado = recepcionService.verificarYRegistrarPorHuella(huella);
    return ResponseEntity.ok(resultado);
}


}
