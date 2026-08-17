package com.accenture.qa.tasks;

import com.accenture.qa.models.CriterioDeOrden;
import com.accenture.qa.userinterfaces.PaginaInventario;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.SelectFromOptions;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Aplica un criterio de ordenamiento al catalogo.
 */
public class OrdenarProductos implements Task {

    private final CriterioDeOrden criterio;

    public OrdenarProductos(CriterioDeOrden criterio) {
        this.criterio = criterio;
    }

    public static OrdenarProductos por(CriterioDeOrden criterio) {
        return instrumented(OrdenarProductos.class, criterio);
    }

    @Override
    @Step("{0} ordena el catalogo por #criterio")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                SelectFromOptions.byValue(criterio.valor()).from(PaginaInventario.SELECTOR_DE_ORDEN)
        );
    }
}
