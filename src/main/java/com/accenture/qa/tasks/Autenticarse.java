package com.accenture.qa.tasks;

import com.accenture.qa.models.Credencial;
import com.accenture.qa.userinterfaces.PaginaLogin;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Diligencia el formulario de login y lo envia.
 *
 * No afirma nada sobre el resultado: se usa tanto en los flujos exitosos como
 * en los negativos, y es cada escenario el que valida lo que espera.
 */
public class Autenticarse implements Task {

    private final Credencial credencial;

    public Autenticarse(Credencial credencial) {
        this.credencial = credencial;
    }

    public static Autenticarse con(Credencial credencial) {
        return instrumented(Autenticarse.class, credencial);
    }

    @Override
    @Step("{0} se autentica con el usuario #credencial")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(credencial.usuario()).into(PaginaLogin.CAMPO_USUARIO),
                Enter.theValue(credencial.clave()).into(PaginaLogin.CAMPO_CLAVE),
                Click.on(PaginaLogin.BOTON_INGRESAR)
        );
    }
}
