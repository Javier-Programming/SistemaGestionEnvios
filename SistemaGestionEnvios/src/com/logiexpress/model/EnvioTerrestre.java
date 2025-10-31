/**
 * Representa un envío terrestre con reglas de negocio específicas.
 * Calcula el costo y tiempo de entrega según el peso, distancia y prioridad.
 * 
 * Extiende la clase {Envio} para incluir información específica sobre
 * los envíos que se realizan por vía terrestre.
 * 
 * Reglas de negocio:
 * - Costo base: $5.000 por kilogramo
 * - Costo por distancia: $500 por cada 100 km
 * - Recargo por prioridad EXPRESS: +50%
 * - Tiempo de entrega: 1 día por cada 500 km (mínimo 1 día)
 * - Peso máximo permitido: 5.000 kg
 * 
 * @author  Andrés Torres Díaz
 * @version 1.0
 * @since   2025-10-29
 */

package com.logiexpress.model;

import java.time.LocalDate;
import java.util.UUID;

import com.logiexpress.enums.EstadoEnvio;
import com.logiexpress.enums.Prioridad;
import com.logiexpress.enums.TipoEnvio;

public class EnvioTerrestre extends Envio {
    // Atributo adicional
    private double distanciaKm;

    // Constructor
    public EnvioTerrestre(String origen, String destino, double peso, Prioridad prioridad, double distanciaKm) {
        super(
                UUID.randomUUID().toString(), // ID generado automáticamente
                origen,
                destino,
                peso,
                prioridad,
                EstadoEnvio.PENDIENTE, // Estado inicial
                LocalDate.now(), // Fecha actual
                TipoEnvio.TERRESTRE // Tipo de envío
        );
        if (peso <= 0 || distanciaKm <= 0)
            throw new IllegalArgumentException("Peso y distancia deben ser mayores que cero");
        if (peso > 5000)
            throw new IllegalArgumentException("Peso máximo permitido: 5000 kg");
        this.distanciaKm = distanciaKm;
    }

    // Métodos abstractos
    @Override
    public double calcularCosto() {
        double costo = peso * 5000 + (distanciaKm / 100) * 500;
        if (prioridad == Prioridad.EXPRESS)
            costo *= 1.5;
        return costo;
    }

    @Override
    public int calcularTiempoEntrega() {
        return Math.max(1, (int) Math.ceil(distanciaKm / 500));
    }

    @Override
    public String obtenerDetallesEspecificos() {
        return String.format("Distancia: %.2f km, Tiempo estimado: %d días", distanciaKm, calcularTiempoEntrega());
    }
}
