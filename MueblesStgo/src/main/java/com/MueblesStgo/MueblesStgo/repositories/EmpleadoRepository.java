package com.MueblesStgo.MueblesStgo.repositories;

import com.MueblesStgo.MueblesStgo.models.EmpleadoModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends CrudRepository<EmpleadoModel, Long> {
}
