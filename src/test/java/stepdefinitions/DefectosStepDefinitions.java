package stepdefinitions;

import com.accenture.qa.models.Credencial;
import com.accenture.qa.questions.ImagenesDeProductos;
import com.accenture.qa.questions.PreciosDeProductos;
import com.accenture.qa.questions.ValorDelCampo;
import com.accenture.qa.tasks.AgregarTodoElCatalogo;
import com.accenture.qa.tasks.Autenticarse;
import com.accenture.qa.userinterfaces.PaginaCarrito;
import com.accenture.qa.userinterfaces.PaginaCheckout;
import com.accenture.qa.utils.ConfiguracionDelSitio;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.ensure.Ensure;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class DefectosStepDefinitions {

    private static final String CLAVE_COMPARTIDA = "secret_sauce";

    @Dado("que {word} ha iniciado sesion con el usuario {string}")
    public void haIniciadoSesionCon(String nombreActor, String usuario) {
        theActorCalled(nombreActor).attemptsTo(
                Open.url(ConfiguracionDelSitio.urlBase()),
                Autenticarse.con(Credencial.de(usuario, CLAVE_COMPARTIDA))
        );
    }

    @Cuando("agrega al carrito todos los productos del catalogo")
    public void agregaTodosLosProductos() {
        theActorInTheSpotlight().attemptsTo(AgregarTodoElCatalogo.disponible());
    }

    @Y("el catalogo deberia mostrar {int} imagenes distintas")
    public void elCatalogoDeberiaMostrarImagenesDistintas(int cantidadEsperada) {
        var imagenes = ImagenesDeProductos.enElCatalogo().answeredBy(theActorInTheSpotlight());
        int distintas = new HashSet<>(imagenes).size();

        theActorInTheSpotlight().attemptsTo(
                Ensure.that(distintas).isEqualTo(cantidadEsperada)
        );
    }

    @Entonces("los precios no deberian quedar ordenados de menor a mayor")
    public void losPreciosNoDeberianQuedarOrdenados() {
        List<BigDecimal> precios = List.copyOf(
                PreciosDeProductos.enElCatalogo().answeredBy(theActorInTheSpotlight()));

        theActorInTheSpotlight().attemptsTo(
                Ensure.that(precios).isNotEqualTo(precios.stream().sorted().toList())
        );
    }

    @Y("escribe {string} en el nombre y {string} en el apellido del checkout")
    public void escribeEnElFormularioDeCheckout(String nombre, String apellido) {
        theActorInTheSpotlight().attemptsTo(
                Click.on(PaginaCarrito.BOTON_CHECKOUT),
                Enter.theValue(nombre).into(PaginaCheckout.CAMPO_NOMBRE),
                Enter.theValue(apellido).into(PaginaCheckout.CAMPO_APELLIDO)
        );
    }

    @Entonces("el campo apellido deberia quedar vacio")
    public void elCampoApellidoDeberiaQuedarVacio() {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(ValorDelCampo.en(PaginaCheckout.CAMPO_APELLIDO)).isEmpty()
        );
    }

    @Y("el campo nombre no deberia contener {string}")
    public void elCampoNombreNoDeberiaContener(String valorNoEsperado) {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(ValorDelCampo.en(PaginaCheckout.CAMPO_NOMBRE)).isNotEqualTo(valorNoEsperado)
        );
    }
}
