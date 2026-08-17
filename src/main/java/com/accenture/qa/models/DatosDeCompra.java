package com.accenture.qa.models;

/**
 * Datos que exige el formulario de checkout.
 */
public record DatosDeCompra(String nombre, String apellido, String codigoPostal) {

    public static DatosDeCompra validos() {
        return new DatosDeCompra("Wilson", "Munoz", "050001");
    }

    public static DatosDeCompra de(String nombre, String apellido, String codigoPostal) {
        return new DatosDeCompra(nombre, apellido, codigoPostal);
    }
}
