package com.accenture.qa.questions;

import com.accenture.qa.userinterfaces.PaginaInventario;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

import java.util.Collection;

/**
 * Nombres de los productos visibles en el catalogo, en el orden en que se
 * muestran en pantalla.
 *
 * Se expone como Collection y no como List porque es el tipo que espera la
 * API de Ensure para sus validaciones sobre colecciones.
 */
public class NombresDeProductos implements Question<Collection<String>> {

    public static NombresDeProductos enElCatalogo() {
        return new NombresDeProductos();
    }

    @Override
    public Collection<String> answeredBy(Actor actor) {
        return Text.ofEach(PaginaInventario.NOMBRES_DE_PRODUCTO)
                .answeredBy(actor)
                .stream()
                .map(String::trim)
                .toList();
    }
}
