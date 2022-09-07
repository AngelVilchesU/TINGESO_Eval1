package com.MueblesStgo.MueblesStgo.controllers;

import com.MueblesStgo.MueblesStgo.models.EmpleadoModel;
import com.MueblesStgo.MueblesStgo.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;

    @GetMapping()
    public ArrayList<EmpleadoModel> obtenerEmpleados(){
        return empleadoService.obtenerEmpleados();
    }

    @PostMapping()
    public EmpleadoModel guardarEmpleado(@RequestBody EmpleadoModel empleado){
        return this.empleadoService.guardarEmpleado(empleado);
    }
}
