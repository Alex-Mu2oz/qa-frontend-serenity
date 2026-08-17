package com.accenture.qa.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

/**
 * Elementos del menu lateral desplegable.
 */
public class MenuLateral {

    public static final Target BOTON_MENU = Target.the("boton del menu lateral")
            .locatedBy("#react-burger-menu-btn");

    public static final Target OPCION_CERRAR_SESION = Target.the("opcion cerrar sesion")
            .locatedBy("#logout_sidebar_link");

    private MenuLateral() {
        // Contenedor de Targets: no instanciable.
    }
}
