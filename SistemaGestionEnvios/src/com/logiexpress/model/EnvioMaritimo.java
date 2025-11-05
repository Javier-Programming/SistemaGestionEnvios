/**
 * Representa un envío marítimo dentro del sistema LogiExpress.
 * 
 * Extiende la clase {Envio} para incluir información específica sobre
 * los envíos que se realizan por vía marítima, como el puerto de destino.
 * 
 * Este tipo de envío tiene una prioridad fija de {NORMAL}, un estado
 * inicial de {PENDIENTE} y su fecha de envío se asigna automáticamente
 * como la fecha actual.
 * 
 * El costo se calcula en función del peso, con un recargo adicional
 * para cargas mayores a 1000 kg. El tiempo promedio de entrega es de 30 días.
 * 
 * Reglas de negocio:
 *  Costo base: $2.000 por kilogramo
 *  Solo disponible para envíos internacionales
 *  Recargo contenedor: +$150.000 si peso > 1.000 kg
 *  Tiempo estimado: 15-45 días (puede usar promedio de 30 días)
 *  Sin límite de peso establecido
 *  Prioridad siempre NORMAL (no aplica express)
 * 
 * @author  Jorge Vargas
 * @version 1.0
 * @since   2025-10-29
 */
package com.logiexpress.model;

import java.time.LocalDate;
import java.util.UUID;

import com.logiexpress.enums.EstadoEnvio;
import com.logiexpress.enums.Prioridad;
import com.logiexpress.enums.TipoEnvio;

public class EnvioMaritimo extends Envio {
    // Atributo adicional
    private String puertoDestino;

    // Constructor
    public EnvioMaritimo(String origen, String destino, double peso, String puertoDestino) {
        super(
                UUID.randomUUID().toString(), // ID generado automáticamente
                origen,
                destino,
                peso,
                Prioridad.NORMAL, // Prioridad fija para marítimo
                EstadoEnvio.PENDIENTE, // Estado inicial
                LocalDate.now(), // Fecha actual
                TipoEnvio.MARITIMO // Tipo de envío
        );
        this.puertoDestino = puertoDestino;
        if (peso <= 0)
            throw new IllegalArgumentException("Peso debe ser mayor que cero");
    }

    // Métodos abstractos
    @Override
    public double calcularCosto() {
        double costo = peso * 2000;
        if (peso > 1000)
            costo += 150000;
        return costo;
    }

    @Override
    public int calcularTiempoEntrega() {
        return 30; // promedio
    }

    @Override
    public String obtenerDetallesEspecificos() {
        return String.format("Puerto destino: %s, Tiempo estimado: %d días", puertoDestino, calcularTiempoEntrega());
    }
}