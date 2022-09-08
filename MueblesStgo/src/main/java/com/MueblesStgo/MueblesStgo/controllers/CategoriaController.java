package com.MueblesStgo.MueblesStgo.controllers;

import com.MueblesStgo.MueblesStgo.entities.CategoriaEntity;
import com.MueblesStgo.MueblesStgo.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    CategoriaService categoriaService;

    @GetMapping()
    public ArrayList<CategoriaEntity> obtenerCategoria(){
        return categoriaService.obtenerCategoria();
    }

    @PostMapping()
    public CategoriaEntity guardarCategoria(@RequestBody CategoriaEntity categoria){
        return this.categoriaService.guardarCategoria(categoria);
    }
}
