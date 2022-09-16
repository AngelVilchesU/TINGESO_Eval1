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

    /*
    El siguiente método retorna un arreglo el cual contiene TODAS las categorias de la base de datos
     */
    public ArrayList<CategoriaEntity> obtenerCategoria(){
        return (ArrayList<CategoriaEntity>) categoriaRepository.findAll();
    }

    /*
    El siguiente método permite guardar una categoria en la base de datos
     */
    public CategoriaEntity guardarCategoria(CategoriaEntity categoria){
        return categoriaRepository.save(categoria);
    }

    /*
    El siguiente método permite eliminar una categoria de la base de datos con su ID
     */
    public boolean eliminarCategoria(Long id){
        try {
            categoriaRepository.deleteById(id);
            return true;
        }
        catch (Exception err){
            return false;
        }
    }

    public float pagoHorasExtra(float horasExtra, char categoria){
        ArrayList<CategoriaEntity> categoriaEntityArrayList = obtenerCategoria();
        for (int i = 0; i < categoriaEntityArrayList.size(); i++){
            if (categoriaEntityArrayList.get(i).getCategoria() == categoria){
                return categoriaEntityArrayList.get(i).getMontoPorHora() * horasExtra;
            }
        }
        return 0;
    }
}
