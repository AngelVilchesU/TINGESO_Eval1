package com.MueblesStgo.MueblesStgo.entities;

import javax.persistence.*;

@Entity
@Table(name = "Bonificacion")
public class BonificacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private float aniosServicio;
    private float bono;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getAniosServicio() {
        return aniosServicio;
    }

    public void setAniosServicio(float aniosServicio) {
        this.aniosServicio = aniosServicio;
    }

    public float getBono() {
        return bono;
    }

    public void setBono(float bono) {
        this.bono = bono;
    }
}
