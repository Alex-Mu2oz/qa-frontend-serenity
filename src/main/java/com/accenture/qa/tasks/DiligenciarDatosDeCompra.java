package com.accenture.qa.tasks;

import com.accenture.qa.models.DatosDeCompra;
import com.accenture.qa.userinterfaces.PaginaCarrito;
import com.accenture.qa.userinterfaces.PaginaCheckout;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Inicia el checkout y diligencia el formulario del comprador.
 *
 * Los campos vacios se omiten deliberadamente para poder reutilizar la tarea
 * en los escenarios negativos de validacion del formulario.
 */
public class DiligenciarDatosDeCompra implements Task {

    private final DatosDeCompra datos;

    public DiligenciarDatosDeCompra(DatosDeCompra datos) {
        this.datos = datos;
    }

    public static DiligenciarDatosDeCompra con(DatosDeCompra datos) {
        return instrumented(DiligenciarDatosDeCompra.class, datos);
    }

    @Override
    @Step("{0} diligencia los datos de compra #datos")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(PaginaCarrito.BOTON_CHECKOUT));

        escribirSiHayValor(actor, datos.nombre(), PaginaCheckout.CAMPO_NOMBRE);
        escribirSiHayValor(actor, datos.apellido(), PaginaCheckout.CAMPO_APELLIDO);
        escribirSiHayValor(actor, datos.codigoPostal(), PaginaCheckout.CAMPO_CODIGO_POSTAL);

        actor.attemptsTo(Click.on(PaginaCheckout.BOTON_CONTINUAR));
    }

    private <T extends Actor> void escribirSiHayValor(
            T actor, String valor, net.serenitybdd.screenplay.targets.Target campo) {
        if (valor != null && !valor.isBlank()) {
            actor.attemptsTo(Enter.theValue(valor).into(campo));
        }
    }
}
