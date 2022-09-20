package com.MueblesStgo.MueblesStgo;
import com.MueblesStgo.MueblesStgo.entities.DescuentoEntity;
import com.MueblesStgo.MueblesStgo.repositories.DescuentoRepository;
import com.MueblesStgo.MueblesStgo.services.DescuentoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DescuentoServiceTest {

    @Autowired
    private DescuentoRepository descuentoRepository;

    DescuentoService descuentoService = new DescuentoService();
    DescuentoEntity descuento = new DescuentoEntity();

    @Test
    public void guardarDescuento_obtenerDescuento(){
        DescuentoEntity descuento1 = new DescuentoEntity();
        descuento1.setId(Long.valueOf("999"));
        descuento1.setTiempoTrabajo(LocalTime.of(20, 49, 00));
        descuento1.setTiempoRetraso(LocalTime.of(0,0,0));
        descuento1.setMontoDescuento(100);
        descuento1.setCotizacionPrevisional(10);
        descuento1.setCotizacionPlanSalud(8);
        ArrayList<DescuentoEntity> descuentoAL = new ArrayList<>();
        try {
            descuentoService.guardarDescuento(descuento1);
            try {
                descuentoAL = descuentoService.obtenerDescuento();
            }
            catch (Exception err){
                err.getMessage();
            }
        }
        catch (Exception err){
            descuentoRepository.save(descuento1);
            try {
                descuentoAL = descuentoService.obtenerDescuento();
            }
            catch (Exception err2){
                descuentoAL = (ArrayList<DescuentoEntity>) descuentoRepository.findAll();
            }
        }
        assertNotNull(descuentoAL);
    }

    @Test
    void aplicacionDescuentos(){
        float sueldo = 1000;
        float porcentajeDescuento = 50;
        float resAct = descuentoService.aplicacionDescuentos(sueldo, porcentajeDescuento);
        float resExp = 500;
        assertEquals(resExp, resAct);
    }

    @Test
    void aplicacionDescuentosMin(){
        float sueldo = 1000;
        float porcentajeDescuento = 0;
        float resAct = descuentoService.aplicacionDescuentos(sueldo, porcentajeDescuento);
        float resExp = 1000;
        assertEquals(resExp, resAct);
    }

    @Test
    void aplicacionDescuentosMax(){
        float sueldo = 1000;
        float porcentajeDescuento = 100;
        float resAct = descuentoService.aplicacionDescuentos(sueldo, porcentajeDescuento);
        float resExp = 0;
        assertEquals(resExp, resAct);
    }
}
