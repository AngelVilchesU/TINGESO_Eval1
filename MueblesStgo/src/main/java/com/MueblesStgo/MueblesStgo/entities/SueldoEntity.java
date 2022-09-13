package com.MueblesStgo.MueblesStgo.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity // Indica que corresponde a una entidad de persistencia
@Table(name = "Sueldo") // Nombre que adoptará la base de datos
public class SueldoEntity {

    // Atributos
    @Id // Permite que la BD visualice el ID como tal
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generado automáticamente e incrementable
    @Column(unique = true, nullable = false) // Es único y no puede ser nulo
    private Long id;
    private String rutEmpleado;
    private float sueldoMensual;
    private LocalDate fecha;

    // Relaciones
    @OneToOne
    @JoinColumn(name = "Descuento")
    DescuentoEntity descuento;

    // Métodos
    public SueldoEntity(Long id, String rutEmpleado, float sueldoMensual, LocalDate fecha) {
        this.id = id;
        this.rutEmpleado = rutEmpleado;
        this.sueldoMensual = sueldoMensual;
        this.fecha = fecha;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRutEmpleado() {
        return rutEmpleado;
    }
    public void setRutEmpleado(String rutEmpleado) {
        this.rutEmpleado = rutEmpleado;
    }
    public float getSueldoMensual() {
        return sueldoMensual;
    }
    public void setSueldoMensual(float sueldoMensual) {
        this.sueldoMensual = sueldoMensual;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public DescuentoEntity getDescuento() {
        return descuento;
    }
    public void setDescuento(DescuentoEntity descuento) {
        this.descuento = descuento;
    }
    public SueldoEntity() {
    }
}
