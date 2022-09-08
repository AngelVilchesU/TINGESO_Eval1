package com.MueblesStgo.MueblesStgo.entities;
import javax.persistence.*;


@Entity
@Table(name = "Categoria")
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private char categoria;
    float sueldoFijoMensual;
    float montoPorHora;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public char getCategoria() {
        return categoria;
    }

    public void setCategoria(char categoria) {
        this.categoria = categoria;
    }

    public float getSueldoFijoMensual() {
        return sueldoFijoMensual;
    }

    public void setSueldoFijoMensual(float sueldoFijoMensual) {
        this.sueldoFijoMensual = sueldoFijoMensual;
    }

    public float getMontoPorHora() {
        return montoPorHora;
    }

    public void setMontoPorHora(float montoPorHora) {
        this.montoPorHora = montoPorHora;
    }
}
