package com.accenture.qa.questions;

import com.accenture.qa.userinterfaces.PaginaCarrito;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

import java.util.Collection;

/**
 * Nombres de los productos listados dentro del carrito.
 */
public class ProductosEnElCarrito implements Question<Collection<String>> {

    public static ProductosEnElCarrito listados() {
        return new ProductosEnElCarrito();
    }

    @Override
    public Collection<String> answeredBy(Actor actor) {
        return Text.ofEach(PaginaCarrito.NOMBRES_DE_ITEMS)
                .answeredBy(actor)
                .stream()
                .map(String::trim)
                .toList();
    }
}
