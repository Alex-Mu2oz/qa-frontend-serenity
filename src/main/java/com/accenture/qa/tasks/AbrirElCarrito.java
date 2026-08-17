package com.accenture.qa.tasks;

import com.accenture.qa.userinterfaces.PaginaInventario;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Navega del catalogo al detalle del carrito.
 */
public class AbrirElCarrito implements Task {

    public static AbrirElCarrito deCompras() {
        return instrumented(AbrirElCarrito.class);
    }

    @Override
    @Step("{0} abre el carrito de compras")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(PaginaInventario.ICONO_CARRITO));
    }
}
