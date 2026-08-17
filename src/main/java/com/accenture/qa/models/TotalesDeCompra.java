package com.accenture.qa.models;

import java.math.BigDecimal;

/**
 * Cifras del resumen previo a confirmar la compra.
 */
public record TotalesDeCompra(BigDecimal subtotal, BigDecimal impuesto, BigDecimal total) {

    /**
     * @return el total que deberia cobrarse segun el subtotal y el impuesto.
     */
    public BigDecimal totalEsperado() {
        return subtotal.add(impuesto);
    }
}
