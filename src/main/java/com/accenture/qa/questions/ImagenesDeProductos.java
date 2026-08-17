package com.accenture.qa.questions;

import com.accenture.qa.userinterfaces.PaginaInventario;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.Collection;

/**
 * Direcciones de las imagenes de los productos del catalogo.
 *
 * Permite detectar catalogos que muestran la misma imagen para todos los
 * articulos, un defecto que no rompe ninguna funcionalidad pero degrada por
 * completo la experiencia de compra.
 */
public class ImagenesDeProductos implements Question<Collection<String>> {

    public static ImagenesDeProductos enElCatalogo() {
        return new ImagenesDeProductos();
    }

    @Override
    public Collection<String> answeredBy(Actor actor) {
        return PaginaInventario.IMAGENES_DE_PRODUCTO.resolveAllFor(actor)
                .stream()
                .map(imagen -> imagen.getAttribute("src"))
                .toList();
    }
}
