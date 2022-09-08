package com.MueblesStgo.MueblesStgo.entities;

import javax.persistence.*;

@Entity
@Table(name = "Descuento")
public class DescuentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private float tiempoRetraso;
    private float montoDescuento;
    private boolean justificativo;
    private float cotizacionPrevisional;
    private float cotizacionPlanSalud;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getTiempoRetraso() {
        return tiempoRetraso;
    }

    public void setTiempoRetraso(float tiempoRetraso) {
        this.tiempoRetraso = tiempoRetraso;
    }

    public float getMontoDescuento() {
        return montoDescuento;
    }

    public void setMontoDescuento(float montoDescuento) {
        this.montoDescuento = montoDescuento;
    }

    public boolean isJustificativo() {
        return justificativo;
    }

    public void setJustificativo(boolean justificativo) {
        this.justificativo = justificativo;
    }

    public float getCotizacionPrevisional() {
        return cotizacionPrevisional;
    }

    public void setCotizacionPrevisional(float cotizacionPrevisional) {
        this.cotizacionPrevisional = cotizacionPrevisional;
    }

    public float getCotizacionPlanSalud() {
        return cotizacionPlanSalud;
    }

    public void setCotizacionPlanSalud(float cotizacionPlanSalud) {
        this.cotizacionPlanSalud = cotizacionPlanSalud;
    }
}
