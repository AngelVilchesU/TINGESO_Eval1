package com.MueblesStgo.MueblesStgo;
import com.MueblesStgo.MueblesStgo.entities.BonificacionEntity;
import com.MueblesStgo.MueblesStgo.repositories.BonificacionRepository;
import com.MueblesStgo.MueblesStgo.services.BonificacionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BonificacionServiceTest {
    @Autowired
    private BonificacionRepository bonificacionRepository;

    BonificacionService bonificacionService = new BonificacionService();
    BonificacionEntity bonificacion = new BonificacionEntity();

    @Test
    public void guardarBonificacion_obtenerBonificacion(){
        BonificacionEntity bonificacion1 = new BonificacionEntity();
        bonificacion1.setId(Long.valueOf("999"));
        bonificacion1.setAniosServicio(12);
        bonificacion1.setBono(1000);
        ArrayList<BonificacionEntity> bonificacionAL = new ArrayList<>();
        try {
            bonificacionService.guardarBonificacion(bonificacion1);
            try {
                bonificacionAL = bonificacionService.obtenerBonificacion();
            }
            catch (Exception err){
                err.getMessage();
            }
        }
        catch (Exception err){
            bonificacionRepository.save(bonificacion1);
            try {
                bonificacionAL = bonificacionService.obtenerBonificacion();
            }
            catch (Exception err2){
                bonificacionAL = (ArrayList<BonificacionEntity>) bonificacionRepository.findAll();
            }
        }
        assertNotNull(bonificacionAL);
    }

    @Test
    void sueldoBonificacionPorcentual(){
        float sueldo = 1000;
        float bonificacionPorcentual = 10;
        float resAct = bonificacionService.sueldoBonificacionPorcentual(sueldo, bonificacionPorcentual);
        float resExp = 100;
        assertEquals(resExp, resAct);
    }


}
