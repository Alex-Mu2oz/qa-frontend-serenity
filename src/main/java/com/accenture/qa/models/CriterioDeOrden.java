package com.accenture.qa.models;

import java.util.Arrays;

/**
 * Criterios del selector de ordenamiento del catalogo.
 *
 * Centraliza la equivalencia entre el texto que ve el usuario, el valor que
 * maneja el {@code <select>} y el nombre en espanol usado en los escenarios
 * Gherkin, para que los .feature no dependan de literales de la interfaz.
 */
public enum CriterioDeOrden {

    NOMBRE_ASCENDENTE("az", "Name (A to Z)", "nombre ascendente"),
    NOMBRE_DESCENDENTE("za", "Name (Z to A)", "nombre descendente"),
    PRECIO_ASCENDENTE("lohi", "Price (low to high)", "precio ascendente"),
    PRECIO_DESCENDENTE("hilo", "Price (high to low)", "precio descendente");

    private final String valor;
    private final String textoVisible;
    private final String descripcion;

    CriterioDeOrden(String valor, String textoVisible, String descripcion) {
        this.valor = valor;
        this.textoVisible = textoVisible;
        this.descripcion = descripcion;
    }

    public String valor() {
        return valor;
    }

    public String textoVisible() {
        return textoVisible;
    }

    public String descripcion() {
        return descripcion;
    }

    /**
     * Resuelve el criterio a partir de la descripcion usada en los escenarios.
     */
    public static CriterioDeOrden desdeDescripcion(String descripcion) {
        return Arrays.stream(values())
                .filter(criterio -> criterio.descripcion.equalsIgnoreCase(descripcion.trim()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Criterio de orden no reconocido: '" + descripcion + "'. "
                                + "Valores validos: " + Arrays.toString(values())));
    }
}
