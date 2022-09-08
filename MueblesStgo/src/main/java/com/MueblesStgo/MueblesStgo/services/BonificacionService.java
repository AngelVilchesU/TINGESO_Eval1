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

    public ArrayList<BonificacionEntity> obtenerBonificacion(){
        return (ArrayList<BonificacionEntity>) bonificacionRepository.findAll();
    }

    public BonificacionEntity guardarBonificacion(BonificacionEntity bonificacion){
        return bonificacionRepository.save(bonificacion);
    }
}
