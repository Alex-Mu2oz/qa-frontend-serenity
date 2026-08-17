package com.accenture.qa.tasks;

import com.accenture.qa.userinterfaces.MenuLateral;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Despliega el menu lateral y cierra la sesion.
 */
public class CerrarSesion implements Task {

    public static CerrarSesion enElSitio() {
        return instrumented(CerrarSesion.class);
    }

    @Override
    @Step("{0} cierra su sesion")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(MenuLateral.BOTON_MENU),
                // El menu se despliega con animacion: sin la espera el clic
                // puede caer sobre el enlace todavia oculto.
                WaitUntil.the(MenuLateral.OPCION_CERRAR_SESION, isVisible()).forNoMoreThan(10).seconds(),
                Click.on(MenuLateral.OPCION_CERRAR_SESION)
        );
    }
}
