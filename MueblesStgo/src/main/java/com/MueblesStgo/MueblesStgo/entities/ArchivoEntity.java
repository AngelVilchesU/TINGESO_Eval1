package com.MueblesStgo.MueblesStgo.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "MarcaReloj")
public class ArchivoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private LocalDate fecha;
    private LocalTime horaIngresoSalida;
    private String rutEmpleado;

    public ArchivoEntity(LocalDate fecha, LocalTime horaIngresoSalida, String rutEmpleado) {
        this.fecha = fecha;
        this.horaIngresoSalida = horaIngresoSalida;
        this.rutEmpleado = rutEmpleado;
    }

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

    public String getRutEmpleado() {
        return rutEmpleado;
    }

    public void setRutEmpleado(String rutEmpleado) {
        this.rutEmpleado = rutEmpleado;
    }

}
