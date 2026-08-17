package com.accenture.qa.questions;

import com.accenture.qa.userinterfaces.PaginaInventario;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Precios visibles en el catalogo, convertidos a numero.
 *
 * Se usa BigDecimal y no double porque son valores monetarios y la comparacion
 * de ordenamiento debe ser exacta.
 */
public class PreciosDeProductos implements Question<Collection<BigDecimal>> {

    public static PreciosDeProductos enElCatalogo() {
        return new PreciosDeProductos();
    }

    @Override
    public Collection<BigDecimal> answeredBy(Actor actor) {
        return Text.ofEach(PaginaInventario.PRECIOS_DE_PRODUCTO)
                .answeredBy(actor)
                .stream()
                .map(precio -> precio.replace("$", "").trim())
                .map(BigDecimal::new)
                .toList();
    }
}
