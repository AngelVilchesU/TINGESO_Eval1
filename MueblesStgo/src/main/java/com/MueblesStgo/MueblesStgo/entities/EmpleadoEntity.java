package com.MueblesStgo.MueblesStgo.entities;
import javax.persistence.Entity;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity // Modelo real
@Table(name = "Empleado") // Control del nombre de la tabla en la BD
public class EmpleadoEntity {
    @Id // El dato corresponde a un Identificador
    @GeneratedValue(strategy = GenerationType.IDENTITY) // El Id se genera de forma automatica incrementable
    @Column(unique = true, nullable = false) // El Id es unico y no debe ser nulo
    private Long id;
    private String rut;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private LocalDate fechaIngresoEmpresa;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRut() {
        return rut;
    }
    public void setRut(String rut) {
        this.rut = rut;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public LocalDate getFechaIngresoEmpresa() {
        return fechaIngresoEmpresa;
    }
    public void setFechaIngresoEmpresa(LocalDate fechaIngresoEmpresa) {
        this.fechaIngresoEmpresa = fechaIngresoEmpresa;
    }
}
