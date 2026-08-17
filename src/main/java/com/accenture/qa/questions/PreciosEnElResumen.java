package com.accenture.qa.questions;

import com.accenture.qa.userinterfaces.PaginaCheckout;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Precios de los articulos listados en el resumen del checkout.
 *
 * Se usan para comprobar que el subtotal informado corresponde realmente a la
 * suma de lo que el usuario esta comprando.
 */
public class PreciosEnElResumen implements Question<Collection<BigDecimal>> {

    public static PreciosEnElResumen deLosArticulos() {
        return new PreciosEnElResumen();
    }

    @Override
    public Collection<BigDecimal> answeredBy(Actor actor) {
        return Text.ofEach(PaginaCheckout.PRECIOS_DE_ITEMS)
                .answeredBy(actor)
                .stream()
                .map(precio -> precio.replace("$", "").trim())
                .map(BigDecimal::new)
                .toList();
    }
}
