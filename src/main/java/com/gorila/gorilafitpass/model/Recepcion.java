package com.gorila.gorilafitpass.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Recepcion")
@Getter
@Setter
public class Recepcion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recepcion")
    private Integer idRecepcion;

    @ManyToOne
    @JoinColumn(name = "Id_Us", nullable = false)
    private Usuario usuario;

    @Column(name = "numero_llegada")
    private Integer numeroLlegada;

    @Column(name = "hora_llegada")
    private LocalDateTime horaLlegada;
}
