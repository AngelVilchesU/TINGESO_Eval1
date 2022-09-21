package com.MueblesStgo.MueblesStgo;
import com.MueblesStgo.MueblesStgo.entities.DescuentoEntity;
import com.MueblesStgo.MueblesStgo.repositories.DescuentoRepository;
import com.MueblesStgo.MueblesStgo.services.DescuentoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(MockitoExtension.class)
public class DescuentoServiceTest {

    @Mock
    private DescuentoRepository descuentoRepository;

    @InjectMocks
    DescuentoService descuentoService;

    @Test
    public void guardarDescuento(){
        DescuentoEntity descuento = new DescuentoEntity(Long.valueOf("999"), LocalTime.of(19, 53, 00), LocalTime.of(0,10,0), Float.valueOf("100"), Float.valueOf("10"), Float.valueOf("8"));
        Mockito.when(descuentoRepository.save(descuento)).thenReturn(descuento);
        final DescuentoEntity resAct = descuentoService.guardarDescuento(descuento);
        assertEquals(descuento, resAct);
    }

    @Test
    public void obtenerDescuento(){
        DescuentoEntity descuento = new DescuentoEntity(Long.valueOf("999"), LocalTime.of(19, 53, 00), LocalTime.of(0,10,0), Float.valueOf("100"), Float.valueOf("10"), Float.valueOf("8"));
        ArrayList<DescuentoEntity> resExp = new ArrayList<>();
        resExp.add(descuento);
        Mockito.when((ArrayList<DescuentoEntity>) descuentoRepository.findAll()).thenReturn(resExp);
        final ArrayList<DescuentoEntity> resAct = descuentoService.obtenerDescuento();
        assertEquals(resExp, resAct);
    }

    @Test
    public void obtenerCotizaciones(){
        DescuentoEntity descuento = new DescuentoEntity(Long.valueOf("999"), LocalTime.of(19, 53, 00), LocalTime.of(0,10,0), Float.valueOf("100"), Float.valueOf("10"), Float.valueOf("8"));
        ArrayList<DescuentoEntity> resExp = new ArrayList<>();
        resExp.add(descuento);
        Mockito.when((ArrayList<DescuentoEntity>) descuentoRepository.findAll()).thenReturn(resExp);
        float[] resAct = descuentoService.obtenerCotizaciones();
        assertEquals(Float.valueOf("10"), resAct[0]);
    }

    @Test
    public void obtenerCotizaciones1(){
        DescuentoEntity descuento = new DescuentoEntity(Long.valueOf("999"), LocalTime.of(19, 53, 00), LocalTime.of(0,10,0), Float.valueOf("100"), Float.valueOf("10"), Float.valueOf("8"));
        ArrayList<DescuentoEntity> resExp = new ArrayList<>();
        resExp.add(descuento);
        Mockito.when((ArrayList<DescuentoEntity>) descuentoRepository.findAll()).thenReturn(resExp);
        float[] resAct = descuentoService.obtenerCotizaciones();
        assertEquals(Float.valueOf("8"), resAct[1]);
    }

    @Test
    public void tiempoNoTrabajo(){
        DescuentoEntity descuento = new DescuentoEntity(Long.valueOf("999"), LocalTime.of(19, 53, 00), LocalTime.of(0,10,0), Float.valueOf("100"), Float.valueOf("10"), Float.valueOf("8"));
        ArrayList<DescuentoEntity> resExp = new ArrayList<>();
        resExp.add(descuento);
        Mockito.when((ArrayList<DescuentoEntity>) descuentoRepository.findAll()).thenReturn(resExp);
        LocalTime resAct = descuentoService.tiempoNoTrabajo(LocalTime.of(19,52,0));
        assertEquals(LocalTime.of(0,1,0), resAct);
    }

    @Test
    public void descuento(){
        DescuentoEntity descuento = new DescuentoEntity(Long.valueOf("999"), LocalTime.of(19, 53, 00), LocalTime.of(0,10,0), Float.valueOf("100"), Float.valueOf("10"), Float.valueOf("8"));
        ArrayList<DescuentoEntity> resExp = new ArrayList<>();
        resExp.add(descuento);
        Mockito.when((ArrayList<DescuentoEntity>) descuentoRepository.findAll()).thenReturn(resExp);
        ArrayList<Float> resAct = descuentoService.descuento(LocalTime.of(0,10,0));
        assertEquals(Float.valueOf("100"), resAct.get(0));
        assertEquals(Float.valueOf("1"), resAct.get(1));
    }

    @Test
    public void noDescuento(){
        DescuentoEntity descuento = new DescuentoEntity(Long.valueOf("999"), LocalTime.of(19, 53, 00), LocalTime.of(0,10,0), Float.valueOf("100"), Float.valueOf("10"), Float.valueOf("8"));
        ArrayList<DescuentoEntity> resExp = new ArrayList<>();
        resExp.add(descuento);
        Mockito.when((ArrayList<DescuentoEntity>) descuentoRepository.findAll()).thenReturn(resExp);
        ArrayList<Float> resAct = descuentoService.descuento(LocalTime.of(0,5,0));
        assertEquals(Float.valueOf("0"), resAct.get(0));
        assertEquals(Float.valueOf("0"), resAct.get(1));
    }

    @Test
    public void inDescuento(){
        DescuentoEntity descuento = new DescuentoEntity(Long.valueOf("999"), LocalTime.of(19, 53, 00), LocalTime.of(0,10,0), Float.valueOf("100"), Float.valueOf("10"), Float.valueOf("8"));
        DescuentoEntity descuento1 = new DescuentoEntity(Long.valueOf("998"), LocalTime.of(10, 30, 00), LocalTime.of(0,5,0), Float.valueOf("50"), Float.valueOf("10"), Float.valueOf("8"));
        ArrayList<DescuentoEntity> resExp = new ArrayList<>();
        resExp.add(descuento);
        resExp.add(descuento1);
        Mockito.when((ArrayList<DescuentoEntity>) descuentoRepository.findAll()).thenReturn(resExp);
        ArrayList<Float> resAct = descuentoService.descuento(LocalTime.of(0,4,0));
        assertEquals(Float.valueOf("0"), resAct.get(0));
        assertEquals(Float.valueOf("0"), resAct.get(1));
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
