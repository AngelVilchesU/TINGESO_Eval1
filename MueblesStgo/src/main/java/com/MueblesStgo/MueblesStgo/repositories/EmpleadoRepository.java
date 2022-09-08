package com.MueblesStgo.MueblesStgo.repositories;

import com.MueblesStgo.MueblesStgo.entities.EmpleadoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface EmpleadoRepository extends CrudRepository<EmpleadoEntity, Long> {
    public abstract ArrayList<EmpleadoEntity> findByNombre(String nombre);
    public abstract ArrayList<EmpleadoEntity> findByRut(String rut);
}
