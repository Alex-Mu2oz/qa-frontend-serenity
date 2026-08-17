package stepdefinitions;

import com.accenture.qa.models.DatosDeCompra;
import com.accenture.qa.models.TotalesDeCompra;
import com.accenture.qa.questions.MensajeDeConfirmacion;
import com.accenture.qa.questions.MensajeDeError;
import com.accenture.qa.questions.PreciosEnElResumen;
import com.accenture.qa.questions.ProductosEnElCarrito;
import com.accenture.qa.questions.ResumenDeCompra;
import com.accenture.qa.questions.TituloDeLaSeccion;
import com.accenture.qa.tasks.AbrirElCarrito;
import com.accenture.qa.tasks.DiligenciarDatosDeCompra;
import com.accenture.qa.tasks.FinalizarCompra;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.ensure.Ensure;

import java.math.BigDecimal;
import java.util.List;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class CarritoStepDefinitions {

    @Cuando("abre el carrito de compras")
    public void abreElCarritoDeCompras() {
        theActorInTheSpotlight().attemptsTo(AbrirElCarrito.deCompras());
    }

    @Cuando("diligencia los datos de compra validos")
    public void diligenciaLosDatosDeCompraValidos() {
        theActorInTheSpotlight().attemptsTo(
                DiligenciarDatosDeCompra.con(DatosDeCompra.validos())
        );
    }

    @Cuando("diligencia los datos de compra con nombre {string}, apellido {string} y codigo postal {string}")
    public void diligenciaLosDatosDeCompra(String nombre, String apellido, String codigoPostal) {
        theActorInTheSpotlight().attemptsTo(
                DiligenciarDatosDeCompra.con(DatosDeCompra.de(nombre, apellido, codigoPostal))
        );
    }

    @Cuando("finaliza la compra")
    public void finalizaLaCompra() {
        theActorInTheSpotlight().attemptsTo(FinalizarCompra.desdeElResumen());
    }

    @Entonces("el carrito deberia contener los productos:")
    public void elCarritoDeberiaContenerLosProductos(List<String> productosEsperados) {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(ProductosEnElCarrito.listados())
                        .containsElementsFrom(productosEsperados)
        );
    }

    @Entonces("el carrito deberia contener {int} productos")
    public void elCarritoDeberiaContenerProductos(int cantidadEsperada) {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(ProductosEnElCarrito.listados()).hasSize(cantidadEsperada)
        );
    }

    @Entonces("deberia ver la seccion {string}")
    public void deberiaVerLaSeccion(String tituloEsperado) {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(TituloDeLaSeccion.mostrado()).isEqualTo(tituloEsperado)
        );
    }

    @Entonces("deberia ver la confirmacion {string}")
    public void deberiaVerLaConfirmacion(String mensajeEsperado) {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(MensajeDeConfirmacion.deLaCompra()).isEqualTo(mensajeEsperado)
        );
    }

    @Y("deberia ver el error del formulario {string}")
    public void deberiaVerElErrorDelFormulario(String mensajeEsperado) {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(MensajeDeError.visible()).contains(mensajeEsperado)
        );
    }

    @Entonces("el subtotal deberia ser la suma de los articulos")
    public void elSubtotalDeberiaSerLaSumaDeLosArticulos() {
        BigDecimal sumaDeArticulos = PreciosEnElResumen.deLosArticulos()
                .answeredBy(theActorInTheSpotlight())
                .stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        TotalesDeCompra totales = ResumenDeCompra.mostrado().answeredBy(theActorInTheSpotlight());

        theActorInTheSpotlight().attemptsTo(
                Ensure.that(totales.subtotal()).isEqualTo(sumaDeArticulos)
        );
    }

    @Y("el total deberia ser el subtotal mas el impuesto")
    public void elTotalDeberiaSerElSubtotalMasElImpuesto() {
        TotalesDeCompra totales = ResumenDeCompra.mostrado().answeredBy(theActorInTheSpotlight());

        theActorInTheSpotlight().attemptsTo(
                Ensure.that(totales.total()).isEqualTo(totales.totalEsperado())
        );
    }
}
