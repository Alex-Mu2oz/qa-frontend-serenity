package runners;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

/**
 * Runner de la suite.
 *
 * Descubre todos los .feature bajo src/test/resources/features y los enlaza
 * con las step definitions del paquete {@code stepdefinitions}.
 *
 * Ejecucion completa:
 *   ./gradlew clean test
 *
 * Ejecucion filtrada por tag:
 *   ./gradlew clean test -Dcucumber.filter.tags="@autenticacion"
 *
 * Ver el navegador durante la ejecucion:
 *   ./gradlew clean test -Dheadless.mode=false
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "stepdefinitions")
@ConfigurationParameter(
        key = Constants.PLUGIN_PROPERTY_NAME,
        value = "net.serenitybdd.cucumber.core.plugin.SerenityReporterParallel")
@ConfigurationParameter(key = Constants.SNIPPET_TYPE_PROPERTY_NAME, value = "camelcase")
public class RunnerPruebas {
}
