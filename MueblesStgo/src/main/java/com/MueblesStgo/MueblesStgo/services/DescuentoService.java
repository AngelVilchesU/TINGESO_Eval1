package com.MueblesStgo.MueblesStgo.services;

import com.MueblesStgo.MueblesStgo.entities.DescuentoEntity;
import com.MueblesStgo.MueblesStgo.repositories.DescuentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;

@Service
public class DescuentoService {
    @Autowired
    DescuentoRepository descuentoRepository;

    /*
    El siguiente método retorna un arreglo el cual contiene TODOS los descuentos de la base de datos
     */
    public ArrayList<DescuentoEntity> obtenerDescuento(){
        return (ArrayList<DescuentoEntity>) descuentoRepository.findAll();
    }

    /*
    El siguiente método permite guardar un descuento en la base de datos
     */
    public DescuentoEntity guardarDescuento(DescuentoEntity descuento){
        return descuentoRepository.save(descuento);
    }

    public float[] obtenerCotizaciones(){
        ArrayList<DescuentoEntity> descuentoEntities = (ArrayList<DescuentoEntity>) descuentoRepository.findAll();
        float[] cotizaciones = new float[2];
        cotizaciones[0] = descuentoEntities.get(0).getCotizacionPrevisional();
        cotizaciones[1] = descuentoEntities.get(0).getCotizacionPlanSalud();
        return cotizaciones;
    }

    public LocalTime tiempoNoTrabajo(LocalTime tiempoTrabajo){
        ArrayList<DescuentoEntity> descuentoEntityArrayList = obtenerDescuento();
        LocalTime tiempoFaltante = descuentoEntityArrayList.get(0).getTiempoTrabajo().minusHours(tiempoTrabajo.getHour());
        tiempoFaltante = tiempoFaltante.minusMinutes(tiempoTrabajo.getMinute());
        tiempoFaltante = tiempoFaltante.minusSeconds(tiempoTrabajo.getSecond());
        return tiempoFaltante;
    }

    public ArrayList<Float> descuento(LocalTime tiempoTrabajoFaltante){
        ArrayList<DescuentoEntity> descuentoEntityArrayList = obtenerDescuento();
        ArrayList<Float> descuentoApelar = new ArrayList<>();
        float porcentajeMinimoDescuento = 0;
        float puedeApelar = 0;
        int aux;
        int i = 0;
        for(i = 0; i < descuentoEntityArrayList.size(); i++){
            aux = tiempoTrabajoFaltante.compareTo(descuentoEntityArrayList.get(i).getTiempoRetraso());
            if(aux < 0){ // Si tiempoTrabajoFaltante < tiempoRetraso entonces...
                if(i == 0){ // No hay descuento
                    descuentoApelar.add(porcentajeMinimoDescuento);
                    descuentoApelar.add(puedeApelar);
                    return descuentoApelar;
                }
                descuentoApelar.add(descuentoEntityArrayList.get(i).getMontoDescuento());
                descuentoApelar.add(puedeApelar);
                return descuentoApelar;
            }
        }
        descuentoApelar.add(descuentoEntityArrayList.get(i - 1).getMontoDescuento());
        puedeApelar = puedeApelar + 1;
        descuentoApelar.add(puedeApelar);
        return descuentoApelar;
    }

    /*
    El siguiente método permite eliminar un descuento de la base de datos con su ID
     */
    public boolean eliminarDescuento(Long id){
        try {
            descuentoRepository.deleteById(id);
            return true;
        }
        catch (Exception err){
            return false;
        }
    }
}
