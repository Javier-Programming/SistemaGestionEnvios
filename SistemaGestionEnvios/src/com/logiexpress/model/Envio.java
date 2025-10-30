package com.logiexpress.model;

import java.time.LocalDate;
import java.util.UUID;

import com.logiexpress.enums.EstadoEnvio;
import com.logiexpress.enums.Prioridad;
import com.logiexpress.enums.TipoEnvio;

public abstract class Envio {
    // Atributos
    protected String id;
    protected String origen;
    protected String destino;
    protected double peso; // En kilogramos
    protected Prioridad prioridad;
    protected EstadoEnvio estado;
    protected LocalDate fechaEnvio;
    protected TipoEnvio tipoEnvio;

    // Constructor
    public Envio(String id, String origen, String destino, double peso, Prioridad prioridad, EstadoEnvio estado,
            LocalDate fechaEnvio, TipoEnvio tipoEnvio) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
        this.prioridad = prioridad;
        this.estado = estado;
        this.fechaEnvio = fechaEnvio;
        this.tipoEnvio = tipoEnvio;
    }

    public Envio(String id2, String origen2, String destino2, double peso2, Prioridad prioridad2,
            boolean esInternacional) {
        //TODO Auto-generated constructor stub
    }
    // Métodos abstractos
    public abstract double calcularCosto();

    public abstract int calcularTiempoEntrega(); // retorna días

    public abstract String obtenerDetallesEspecificos();

    // Métodos concretos
    public final String generarNumeroSeguimiento() {
        return UUID.randomUUID().toString(); // Genera un ID único
    }

    public void actualizarEstado(EstadoEnvio nuevoEstado) {
        this.estado = nuevoEstado;
    }

    // Getters y Seders
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public EstadoEnvio getEstado() {
        return estado;
    }

    public void setEstado(EstadoEnvio estado) {
        this.estado = estado;
    }

    public LocalDate getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDate fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public TipoEnvio getTipoEnvio() {
        return tipoEnvio;
    }

    public void setTipoEnvio(TipoEnvio tipoEnvio) {
        this.tipoEnvio = tipoEnvio;
    }
}
