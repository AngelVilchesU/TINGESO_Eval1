package com.MueblesStgo.MueblesStgo.controllers;

import com.MueblesStgo.MueblesStgo.entities.SueldoEntity;
import com.MueblesStgo.MueblesStgo.services.SueldoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/sueldo")
public class SueldoController {
    @Autowired
    SueldoService sueldoService;

    /*
    El siguiente método retorna los datos de la presente entidad desde la vista de sueldo,
    es decir, "/sueldo" lo cual permite visualizar el contenido de la base de datos en dicha
    dirección
     */
    @GetMapping()
    public ArrayList<SueldoEntity> obtenerSueldo(){
        return sueldoService.obtenerSueldo();
    }

    /*
    El siguiente método permite guardar en la base de datos un objeto tipo sueldo a la
    base de datos
     */
    @PostMapping()
    public SueldoEntity guardarSueldo(@RequestBody SueldoEntity sueldo){
        return this.sueldoService.guardarSueldo(sueldo);
    }
}
