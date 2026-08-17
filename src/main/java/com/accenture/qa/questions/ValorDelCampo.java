package com.accenture.qa.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

/**
 * Contenido real de un campo de formulario.
 *
 * Se lee el atributo value y no el texto del elemento, porque en un input el
 * texto visible no forma parte del DOM como contenido sino como valor.
 */
public class ValorDelCampo implements Question<String> {

    private final Target campo;

    public ValorDelCampo(Target campo) {
        this.campo = campo;
    }

    public static ValorDelCampo en(Target campo) {
        return new ValorDelCampo(campo);
    }

    @Override
    public String answeredBy(Actor actor) {
        String valor = campo.resolveFor(actor).getAttribute("value");
        return valor == null ? "" : valor;
    }
}
