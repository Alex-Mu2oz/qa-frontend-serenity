package com.accenture.qa.utils;

import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.environment.SystemEnvironmentVariables;

/**
 * Acceso a la configuracion definida en serenity.conf.
 *
 * Resolver la URL base aqui —y no con un placeholder en una anotacion—
 * garantiza que se lea la seccion del ambiente activo
 * ({@code -Denvironment=...}) y evita depender de que Serenity instancie el
 * PageObject por reflexion para expandir la expresion.
 */
public final class ConfiguracionDelSitio {

    private static final String LLAVE_URL_BASE = "webdriver.base.url";

    private ConfiguracionDelSitio() {
        // Clase de utilidades: no instanciable.
    }

    public static String urlBase() {
        String url = EnvironmentSpecificConfiguration
                .from(SystemEnvironmentVariables.currentEnvironmentVariables())
                .getProperty(LLAVE_URL_BASE);

        if (url == null || url.isBlank()) {
            throw new IllegalStateException(
                    "No se encontro '" + LLAVE_URL_BASE + "' en serenity.conf. "
                            + "Verifique la seccion environments del archivo de configuracion.");
        }
        return url;
    }
}
