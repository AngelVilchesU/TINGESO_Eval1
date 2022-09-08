package com.MueblesStgo.MueblesStgo.controllers;

import com.MueblesStgo.MueblesStgo.entities.EmpleadoEntity;
import com.MueblesStgo.MueblesStgo.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;

    @GetMapping()
    public ArrayList<EmpleadoEntity> obtenerEmpleados(){
        return empleadoService.obtenerEmpleados();
    }

    @PostMapping()
    public EmpleadoEntity guardarEmpleado(@RequestBody EmpleadoEntity empleado){
        return this.empleadoService.guardarEmpleado(empleado);
    }

    @GetMapping(path = "/{id}")
    public Optional<EmpleadoEntity> obtenerEmpleadoPorId(@PathVariable("id") Long id){
        return this.empleadoService.obtenerPorId(id);
    }

    @GetMapping("/query") // /empleado/query?rut=20.996.064-8
    public ArrayList<EmpleadoEntity> obtenerEmpleadoPorRut(@RequestParam("rut") String rut){
        return this.empleadoService.obtenerPorRut(rut);
    }

    @DeleteMapping(path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id){
        boolean hecho = this.empleadoService.eliminarEmpleado(id);
        if (hecho){
            return ("Se ha eliminado el usuario de ID: " + id);
        }
        else {
            return ("No se ha podido eliminar al usuario de ID: " + id);
        }
    }
}
