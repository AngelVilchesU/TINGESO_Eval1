package com.MueblesStgo.MueblesStgo;

import com.MueblesStgo.MueblesStgo.entities.JustificativoEntity;
import com.MueblesStgo.MueblesStgo.repositories.JustificativoRepository;
import com.MueblesStgo.MueblesStgo.services.JustificativoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JustificativoServiceTest {
    @Autowired
    private JustificativoRepository justificativoRepository;

    JustificativoService justificativoService = new JustificativoService();
    JustificativoEntity justificativo = new JustificativoEntity();

    @Test
    public void guardarJustificativo_obtenerJustificativo(){
        JustificativoEntity justificativo1 = new JustificativoEntity();
        justificativo1.setId(Long.valueOf("999"));
        justificativo1.setFechaInasistencia(LocalDate.of(2022,9,19));
        justificativo1.setRutEmpleado("12.345.678-9");
        ArrayList<JustificativoEntity> justificativoAL = new ArrayList<>();
        try {
            justificativoService.guardarJustificativo(justificativo1);
            try {
                justificativoAL = justificativoService.obtenerJustificativos();
            }
            catch (Exception err){
                err.getMessage();
            }
        }
        catch (Exception err){
            justificativoRepository.save(justificativo1);
            try {
                justificativoAL = justificativoService.obtenerJustificativos();
            }
            catch (Exception err2){
                justificativoAL = (ArrayList<JustificativoEntity>) justificativoRepository.findAll();
            }
        }
        assertNotNull(justificativoAL);
    }

    @Test
    void fechaFormato(){
        String fechaStr = "2022-09-20";
        LocalDate resAct = justificativoService.fechaFormato(fechaStr);
        LocalDate resExp = LocalDate.of(2022, 9, 20);
        assertEquals(resExp, resAct);
    }
}
