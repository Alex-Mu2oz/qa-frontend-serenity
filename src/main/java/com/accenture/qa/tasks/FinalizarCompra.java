package com.accenture.qa.tasks;

import com.accenture.qa.interactions.DesplazarseHasta;
import com.accenture.qa.userinterfaces.PaginaCheckout;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Confirma la compra desde la pantalla de resumen.
 */
public class FinalizarCompra implements Task {

    public static FinalizarCompra desdeElResumen() {
        return instrumented(FinalizarCompra.class);
    }

    @Override
    @Step("{0} finaliza la compra")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                DesplazarseHasta.el(PaginaCheckout.BOTON_FINALIZAR),
                Click.on(PaginaCheckout.BOTON_FINALIZAR)
        );
    }
}
