package com.MueblesStgo.MueblesStgo.services;

import com.MueblesStgo.MueblesStgo.entities.CategoriaEntity;
import com.MueblesStgo.MueblesStgo.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoriaService {
    @Autowired
    CategoriaRepository categoriaRepository;

    public ArrayList<CategoriaEntity> obtenerCategoria(){
        return (ArrayList<CategoriaEntity>) categoriaRepository.findAll();
    }

    public CategoriaEntity guardarCategoria(CategoriaEntity categoria){
        return categoriaRepository.save(categoria);
    }
}
