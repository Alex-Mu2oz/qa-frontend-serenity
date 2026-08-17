package com.accenture.qa.tasks;

import com.accenture.qa.userinterfaces.PaginaInventario;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Quita un producto del carrito desde el catalogo.
 */
public class RemoverDelCarrito implements Task {

    private final String producto;

    public RemoverDelCarrito(String producto) {
        this.producto = producto;
    }

    public static RemoverDelCarrito elProducto(String producto) {
        return instrumented(RemoverDelCarrito.class, producto);
    }

    @Override
    @Step("{0} remueve del carrito el producto #producto")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(PaginaInventario.BOTON_REMOVER_DE.of(producto)));
    }
}
