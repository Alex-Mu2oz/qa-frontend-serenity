package com.accenture.qa.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

/**
 * Elementos del flujo de checkout: datos del comprador, resumen y confirmacion.
 */
public class PaginaCheckout {

    public static final Target CAMPO_NOMBRE = Target.the("campo nombre")
            .locatedBy("[data-test='firstName']");

    public static final Target CAMPO_APELLIDO = Target.the("campo apellido")
            .locatedBy("[data-test='lastName']");

    public static final Target CAMPO_CODIGO_POSTAL = Target.the("campo codigo postal")
            .locatedBy("[data-test='postalCode']");

    public static final Target BOTON_CONTINUAR = Target.the("boton continuar")
            .locatedBy("[data-test='continue']");

    public static final Target BOTON_FINALIZAR = Target.the("boton finalizar compra")
            .locatedBy("[data-test='finish']");

    public static final Target MENSAJE_DE_ERROR = Target.the("mensaje de error del formulario")
            .locatedBy("[data-test='error']");

    public static final Target SUBTOTAL_DEL_RESUMEN = Target.the("subtotal de articulos")
            .locatedBy(".summary_subtotal_label");

    public static final Target IMPUESTO_DEL_RESUMEN = Target.the("impuesto del resumen")
            .locatedBy(".summary_tax_label");

    public static final Target TOTAL_DEL_RESUMEN = Target.the("total del resumen")
            .locatedBy(".summary_total_label");

    public static final Target PRECIOS_DE_ITEMS = Target.the("precios de los items del resumen")
            .locatedBy(".cart_item .inventory_item_price");

    public static final Target MENSAJE_DE_CONFIRMACION = Target.the("mensaje de confirmacion")
            .locatedBy(".complete-header");

    private PaginaCheckout() {
        // Contenedor de Targets: no instanciable.
    }
}
