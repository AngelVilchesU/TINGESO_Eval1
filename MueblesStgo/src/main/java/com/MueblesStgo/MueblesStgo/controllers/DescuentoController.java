package com.MueblesStgo.MueblesStgo.controllers;

import com.MueblesStgo.MueblesStgo.entities.DescuentoEntity;
import com.MueblesStgo.MueblesStgo.services.DescuentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/descuento")
public class DescuentoController {
    @Autowired
    DescuentoService descuentoService;

    @GetMapping()
    public ArrayList<DescuentoEntity> obtenerDescuento(){
        return descuentoService.obtenerDescuento();
    }

    @PostMapping()
    public DescuentoEntity guardarDescuento(@RequestBody DescuentoEntity descuento){
        return this.descuentoService.guardarDescuento(descuento);
    }
}
