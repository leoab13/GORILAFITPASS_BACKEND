package com.gorila.gorilafitpass.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "Usuario")
@Getter
@Setter
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Us")
    private Integer idUs;

    @Column(name = "Nombre", nullable = false)
    private String nombre;

    @Column(name = "Edad")
    private String edad;

    @Column(name = "Telefono")
    private String telefono;

    @Column(name = "TelefonoEmer")
    private String telefonoEmer;

    @Lob
    @Column(name = "Huella")
    private byte[] huella;

    @Lob
    @Column(name = "Foto")
    private byte[] foto;
}
