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

    /*
    El siguiente método retorna un arreglo el cual contiene TODOS los descuentos de la base de datos
     */
    public ArrayList<DescuentoEntity> obtenerDescuento(){
        return (ArrayList<DescuentoEntity>) descuentoRepository.findAll();
    }

    /*
    El siguiente método permite guardar un descuento en la base de datos
     */
    public DescuentoEntity guardarDescuento(DescuentoEntity descuento){
        return descuentoRepository.save(descuento);
    }

    /*
    El siguiente método permite eliminar un descuento de la base de datos con su ID
     */
    public boolean eliminarDescuento(Long id){
        try {
            descuentoRepository.deleteById(id);
            return true;
        }
        catch (Exception err){
            return false;
        }
    }
}
