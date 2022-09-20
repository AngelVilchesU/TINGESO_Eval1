package com.MueblesStgo.MueblesStgo;

import com.MueblesStgo.MueblesStgo.entities.ArchivoEntity;
import com.MueblesStgo.MueblesStgo.repositories.ArchivoRepository;
import com.MueblesStgo.MueblesStgo.services.ArchivoService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(MockitoExtension.class)
public class ArchivoServiceTest {

    @Mock
    private ArchivoRepository archivoRepository;

    @InjectMocks
    ArchivoService archivoService;

    @Test
    public void guardarMarca(){
        ArchivoEntity archivo = new ArchivoEntity(LocalDate.of(2022, 9,20), LocalTime.of(16,31,00), "12.345.678-9",null);
        Mockito.when(archivoRepository.save(archivo)).thenReturn(archivo);
        final ArchivoEntity resAct = archivoService.guardarMarca(archivo);
        assertEquals(archivo, resAct);
    }

    @Test
    public void obtenerMarca(){
        ArchivoEntity archivo = new ArchivoEntity(LocalDate.of(2022, 9,20), LocalTime.of(16,31,00), "12.345.678-9",null);
        ArrayList<ArchivoEntity> resExp = new ArrayList<>();
        resExp.add(archivo);
        Mockito.when((ArrayList<ArchivoEntity>) archivoRepository.findAll()).thenReturn(resExp);
        final ArrayList<ArchivoEntity> resAct = archivoService.obtenerMarcas();
        assertEquals(resExp, resAct);
    }


}
