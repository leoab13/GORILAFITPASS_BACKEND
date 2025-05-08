package com.gorila.gorilafitpass.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Inscripcion")
@Getter
@Setter
public class Inscripcion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num_inscripcion")
    private Integer numInscripcion;

    @ManyToOne
    @JoinColumn(name = "Id_Us", nullable = false)
    private Usuario usuario;

    @Column(name = "tipo_inscripcion")
    private String tipoInscripcion;

    @Column(name = "fecha_inscripcion")
    private LocalDate fechaInscripcion;
}
