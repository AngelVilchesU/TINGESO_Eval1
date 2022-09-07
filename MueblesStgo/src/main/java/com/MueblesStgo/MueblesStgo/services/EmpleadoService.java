package com.MueblesStgo.MueblesStgo.services;

import com.MueblesStgo.MueblesStgo.models.EmpleadoModel;
import com.MueblesStgo.MueblesStgo.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EmpleadoService {
    @Autowired // Instancia
    EmpleadoRepository empleadoRepository;

    public ArrayList<EmpleadoModel> obtenerEmpleados(){
        return (ArrayList<EmpleadoModel>) empleadoRepository.findAll();
    }

    public EmpleadoModel guardarEmpleado(EmpleadoModel empleado){
        return empleadoRepository.save(empleado);
    }
}
