package com.gorila.gorilafitpass.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Empleado")
@Getter
@Setter
public class Empleado {
    
    @Id
    @Column(name = "Id_Us")
    private Integer idUs;

    @OneToOne
    @MapsId
    @JoinColumn(name = "Id_Us")
    private Usuario usuario;

    @Column(name = "Puesto")
    private String puesto;

    @Column(name = "Horario")
    private String horario;
}
