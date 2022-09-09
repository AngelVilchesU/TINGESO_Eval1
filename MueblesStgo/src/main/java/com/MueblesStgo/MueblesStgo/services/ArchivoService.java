package com.MueblesStgo.MueblesStgo.services;

import com.MueblesStgo.MueblesStgo.entities.ArchivoEntity;
import com.MueblesStgo.MueblesStgo.repositories.ArchivoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;


@Service
public class ArchivoService {
    @Autowired
    ArchivoRepository archivoRepository;
    ArchivoEntity archivoEntity;
    private String nombreArchivo = "DATA.txt";
    private String carpetaDestino="Marcas//";
    private final Logger carga = LoggerFactory.getLogger(ArchivoService.class);


    public ArrayList<ArchivoEntity> obtenerMarcas(){
        return (ArrayList<ArchivoEntity>) archivoRepository.findAll();
    }

    public ArchivoEntity guardarMarca(ArchivoEntity marca){
        return archivoRepository.save(marca);
    }
    public String cargarArchivo(MultipartFile archivo){
        if (archivo.isEmpty()){
            return "El archivo no se ha subido exitosamente";
        }
        else {
            try {
                byte[] arrayByte = archivo.getBytes();
                Path ruta = Paths.get(carpetaDestino + archivo.getOriginalFilename());
                Files.write(ruta, arrayByte);
                carga.info("Archivo subido");
            }
            catch (IOException error){
                error.printStackTrace();
            }
            leerArchivo(carpetaDestino + archivo.getOriginalFilename());
            return "El archivo se ha subido exitosamente";
        }
    }

    public String leerArchivo(String ruta){
        File archivo = new File(ruta);
        try {
            Scanner escaner = new Scanner(archivo);
            while (escaner.hasNextLine()){
                String linea = escaner.nextLine();
                String[] parte = linea.split(";");
                String fechaTmp = parte[0].replace("/", "-");
                LocalDate fecha = LocalDate.parse(fechaTmp);
                String horaTmp = parte[1];
                LocalTime hora = LocalTime.parse(horaTmp);
                String rut = parte[2];



                guardarMarca(new ArchivoEntity(fecha, hora, rut));
                System.out.println(fecha + ";" + hora + ";" + rut);
            }
        }
        catch (FileNotFoundException error){
            error.printStackTrace();
        }
        return "El archivo se ha leido existosamente";


    }
}
