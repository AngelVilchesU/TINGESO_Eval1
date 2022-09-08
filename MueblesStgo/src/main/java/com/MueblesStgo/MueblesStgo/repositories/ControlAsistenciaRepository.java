package com.MueblesStgo.MueblesStgo.repositories;

import com.MueblesStgo.MueblesStgo.entities.ControlAsistenciaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControlAsistenciaRepository extends CrudRepository<ControlAsistenciaEntity, Long> {
}
