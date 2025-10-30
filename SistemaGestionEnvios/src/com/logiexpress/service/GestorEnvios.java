package com.logiexpress.service;

import com.logiexpress.model.*;
import com.logiexpress.enums.*;
import java.util.ArrayList;

public class GestorEnvios {
    // Lista para almacenar los envíos
    private ArrayList<Envio> listaEnvios = new ArrayList<>();

    // === MÉTODOS PARA GESTIONAR ENVÍOS === //
    // Agregar un nuevo envío
    public void agregarEnvio(Envio envio) {
        listaEnvios.add(envio);
    }

    // Listar todos los envíos
    public void listarTodosLosEnvios() {
        listaEnvios.forEach(System.out::println);
    }

    // Buscar envío por ID
    public Envio buscarEnvioPorId(String id) {
        return listaEnvios.stream().filter(envioEncontrado -> envioEncontrado.getId().equals(id)).findFirst()
                .orElse(null);
    }

    // Calcular costo total de todos los envíos
    public double calcularCostoTotal() {
        return listaEnvios.stream().mapToDouble(Envio::calcularCosto).sum();
    }

    // Filtrar por tipo
    public ArrayList<Envio> filtrarPorTipo(TipoEnvio tipo) {
        ArrayList<Envio> filtrados = new ArrayList<>();
        for (Envio envioEncontrado : listaEnvios)
            if (envioEncontrado.getTipoEnvio() == tipo)
                filtrados.add(envioEncontrado);
        return filtrados;
    }

    // Filtrar por estado
    public ArrayList<Envio> filtrarPorEstado(EstadoEnvio estado) {
        ArrayList<Envio> filtrados = new ArrayList<>();
        for (Envio envioEncontrado : listaEnvios)
            if (envioEncontrado.getEstado() == estado)
                filtrados.add(envioEncontrado);
        return filtrados;
    }

    // Reporte
    public void generarReporte() {
        System.out.println("Total envíos: " + listaEnvios.size());
        System.out.printf("Costo total: $%.2f%n", calcularCostoTotal());
        System.out.printf("Costo promedio: $%.2f%n", calcularCostoTotal() / Math.max(listaEnvios.size(), 1));
    }
}
