package com.accenture.qa.questions;

import com.accenture.qa.userinterfaces.PaginaCheckout;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

/**
 * Encabezado de confirmacion mostrado al completar una compra.
 */
public class MensajeDeConfirmacion implements Question<String> {

    public static MensajeDeConfirmacion deLaCompra() {
        return new MensajeDeConfirmacion();
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(PaginaCheckout.MENSAJE_DE_CONFIRMACION).answeredBy(actor).trim();
    }
}
