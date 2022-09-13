package com.MueblesStgo.MueblesStgo.services;

import com.MueblesStgo.MueblesStgo.entities.AutorizacionEntity;
import com.MueblesStgo.MueblesStgo.repositories.AutorizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class AutorizacionService {
    @Autowired
    AutorizacionRepository autorizacionRepository;

    /*
    El siguiente método retorna un arreglo el cual contiene TODAS las autorizaciones de la base de datos
     */
    public ArrayList<AutorizacionEntity> obtenerAutorizaciones(){
        return (ArrayList<AutorizacionEntity>) autorizacionRepository.findAll();
    }

    /*
    El siguiente método permite guardar una autorización en la base de datos
     */
    public AutorizacionEntity guardarAutorizacion (AutorizacionEntity autorizacion){
        return autorizacionRepository.save(autorizacion);
    }

    /*
    El siguiente método retorna un dato tipo LocalDate con un formato YYYY-MM-DD a partir
    de un string de formato de entrada "YYYY-MM-DD"
     */
    public LocalDate fechaFormato(String fecha){
        String[] parte = fecha.split("-");
        int dia = Integer.parseInt(parte[2]);
        int mes = Integer.parseInt(parte[1]);
        int anio = Integer.parseInt(parte[0]);
        LocalDate fechaFormato = LocalDate.of(anio, mes, dia);
        return fechaFormato;
    }
}
