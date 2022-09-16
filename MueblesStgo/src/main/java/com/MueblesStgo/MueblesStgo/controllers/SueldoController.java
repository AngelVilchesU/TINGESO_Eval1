package com.MueblesStgo.MueblesStgo.controllers;

import com.MueblesStgo.MueblesStgo.entities.EmpleadoEntity;
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

/*
    @GetMapping("/query") // /query?dia=14&mes=9&anio=2022
    public void diaSemana(@RequestParam("dia") int dia, @RequestParam("mes") int mes, @RequestParam("anio") int anio){
        this.sueldoService.diaDeSemana(dia, mes, anio);
    }

*/
    /*
@GetMapping("/query") // /query?dia=14&mes=9&anio=2022
public void esBisiesto(@RequestParam("anio") int anio){
    this.sueldoService.esBisiesto(anio);
}*/
    /*
@GetMapping("/query") // /query?dia=14&mes=9&anio=2022
public void diasDelMes(@RequestParam("mes") int mes, @RequestParam("anio") int anio){
    this.sueldoService.diasDelMes(mes, anio);
}
*/
@GetMapping("/query") // /query?dia=14&mes=9&anio=2022
public void calculoPlanillas(@RequestParam("ruta") String ruta, @RequestParam("arr") String arr){
    this.sueldoService.calculoPlanillas(ruta, arr);
}


}
