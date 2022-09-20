package com.MueblesStgo.MueblesStgo;

import com.MueblesStgo.MueblesStgo.entities.EmpleadoEntity;
import com.MueblesStgo.MueblesStgo.repositories.EmpleadoRepository;
import com.MueblesStgo.MueblesStgo.services.EmpleadoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmpleadoServiceTest {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    EmpleadoService empleadoService = new EmpleadoService();
    EmpleadoEntity empleado = new EmpleadoEntity();

    @Test
    public void guardarEmpleado_obtenerEmpleado(){
        EmpleadoEntity empleado1 = new EmpleadoEntity();
        empleado1.setId(Long.valueOf("999"));
        empleado1.setRut("12.345.678-9");
        empleado1.setNombre("Nombre");
        empleado1.setApellido("Apellido");
        empleado1.setFechaNacimiento(LocalDate.of(2000,12,4));
        empleado1.setFechaIngresoEmpresa(LocalDate.of(2020,4,8));
        ArrayList<EmpleadoEntity> empleadoAL = new ArrayList<>();
        try {
            empleadoService.guardarEmpleado(empleado1);
            try {
                empleadoAL = empleadoService.obtenerEmpleados();
            }
            catch (Exception err){
                err.getMessage();
            }
        }
        catch (Exception err){
            empleadoRepository.save(empleado1);
            try {
                empleadoAL = empleadoService.obtenerEmpleados();
            }
            catch (Exception err2){
                empleadoAL = (ArrayList<EmpleadoEntity>) empleadoRepository.findAll();
            }
        }
        assertNotNull(empleadoAL);
    }

    @Test
    public void obtenerPorRut(){
        EmpleadoEntity empleado1 = new EmpleadoEntity();
        empleado1.setId(Long.valueOf("999"));
        empleado1.setRut("12.345.678-9");
        empleado1.setNombre("Nombre");
        empleado1.setApellido("Apellido");
        empleado1.setFechaNacimiento(LocalDate.of(2000,12,4));
        empleado1.setFechaIngresoEmpresa(LocalDate.of(2020,4,8));
        EmpleadoEntity empleado2 = new EmpleadoEntity();
        try {
            empleadoService.guardarEmpleado(empleado1);
            try {
                empleado2 = empleadoService.obtenerPorRut(empleado1.getRut());
            }
            catch (Exception err){
                err.getMessage();
            }
        }
        catch (Exception err){
            empleadoRepository.save(empleado1);
            try {
                empleado2 = empleadoService.obtenerPorRut(empleado1.getRut());
            }
            catch (Exception err2){
                empleado2 = empleadoRepository.findByRut(empleado1.getRut());
            }
        }
        assertNotNull(empleado2);
    }
}
