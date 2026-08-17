package stepdefinitions;

import com.accenture.qa.models.Credencial;
import com.accenture.qa.questions.MensajeDeError;
import com.accenture.qa.questions.TituloDeLaSeccion;
import com.accenture.qa.tasks.Autenticarse;
import com.accenture.qa.tasks.CerrarSesion;
import com.accenture.qa.userinterfaces.PaginaLogin;
import com.accenture.qa.utils.ConfiguracionDelSitio;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.CurrentVisibility;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class AutenticacionStepDefinitions {

    @Dado("que {word} se encuentra en la pagina de inicio de sesion")
    public void queElUsuarioEstaEnLaPaginaDeLogin(String nombreActor) {
        theActorCalled(nombreActor).attemptsTo(
                Open.url(ConfiguracionDelSitio.urlBase())
        );
    }

    @Dado("que {word} ha iniciado sesion con el usuario estandar")
    public void queElUsuarioHaIniciadoSesion(String nombreActor) {
        theActorCalled(nombreActor).attemptsTo(
                Open.url(ConfiguracionDelSitio.urlBase()),
                Autenticarse.con(Credencial.estandar())
        );
    }

    @Cuando("ingresa con el usuario estandar")
    public void ingresaConElUsuarioEstandar() {
        theActorInTheSpotlight().attemptsTo(Autenticarse.con(Credencial.estandar()));
    }

    @Cuando("ingresa con el usuario bloqueado")
    public void ingresaConElUsuarioBloqueado() {
        theActorInTheSpotlight().attemptsTo(Autenticarse.con(Credencial.bloqueado()));
    }

    @Cuando("ingresa con el usuario {string} y la clave {string}")
    public void ingresaConCredenciales(String usuario, String clave) {
        theActorInTheSpotlight().attemptsTo(Autenticarse.con(Credencial.de(usuario, clave)));
    }

    @Cuando("cierra su sesion")
    public void cierraSuSesion() {
        theActorInTheSpotlight().attemptsTo(CerrarSesion.enElSitio());
    }

    @Entonces("deberia ver el catalogo de productos")
    public void deberiaVerElCatalogo() {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(TituloDeLaSeccion.mostrado()).isEqualTo("Products")
        );
    }

    @Entonces("deberia ver el mensaje de error {string}")
    public void deberiaVerElMensajeDeError(String mensajeEsperado) {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(MensajeDeError.visible()).contains(mensajeEsperado)
        );
    }

    @Y("no deberia acceder al catalogo")
    public void noDeberiaAccederAlCatalogo() {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(CurrentVisibility.of(PaginaLogin.BOTON_INGRESAR)).isTrue()
        );
    }

    @Entonces("deberia regresar a la pagina de inicio de sesion")
    public void deberiaRegresarALaPaginaDeLogin() {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(CurrentVisibility.of(PaginaLogin.BOTON_INGRESAR)).isTrue()
        );
    }
}
