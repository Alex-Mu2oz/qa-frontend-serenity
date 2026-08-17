package com.accenture.qa.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

/**
 * Elementos de la pantalla del carrito de compras.
 */
public class PaginaCarrito {

    public static final Target TITULO_SECCION = Target.the("titulo de la seccion")
            .locatedBy(".title");

    public static final Target ITEMS = Target.the("items del carrito")
            .locatedBy(".cart_item");

    public static final Target NOMBRES_DE_ITEMS = Target.the("nombres de los items del carrito")
            .locatedBy(".cart_item .inventory_item_name");

    public static final Target BOTON_CHECKOUT = Target.the("boton de checkout")
            .locatedBy("[data-test='checkout']");

    public static final Target BOTON_SEGUIR_COMPRANDO = Target.the("boton seguir comprando")
            .locatedBy("[data-test='continue-shopping']");

    public static final Target BOTON_REMOVER_DE = Target.the("boton remover '{0}' del carrito")
            .locatedBy("//div[contains(@class,'inventory_item_name')][text()='{0}']"
                    + "/ancestor::div[@class='cart_item']//button[contains(@data-test,'remove')]");

    private PaginaCarrito() {
        // Contenedor de Targets: no instanciable.
    }
}
