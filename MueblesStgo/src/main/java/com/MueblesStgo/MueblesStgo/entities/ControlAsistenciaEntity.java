package com.MueblesStgo.MueblesStgo.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Control_Asistencia")
public class ControlAsistenciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private LocalDate fecha;
    private LocalTime horaIngresoSalida;
    private String rut;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraIngresoSalida() {
        return horaIngresoSalida;
    }

    public void setHoraIngresoSalida(LocalTime horaIngresoSalida) {
        this.horaIngresoSalida = horaIngresoSalida;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }
}
