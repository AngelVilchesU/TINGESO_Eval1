package com.MueblesStgo.MueblesStgo.services;

import com.MueblesStgo.MueblesStgo.entities.DescuentoEntity;
import com.MueblesStgo.MueblesStgo.repositories.DescuentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DescuentoService {
    @Autowired
    DescuentoRepository descuentoRepository;

    public ArrayList<DescuentoEntity> obtenerDescuento(){
        return (ArrayList<DescuentoEntity>) descuentoRepository.findAll();
    }

    public DescuentoEntity guardarDescuento(DescuentoEntity descuento){
        return descuentoRepository.save(descuento);
    }
}
