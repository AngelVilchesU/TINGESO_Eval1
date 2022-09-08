package com.MueblesStgo.MueblesStgo.services;

import com.MueblesStgo.MueblesStgo.entities.ControlAsistenciaEntity;
import com.MueblesStgo.MueblesStgo.repositories.ControlAsistenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ControlAsistenciaService {
    @Autowired
    ControlAsistenciaRepository controlAsistenciaRepository;

    public ArrayList<ControlAsistenciaEntity> obtenerControlAsistencia(){
        return (ArrayList<ControlAsistenciaEntity>) controlAsistenciaRepository.findAll();
    }

    public ControlAsistenciaEntity guardarControlAsistencia(ControlAsistenciaEntity controlAsistencia){
        return controlAsistenciaRepository.save(controlAsistencia);
    }
}
