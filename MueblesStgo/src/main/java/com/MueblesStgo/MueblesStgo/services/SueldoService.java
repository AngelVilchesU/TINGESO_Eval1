package com.MueblesStgo.MueblesStgo.services;

import com.MueblesStgo.MueblesStgo.entities.DescuentoEntity;
import com.MueblesStgo.MueblesStgo.entities.SueldoEntity;
import com.MueblesStgo.MueblesStgo.repositories.SueldoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SueldoService {
    @Autowired
    SueldoRepository sueldoRepository;

    /*
    El siguiente método retorna un arreglo el cual contiene TODOS los sueldos de la base de datos
     */
    public ArrayList<SueldoEntity> obtenerSueldo(){
        return (ArrayList<SueldoEntity>) sueldoRepository.findAll();
    }

    /*
    El siguiente método permite guardar un sueldo en la base de datos
     */
    public SueldoEntity guardarSueldo(SueldoEntity sueldo){
        return sueldoRepository.save(sueldo);
    }

}
