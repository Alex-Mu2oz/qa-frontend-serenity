package stepdefinitions;

import com.accenture.qa.questions.CantidadEnElCarrito;
import com.accenture.qa.questions.NombresDeProductos;
import com.accenture.qa.questions.PreciosDeProductos;
import com.accenture.qa.tasks.AgregarAlCarrito;
import com.accenture.qa.tasks.RemoverDelCarrito;
import com.accenture.qa.userinterfaces.PaginaInventario;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.CurrentVisibility;

import java.math.BigDecimal;
import java.util.List;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class ProductosStepDefinitions {

    @Cuando("agrega al carrito el producto {string}")
    public void agregaAlCarritoElProducto(String producto) {
        theActorInTheSpotlight().attemptsTo(AgregarAlCarrito.elProducto(producto));
    }

    @Cuando("agrega al carrito los productos:")
    public void agregaAlCarritoLosProductos(List<String> productos) {
        theActorInTheSpotlight().attemptsTo(AgregarAlCarrito.losProductos(productos));
    }

    @Cuando("remueve del catalogo el producto {string}")
    public void removeDelCatalogoElProducto(String producto) {
        theActorInTheSpotlight().attemptsTo(RemoverDelCarrito.elProducto(producto));
    }

    @Entonces("el catalogo deberia mostrar {int} productos")
    public void elCatalogoDeberiaMostrarProductos(int cantidadEsperada) {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(NombresDeProductos.enElCatalogo()).hasSize(cantidadEsperada)
        );
    }

    @Entonces("cada producto deberia mostrar nombre y precio")
    public void cadaProductoDeberiaMostrarNombreYPrecio() {
        List<String> nombres = List.copyOf(
                NombresDeProductos.enElCatalogo().answeredBy(theActorInTheSpotlight()));
        List<BigDecimal> precios = List.copyOf(
                PreciosDeProductos.enElCatalogo().answeredBy(theActorInTheSpotlight()));

        theActorInTheSpotlight().attemptsTo(
                // Debe haber tantos precios como nombres: ninguna tarjeta incompleta
                Ensure.that(precios.size()).isEqualTo(nombres.size()),
                Ensure.that(nombres).allMatch("no esta vacio", nombre -> !nombre.isBlank()),
                Ensure.that(precios).allMatch("es mayor que cero",
                        precio -> precio.compareTo(BigDecimal.ZERO) > 0)
        );
    }

    @Entonces("el contador del carrito deberia mostrar {int}")
    public void elContadorDelCarritoDeberiaMostrar(int cantidadEsperada) {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(CantidadEnElCarrito.mostrada()).isEqualTo(cantidadEsperada)
        );
    }

    @Entonces("el carrito deberia quedar sin contador visible")
    public void elCarritoDeberiaQuedarSinContadorVisible() {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(CantidadEnElCarrito.mostrada()).isEqualTo(0)
        );
    }

    @Y("el producto {string} deberia ofrecer la opcion de removerlo")
    public void elProductoDeberiaOfrecerLaOpcionDeRemoverlo(String producto) {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(CurrentVisibility.of(PaginaInventario.BOTON_REMOVER_DE.of(producto))).isTrue()
        );
    }

    @Y("el producto {string} deberia ofrecer la opcion de agregarlo")
    public void elProductoDeberiaOfrecerLaOpcionDeAgregarlo(String producto) {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(CurrentVisibility.of(PaginaInventario.BOTON_AGREGAR_DE.of(producto))).isTrue()
        );
    }
}
