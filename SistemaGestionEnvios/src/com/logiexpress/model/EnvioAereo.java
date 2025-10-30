/**
 * Representa un envío de tipo aéreo dentro del sistema LogiExpress.
 * 
 * <p>Los envíos aéreos pueden ser nacionales o internacionales y su costo 
 * y tiempo de entrega varían según el peso, la prioridad y la modalidad.</p>
 * 
 * <p><b>Reglas de negocio:</b></p>
 * <ul>
 *   <li>Costo base: $15.000 por kilogramo</li>
 *   <li>Recargo internacional: $50.000 fijo</li>
 *   <li>Recargo prioridad express: +80% del costo total</li>
 *   <li>Tiempo nacional: 1–3 días</li>
 *   <li>Tiempo internacional: 3–7 días</li>
 *   <li>Peso máximo permitido: 1.000 kg</li>
 * </ul>
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
            LocalDate.now(),       // Fecha actual
            TipoEnvio.AEREO        // Tipo de envío
        );
        this.esInternacional = esInternacional;

        // Validar peso máximo
        if (peso > 1000){
            throw new IllegalArgumentException("El peso máximo permitido para envío aéreo es 1000 kg.");
        }
    }

    // Implementación de métodos abstractos
    @Override
    public double calcularCosto() {
        double costoBase = peso * 15000; // $15.000 por kg

        if (esInternacional){
            costoBase += 5000; // Recargo fijo internacional
        }

        // Recargo prioridad express: +80%
        if (prioridad == Prioridad.EXPRESS){
            costoBase *= 1.8;
        }
        return costoBase;
    }

    @Override
    public int calcularTiempoEntrega() {
        if (esInternacional) {
            // Internacional: 3–7 días
            return (prioridad == Prioridad.EXPRESS) ? 3 : 7;
        } else {
             // Nacional: 1–3 días
            return (prioridad == Prioridad.EXPRESS) ? 1 : 3;
        }
    }

    @Override
    public String obtenerDetallesEspecificos() {
        return "Envío Aéreo " + (esInternacional ? "Internacional" : "Nacional");
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
