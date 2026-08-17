package com.accenture.qa.questions;

import com.accenture.qa.models.TotalesDeCompra;
import com.accenture.qa.userinterfaces.PaginaCheckout;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;

import java.math.BigDecimal;

/**
 * Cifras del resumen del checkout, extraidas de sus etiquetas.
 *
 * El sitio las presenta como texto con prefijo —"Item total: $39.98",
 * "Tax: $3.20", "Total: $43.18"— por lo que se aisla el importe posterior
 * al simbolo de moneda.
 */
public class ResumenDeCompra implements Question<TotalesDeCompra> {

    public static ResumenDeCompra mostrado() {
        return new ResumenDeCompra();
    }

    @Override
    public TotalesDeCompra answeredBy(Actor actor) {
        return new TotalesDeCompra(
                importeDe(PaginaCheckout.SUBTOTAL_DEL_RESUMEN, actor),
                importeDe(PaginaCheckout.IMPUESTO_DEL_RESUMEN, actor),
                importeDe(PaginaCheckout.TOTAL_DEL_RESUMEN, actor)
        );
    }

    private BigDecimal importeDe(Target etiqueta, Actor actor) {
        String texto = Text.of(etiqueta).answeredBy(actor);
        int inicio = texto.indexOf('$');

        if (inicio < 0) {
            throw new IllegalStateException(
                    "No se encontro un importe en la etiqueta: '" + texto + "'");
        }
        return new BigDecimal(texto.substring(inicio + 1).trim());
    }
}
