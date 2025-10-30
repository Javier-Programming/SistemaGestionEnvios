/**
 * Representa un envío de tipo aéreo dentro del sistema LogiExpress.
 * 
 * Extiende la clase {Envio} para incluir información específica sobre
 * los envíos que se realizan por vía aérea.
 * 
 * Los envíos aéreos pueden ser nacionales o internacionales y su costo 
 * y tiempo de entrega varían según el peso, la prioridad y la modalidad.
 * 
 * Reglas de negocio:
 *  Costo base: $15.000 por kilogramo
 *  Recargo internacional: $50.000 fijo
 *  Recargo prioridad express: +80% del costo total
 *  Tiempo nacional: 1–3 días
 *  Tiempo internacional: 3–7 días
 *  Peso máximo permitido: 1.000 kg
 *
 * @author  Javier Díaz
 * @version 1.0
 * @since   2025-10-29
 */
package com.logiexpress.model;

import java.time.LocalDate;
import java.util.UUID;

import com.logiexpress.enums.EstadoEnvio;
import com.logiexpress.enums.Prioridad;
import com.logiexpress.enums.TipoEnvio;

public class EnvioAereo extends Envio {
    // Atributo adicional
    private boolean esInternacional;

    // Constructor
    public EnvioAereo(String origen, String destino, double peso, Prioridad prioridad, boolean esInternacional) {
        super(
                UUID.randomUUID().toString(), // ID generado automáticamente
                origen,
                destino,
                peso,
                prioridad,
                EstadoEnvio.PENDIENTE, // Estado inicial
                LocalDate.now(), // Fecha actual
                TipoEnvio.AEREO // Tipo de envío
        );
        this.esInternacional = esInternacional;

        // Validar peso máximo
        if (peso > 1000) {
            throw new IllegalArgumentException("El peso máximo permitido para envío aéreo es 1000 kg.");
        }
    }

    // Métodos abstractos
    @Override
    public double calcularCosto() {
        double costoBase = peso * 15000; // $15.000 por kg

        if (esInternacional) {
            costoBase += 5000; // Recargo fijo internacional
        }

        // Recargo prioridad express: +80%
        if (prioridad == Prioridad.EXPRESS) {
            costoBase *= 1.8;
        }
        return costoBase;
    }

    @Override
    public int calcularTiempoEntrega() {
        if (esInternacional) {
            // Internacional: 3–7 días
            if (prioridad == Prioridad.EXPRESS) {
                return 3;
            } else {
                return 7;
            }
        } else {
            // Nacional: 1–3 días
            if (prioridad == Prioridad.EXPRESS) {
                return 1;
            } else {
                return 3;
            }
        }
    }

    @Override
    public String obtenerDetallesEspecificos() {
        if (esInternacional) {
            return "Internacional";
        } else {
            return "Nacional";
        }
    }

    // Getter y Setter
    public boolean isEsInternacional() {
        return esInternacional;
    }

    public void setEsInternacional(boolean esInternacional) {
        this.esInternacional = esInternacional;
    }

    // toString
    @Override
    public String toString() {
        return super.toString() +
                "\nTipo: AÉREO" +
                "\nInternacional: " + (esInternacional ? "Sí" : "No") +
                "\nCosto estimado: $" + String.format("%,.2f", calcularCosto()) +
                "\nTiempo estimado: " + calcularTiempoEntrega() + " días";
    }
}
