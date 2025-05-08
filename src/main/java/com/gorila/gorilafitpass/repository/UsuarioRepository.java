package com.gorila.gorilafitpass.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gorila.gorilafitpass.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    Optional<Usuario> findByHuella(byte[] huella);

}
