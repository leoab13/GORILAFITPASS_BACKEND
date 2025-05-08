package com.gorila.gorilafitpass.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gorila.gorilafitpass.model.Usuario;
import com.gorila.gorilafitpass.repository.UsuarioRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {
       private final UsuarioRepository usuarioRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario actualizarUsuario(Integer id, Usuario nuevosDatos) {
        return usuarioRepository.findById(id)
            .map(usuario -> {
                usuario.setNombre(nuevosDatos.getNombre());
                usuario.setEdad(nuevosDatos.getEdad());
                usuario.setTelefono(nuevosDatos.getTelefono());
                usuario.setTelefonoEmer(nuevosDatos.getTelefonoEmer());
                usuario.setHuella(nuevosDatos.getHuella());
                usuario.setFoto(nuevosDatos.getFoto());
                return usuarioRepository.save(usuario);
            }).orElse(null);
    }

    @Transactional
    public boolean eliminarUsuario(Integer id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Usuario obtenerUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    @Transactional
    public void agregarUsuarioConSP(Usuario usuario) {
        entityManager
            .createStoredProcedureQuery("sp_agregar_usuario")
            .registerStoredProcedureParameter("p_nombre", String.class, jakarta.persistence.ParameterMode.IN)
            .registerStoredProcedureParameter("p_edad", String.class, jakarta.persistence.ParameterMode.IN)
            .registerStoredProcedureParameter("p_telefono", String.class, jakarta.persistence.ParameterMode.IN)
            .registerStoredProcedureParameter("p_telefonoEmer", String.class, jakarta.persistence.ParameterMode.IN)
            .registerStoredProcedureParameter("p_huella", byte[].class, jakarta.persistence.ParameterMode.IN)
            .registerStoredProcedureParameter("p_foto", byte[].class, jakarta.persistence.ParameterMode.IN)
            .setParameter("p_nombre", usuario.getNombre())
            .setParameter("p_edad", usuario.getEdad())
            .setParameter("p_telefono", usuario.getTelefono())
            .setParameter("p_telefonoEmer", usuario.getTelefonoEmer())
            .setParameter("p_huella", usuario.getHuella())
            .setParameter("p_foto", usuario.getFoto())
            .execute();
    }
}
