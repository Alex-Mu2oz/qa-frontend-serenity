package com.accenture.qa.questions;

import com.accenture.qa.userinterfaces.PaginaInventario;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

/**
 * Numero que muestra el contador del icono del carrito.
 *
 * El contador no existe en el DOM cuando el carrito esta vacio, por lo que la
 * ausencia del elemento se traduce a cero en lugar de propagar un error.
 */
public class CantidadEnElCarrito implements Question<Integer> {

    public static CantidadEnElCarrito mostrada() {
        return new CantidadEnElCarrito();
    }

    @Override
    public Integer answeredBy(Actor actor) {
        Target contador = PaginaInventario.CONTADOR_DEL_CARRITO;

        if (!contador.resolveAllFor(actor).isEmpty()) {
            String texto = contador.resolveFor(actor).getText().trim();
            return texto.isEmpty() ? 0 : Integer.parseInt(texto);
        }
        return 0;
    }
}
