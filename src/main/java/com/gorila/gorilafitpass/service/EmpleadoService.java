package com.gorila.gorilafitpass.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gorila.gorilafitpass.model.Empleado;
import com.gorila.gorilafitpass.repository.EmpleadoRepository;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public Empleado guardarEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public Empleado actualizarEmpleado(Integer id, Empleado nuevo) {
        return empleadoRepository.findById(id).map(empleado -> {
            empleado.setPuesto(nuevo.getPuesto());
            empleado.setHorario(nuevo.getHorario());
            return empleadoRepository.save(empleado);
        }).orElse(null);
    }

    public boolean eliminarEmpleado(Integer id) {
        if (empleadoRepository.existsById(id)) {
            empleadoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Empleado obtenerPorId(Integer id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    public List<Empleado> obtenerTodos() {
        return empleadoRepository.findAll();
    }

}
