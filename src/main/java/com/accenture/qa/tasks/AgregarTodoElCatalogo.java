package com.accenture.qa.tasks;

import com.accenture.qa.questions.NombresDeProductos;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import java.util.List;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Agrega al carrito todos los productos visibles en el catalogo.
 *
 * Se resuelven primero los nombres y luego se pulsa el boton de cada producto,
 * en lugar de recorrer una lista de botones. Al hacer clic, el boton cambia de
 * "Add to cart" a "Remove" y el elemento original queda obsoleto; trabajar por
 * nombre evita esa referencia rancia y garantiza un unico intento por producto,
 * que es lo que permite medir cuantos se agregaron realmente.
 */
public class AgregarTodoElCatalogo implements Task {

    public static AgregarTodoElCatalogo disponible() {
        return instrumented(AgregarTodoElCatalogo.class);
    }

    @Override
    @Step("{0} intenta agregar al carrito todos los productos del catalogo")
    public <T extends Actor> void performAs(T actor) {
        List<String> productos = List.copyOf(
                NombresDeProductos.enElCatalogo().answeredBy(actor));

        actor.attemptsTo(AgregarAlCarrito.losProductos(productos));
    }
}
