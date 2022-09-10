package com.MueblesStgo.MueblesStgo.repositories;

import com.MueblesStgo.MueblesStgo.entities.EmpleadoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface EmpleadoRepository extends CrudRepository<EmpleadoEntity, Long> {

    /*
    El siguiente método permite retornar el usuario desde la base de datos
    con el rut que sea ingresado
     */
    public abstract ArrayList<EmpleadoEntity> findByRut(String rut);
}
