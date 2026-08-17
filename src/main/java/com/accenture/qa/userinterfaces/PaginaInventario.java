package com.accenture.qa.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

/**
 * Elementos del catalogo de productos.
 */
public class PaginaInventario {

    public static final Target TITULO_SECCION = Target.the("titulo de la seccion")
            .locatedBy(".title");

    public static final Target LISTA_DE_PRODUCTOS = Target.the("contenedor del catalogo")
            .locatedBy(".inventory_list");

    public static final Target PRODUCTOS = Target.the("tarjetas de producto")
            .locatedBy(".inventory_item");

    public static final Target NOMBRES_DE_PRODUCTO = Target.the("nombres de los productos")
            .locatedBy(".inventory_item_name");

    public static final Target PRECIOS_DE_PRODUCTO = Target.the("precios de los productos")
            .locatedBy(".inventory_item_price");

    public static final Target IMAGENES_DE_PRODUCTO = Target.the("imagenes de los productos")
            .locatedBy(".inventory_item_img img");

    public static final Target SELECTOR_DE_ORDEN = Target.the("selector de ordenamiento")
            .locatedBy(".product_sort_container");

    public static final Target BOTONES_AGREGAR = Target.the("botones de agregar al carrito")
            .locatedBy("button[data-test^='add-to-cart']");

    /**
     * Boton de agregar al carrito de un producto identificado por su nombre.
     * Se navega desde el nombre hacia la tarjeta contenedora para no depender
     * del identificador generado a partir del titulo.
     */
    public static final Target BOTON_AGREGAR_DE = Target.the("boton agregar al carrito de '{0}'")
            .locatedBy("//div[@class='inventory_item_name ' or @class='inventory_item_name'][text()='{0}']"
                    + "/ancestor::div[@class='inventory_item']//button[contains(@data-test,'add-to-cart')]");

    public static final Target BOTON_REMOVER_DE = Target.the("boton remover del carrito de '{0}'")
            .locatedBy("//div[@class='inventory_item_name ' or @class='inventory_item_name'][text()='{0}']"
                    + "/ancestor::div[@class='inventory_item']//button[contains(@data-test,'remove')]");

    public static final Target NOMBRE_DE_PRODUCTO = Target.the("producto '{0}'")
            .locatedBy("//div[contains(@class,'inventory_item_name')][text()='{0}']");

    public static final Target ICONO_CARRITO = Target.the("icono del carrito")
            .locatedBy(".shopping_cart_link");

    public static final Target CONTADOR_DEL_CARRITO = Target.the("contador de items del carrito")
            .locatedBy(".shopping_cart_badge");

    private PaginaInventario() {
        // Contenedor de Targets: no instanciable.
    }
}
