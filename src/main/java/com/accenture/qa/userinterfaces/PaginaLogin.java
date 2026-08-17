package com.accenture.qa.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

/**
 * Elementos de la pantalla de inicio de sesion.
 *
 * Se prefieren los atributos data-test sobre los ids o las clases CSS porque
 * el sitio los expone justamente como puntos de anclaje para automatizacion,
 * lo que los hace menos sensibles a cambios de maquetacion.
 */
public class PaginaLogin {

    public static final Target CAMPO_USUARIO = Target.the("campo de usuario")
            .locatedBy("[data-test='username']");

    public static final Target CAMPO_CLAVE = Target.the("campo de clave")
            .locatedBy("[data-test='password']");

    public static final Target BOTON_INGRESAR = Target.the("boton de ingreso")
            .locatedBy("[data-test='login-button']");

    public static final Target MENSAJE_DE_ERROR = Target.the("mensaje de error del login")
            .locatedBy("[data-test='error']");

    public static final Target LOGO = Target.the("logo de Swag Labs")
            .locatedBy(".login_logo");

    private PaginaLogin() {
        // Contenedor de Targets: no instanciable.
    }
}
