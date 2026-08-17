package stepdefinitions;

import com.accenture.qa.models.CriterioDeOrden;
import com.accenture.qa.questions.NombresDeProductos;
import com.accenture.qa.questions.PreciosDeProductos;
import com.accenture.qa.tasks.OrdenarProductos;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.ensure.Ensure;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class FiltradoStepDefinitions {

    private IllegalArgumentException errorAlResolverCriterio;

    @Cuando("ordena el catalogo por {string}")
    public void ordenaElCatalogoPor(String descripcionDelCriterio) {
        theActorInTheSpotlight().attemptsTo(
                OrdenarProductos.por(CriterioDeOrden.desdeDescripcion(descripcionDelCriterio))
        );
    }

    @Entonces("los productos deberian quedar ordenados alfabeticamente de forma ascendente")
    public void losProductosDeberianQuedarOrdenadosAlfabeticamenteAscendente() {
        List<String> nombres = nombresVisibles();

        theActorInTheSpotlight().attemptsTo(
                Ensure.that(nombres).isEqualTo(nombres.stream().sorted().toList())
        );
    }

    @Entonces("los productos deberian quedar ordenados alfabeticamente de forma descendente")
    public void losProductosDeberianQuedarOrdenadosAlfabeticamenteDescendente() {
        List<String> nombres = nombresVisibles();

        theActorInTheSpotlight().attemptsTo(
                Ensure.that(nombres).isEqualTo(
                        nombres.stream().sorted(Comparator.reverseOrder()).toList())
        );
    }

    @Entonces("los precios deberian quedar ordenados de menor a mayor")
    public void losPreciosDeberianQuedarOrdenadosDeMenorAMayor() {
        List<BigDecimal> precios = preciosVisibles();

        theActorInTheSpotlight().attemptsTo(
                Ensure.that(precios).isEqualTo(precios.stream().sorted().toList())
        );
    }

    @Entonces("los precios deberian quedar ordenados de mayor a menor")
    public void losPreciosDeberianQuedarOrdenadosDeMayorAMenor() {
        List<BigDecimal> precios = preciosVisibles();

        theActorInTheSpotlight().attemptsTo(
                Ensure.that(precios).isEqualTo(
                        precios.stream().sorted(Comparator.reverseOrder()).toList())
        );
    }

    @Entonces("el primer producto listado deberia ser {string}")
    public void elPrimerProductoListadoDeberiaSer(String nombreEsperado) {
        List<String> nombres = nombresVisibles();

        theActorInTheSpotlight().attemptsTo(
                Ensure.that(nombres).isNotEmpty(),
                Ensure.that(nombres.get(0)).isEqualTo(nombreEsperado)
        );
    }

    @Cuando("intenta ordenar el catalogo por {string}")
    public void intentaOrdenarElCatalogoPor(String descripcionInvalida) {
        try {
            CriterioDeOrden.desdeDescripcion(descripcionInvalida);
            errorAlResolverCriterio = null;
        } catch (IllegalArgumentException e) {
            errorAlResolverCriterio = e;
        }
    }

    @Entonces("la automatizacion deberia rechazar el criterio indicando los valores validos")
    public void laAutomatizacionDeberiaRechazarElCriterio() {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(errorAlResolverCriterio != null).isTrue(),
                Ensure.that(errorAlResolverCriterio.getMessage())
                        .contains("Criterio de orden no reconocido"),
                Ensure.that(errorAlResolverCriterio.getMessage())
                        .contains("NOMBRE_ASCENDENTE")
        );
    }

    private List<String> nombresVisibles() {
        return List.copyOf(
                NombresDeProductos.enElCatalogo().answeredBy(theActorInTheSpotlight()));
    }

    private List<BigDecimal> preciosVisibles() {
        return List.copyOf(
                PreciosDeProductos.enElCatalogo().answeredBy(theActorInTheSpotlight()));
    }
}
