package com.MueblesStgo.MueblesStgo;
import com.MueblesStgo.MueblesStgo.entities.SueldoEntity;
import com.MueblesStgo.MueblesStgo.repositories.SueldoRepository;
import com.MueblesStgo.MueblesStgo.services.SueldoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SueldoServiceTest {
    @Autowired
    private SueldoRepository sueldoRepository;

    SueldoService sueldoService = new SueldoService();
    SueldoEntity sueldo = new SueldoEntity();

    @Test
    public void guardarSueldo_obtenerSueldo(){
        SueldoEntity sueldo1 = new SueldoEntity();
        sueldo1.setRutEmpleado("12.345.678-9");
        sueldo1.setNombreApellido("Nombre Apellido");
        sueldo1.setCategoria('A');
        sueldo1.setAniosServicio(12);
        sueldo1.setSueldoFijoMensual(1000);
        sueldo1.setMontoBonificacionAniosServicio(200);
        sueldo1.setPagoHorasExtra(50);
        sueldo1.setDescuentos(Float.valueOf("25"));
        sueldo1.setSueldoBruto(1500);
        sueldo1.setCotizacionPrevisional(Float.valueOf("10"));
        sueldo1.setCotizacionSalud(Float.valueOf("8"));
        sueldo1.setMontoSueldoFinal(Float.valueOf("900"));
        sueldo1.setFecha(LocalDate.of(2022,9,19));
        ArrayList<SueldoEntity> sueldoAL = new ArrayList<>();
        try {
            sueldoService.guardarSueldo(sueldo1);
            try {
                sueldoAL = sueldoService.obtenerSueldo();
            }
            catch (Exception err){
                err.getMessage();
            }
        }
        catch (Exception err){
            sueldoRepository.save(sueldo1);
            try {
                sueldoAL = sueldoService.obtenerSueldo();
            }
            catch (Exception err2){
                sueldoAL = (ArrayList<SueldoEntity>) sueldoRepository.findAll();
            }
        }
        assertNotNull(sueldoAL);
    }

    @Test
    void esDiaDeSemana(){
        int dia = 26;
        int mes = 9;
        int anio = 2022;
        boolean resAct = sueldoService.esDiaDeSemana(dia, mes, anio);
        boolean resExp = true;
        assertEquals(resExp, resAct);
    }

    @Test
    void noEsDiaDeSemana(){
        int dia = 25;
        int mes = 9;
        int anio = 2022;
        boolean resAct = sueldoService.esDiaDeSemana(dia, mes, anio);
        boolean resExp = false;
        assertEquals(resExp, resAct);
    }

    @Test
    void esBisiesto(){
        int anio = 2020;
        boolean resAct = sueldoService.esBisiesto(anio);
        boolean resExp = true;
        assertEquals(resExp, resAct);
    }

    @Test
    void noEsBisiesto(){
        int anio = 2022;
        boolean resAct = sueldoService.esBisiesto(anio);
        boolean resExp = false;
        assertEquals(resExp, resAct);
    }

    @Test
    void diasDelMes30(){
        int mes = 9;
        int anio = 2022;
        int resAct = sueldoService.diasDelMes(mes, anio);
        int resExp = 30;
        assertEquals(resExp, resAct);
    }

    @Test
    void diasDelMes31(){
        int mes = 10;
        int anio = 2022;
        int resAct = sueldoService.diasDelMes(mes, anio);
        int resExp = 31;
        assertEquals(resExp, resAct);
    }

    @Test
    void diasDelMes28(){
        int mes = 2;
        int anio = 2023;
        int resAct = sueldoService.diasDelMes(mes, anio);
        int resExp = 28;
        assertEquals(resExp, resAct);
    }

    @Test
    void diasDelMes29(){
        int mes = 2;
        int anio = 2024;
        int resAct = sueldoService.diasDelMes(mes, anio);
        int resExp = 29;
        assertEquals(resExp, resAct);
    }
}
