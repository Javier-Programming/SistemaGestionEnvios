/**
 * Representa un envío terrestre con reglas de negocio específicas.
 * Calcula el costo y tiempo de entrega según el peso, distancia y prioridad.
 * 
 * Reglas de negocio:
 * - Costo base: $5.000 por kilogramo
 * - Costo por distancia: $500 por cada 100 km
 * - Recargo por prioridad EXPRESS: +50%
 * - Tiempo de entrega: 1 día por cada 500 km (mínimo 1 día)
 * - Peso máximo permitido: 5.000 kg
 * 
 * @author Andres Torres
 * @version 1.0
 */

package com.logiexpress.model;

import com.logiexpress.enums.Prioridad;

public class EnvioTerrestre extends Envio {
    // atributos
    public double distanciaKm;

    // constructor
    public EnvioTerrestre(String origen, String destino, double peso, Prioridad prioridad,
            double distanciaKm) {
        super(origen, destino, peso, prioridad, distanciaKm);

        // 🔹 Validaciones
        if (peso <= 0) {
            throw new IllegalArgumentException("El peso debe ser mayor que 0 kg.");
        }
        if (peso > 5000) {
            throw new IllegalArgumentException("El peso máximo permitido es de 5000 kg.");
        }
        if (distanciaKm <= 0) {
            throw new IllegalArgumentException("La distancia debe ser mayor que 0 km.");
        }

        this.distanciaKm = distanciaKm;
    }

    // Costo base: $5.000 por kg
    // Costo por distancia: $500 por cada 100 km
    // Recargo express: +50%
    @Override
    public double calcularCosto() {
        double costoBase = getPeso() * 5000;
        double costoDistancia = (distanciaKm / 100) * 500;
        double costoTotal = costoBase + costoDistancia;

        if (getPrioridad() == Prioridad.EXPRESS) {
            costoTotal *= 1.5; // recargo del 50%
        }

        return costoTotal;
    }

    @Override
    public int calcularTiempoEntrega() {
        return Math.max(1, (int) Math.ceil(distanciaKm / 500.0)); // math.max devuelve el valor mayor en una divicion
                                                                  // math.ceil redondea un numero hacia arrriba al
                                                                  // entero mas cercano
    }

    @Override
    public String obtenerDetallesEspecificos() {
        return String.format(
                "Envío terrestre de %.2f kg a %.0f km. Prioridad: %s. Costo: $%.2f. Tiempo estimado: %d días.",
                getPeso(),
                distanciaKm,
                getPrioridad(),
                calcularCosto(),
                calcularTiempoEntrega());
    }

}
