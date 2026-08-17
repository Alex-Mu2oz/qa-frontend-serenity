package com.accenture.qa.questions;

import com.accenture.qa.userinterfaces.PaginaLogin;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

/**
 * Texto del mensaje de error mostrado al usuario.
 *
 * Sirve tanto para el login como para el formulario de checkout: ambos usan
 * el mismo contenedor [data-test='error'].
 */
public class MensajeDeError implements Question<String> {

    public static MensajeDeError visible() {
        return new MensajeDeError();
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(PaginaLogin.MENSAJE_DE_ERROR).answeredBy(actor).trim();
    }
}
