package com.MueblesStgo.MueblesStgo.controllers;

import com.MueblesStgo.MueblesStgo.entities.BonificacionEntity;
import com.MueblesStgo.MueblesStgo.services.BonificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/bonificacion")
public class BonificacionController {
    @Autowired
    BonificacionService bonificacionService;

    @GetMapping()
    public ArrayList<BonificacionEntity> obtenerBonificacion(){
        return bonificacionService.obtenerBonificacion();
    }

    @PostMapping()
    public BonificacionEntity guardarBonificacion(@RequestBody BonificacionEntity bonificacion){
        return this.bonificacionService.guardarBonificacion(bonificacion);
    }
}
