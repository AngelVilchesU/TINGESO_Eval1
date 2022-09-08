package com.MueblesStgo.MueblesStgo.controllers;

import com.MueblesStgo.MueblesStgo.entities.ControlAsistenciaEntity;
import com.MueblesStgo.MueblesStgo.services.ControlAsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/controlAsistencia")
public class ControlAsistenciaController {
    @Autowired
    ControlAsistenciaService controlAsistenciaService;

    @GetMapping()
    public ArrayList<ControlAsistenciaEntity> obtenerControlAsistencia(){
        return controlAsistenciaService.obtenerControlAsistencia();
    }

    @PostMapping()
    public ControlAsistenciaEntity guardarControlAsistencia(@RequestBody ControlAsistenciaEntity controlAsistencia){
        return this.controlAsistenciaService.guardarControlAsistencia(controlAsistencia);
    }
}
