package com.accenture.qa.tasks;

import com.accenture.qa.userinterfaces.PaginaInventario;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import java.util.List;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Agrega al carrito uno o varios productos identificados por su nombre visible.
 *
 * Localizar por nombre —y no por el id generado a partir del titulo— hace que
 * los escenarios se lean en el lenguaje del negocio y resistan cambios en la
 * convencion de identificadores del sitio.
 */
public class AgregarAlCarrito implements Task {

    private final List<String> productos;

    public AgregarAlCarrito(List<String> productos) {
        this.productos = productos;
    }

    public static AgregarAlCarrito losProductos(List<String> productos) {
        return instrumented(AgregarAlCarrito.class, productos);
    }

    public static AgregarAlCarrito elProducto(String producto) {
        return instrumented(AgregarAlCarrito.class, List.of(producto));
    }

    @Override
    @Step("{0} agrega al carrito los productos #productos")
    public <T extends Actor> void performAs(T actor) {
        productos.forEach(producto ->
                actor.attemptsTo(Click.on(PaginaInventario.BOTON_AGREGAR_DE.of(producto)))
        );
    }
}
