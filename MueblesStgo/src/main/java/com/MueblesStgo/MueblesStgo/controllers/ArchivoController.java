package com.MueblesStgo.MueblesStgo.controllers;

import com.MueblesStgo.MueblesStgo.services.ArchivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ArchivoController {

    @Autowired
    private ArchivoService archivoService;

    @GetMapping("/carga")
    public String cargaArchivoPag(){
        return ("carga");
    }

    @PostMapping("/subir")
    public String guardarArchivo(@RequestParam ("DATA")MultipartFile archivo){
        archivoService.cargarArchivo(archivo);
        return "redirect:/carga";
    }

}
