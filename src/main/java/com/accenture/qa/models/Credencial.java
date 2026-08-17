package com.accenture.qa.models;

/**
 * Credenciales de acceso al sitio.
 *
 * Los usuarios y la clave son los que SauceDemo publica en su propia pantalla
 * de login para uso de pruebas, por lo que no constituyen datos sensibles.
 */
public record Credencial(String usuario, String clave) {

    private static final String CLAVE_COMPARTIDA = "secret_sauce";

    public static Credencial estandar() {
        return new Credencial("standard_user", CLAVE_COMPARTIDA);
    }

    public static Credencial bloqueado() {
        return new Credencial("locked_out_user", CLAVE_COMPARTIDA);
    }

    public static Credencial conProblemas() {
        return new Credencial("problem_user", CLAVE_COMPARTIDA);
    }

    public static Credencial de(String usuario, String clave) {
        return new Credencial(usuario, clave);
    }
}
