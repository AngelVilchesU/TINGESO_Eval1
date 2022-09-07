package com.MueblesStgo.MueblesStgo.models;
import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Date;

@Entity // Modelo real
@Table(name = "Empleado") // Control del nombre de la tabla en la BD
public class EmpleadoModel {
    @Id // El dato corresponde a un Identificador
    @GeneratedValue(strategy = GenerationType.IDENTITY) // El Id se genera de forma automatica incrementable
    @Column(unique = true, nullable = false) // El Id es unico y no debe ser nulo
    private Long id;
    private String rut;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private char categoria;
    private Date fechaIngresoEmpresa;

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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public char getCategoria() {
        return categoria;
    }

    public void setCategoria(char categoria) {
        this.categoria = categoria;
    }

    public Date getFechaIngresoEmpresa() {
        return fechaIngresoEmpresa;
    }

    public void setFechaIngresoEmpresa(Date fechaIngresoEmpresa) {
        this.fechaIngresoEmpresa = fechaIngresoEmpresa;
    }
}
