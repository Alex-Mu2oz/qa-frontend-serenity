package com.accenture.qa.questions;

import com.accenture.qa.userinterfaces.PaginaInventario;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

/**
 * Titulo de la seccion activa: "Products", "Your Cart", "Checkout: ...".
 * Sirve para confirmar en que punto del flujo se encuentra el usuario.
 */
public class TituloDeLaSeccion implements Question<String> {

    public static TituloDeLaSeccion mostrado() {
        return new TituloDeLaSeccion();
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(PaginaInventario.TITULO_SECCION).answeredBy(actor).trim();
    }
}
