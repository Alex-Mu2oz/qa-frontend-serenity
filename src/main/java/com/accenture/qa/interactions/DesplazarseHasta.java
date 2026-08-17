package com.accenture.qa.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.JavascriptExecutor;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Desplaza la pagina hasta dejar un elemento a la vista.
 *
 * En el resumen del checkout el boton de finalizar queda por debajo del area
 * visible cuando el carrito tiene varios productos. Aunque el WebDriver suele
 * desplazarse solo antes de un clic, hacerlo de forma explicita evita clics
 * interceptados por elementos superpuestos y hace el paso visible en el reporte.
 */
public class DesplazarseHasta implements Interaction {

    private final Target objetivo;

    public DesplazarseHasta(Target objetivo) {
        this.objetivo = objetivo;
    }

    public static DesplazarseHasta el(Target objetivo) {
        return instrumented(DesplazarseHasta.class, objetivo);
    }

    @Override
    @Step("{0} se desplaza hasta #objetivo")
    public <T extends Actor> void performAs(T actor) {
        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
        js.executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                objetivo.resolveFor(actor));
    }
}
