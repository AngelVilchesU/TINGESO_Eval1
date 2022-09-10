package com.MueblesStgo.MueblesStgo.services;

import com.MueblesStgo.MueblesStgo.entities.BonificacionEntity;
import com.MueblesStgo.MueblesStgo.repositories.BonificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BonificacionService {
    @Autowired
    BonificacionRepository bonificacionRepository;

    /*
    El siguiente método retorna un arreglo el cual contiene TODAS las bonificaciones de la base de datos
     */
    public ArrayList<BonificacionEntity> obtenerBonificacion(){
        return (ArrayList<BonificacionEntity>) bonificacionRepository.findAll();
    }

    /*
    El siguiente método permite guardar una bonificación en la base de datos
     */
    public BonificacionEntity guardarBonificacion(BonificacionEntity bonificacion){
        return bonificacionRepository.save(bonificacion);
    }

    /*
    El siguiente método permite eliminar una bonificación de la base de datos con su ID
     */
    public boolean eliminarBonificacion(Long id){
        try {
            bonificacionRepository.deleteById(id);
            return true;
        }
        catch (Exception err){
            return false;
        }
    }
}
