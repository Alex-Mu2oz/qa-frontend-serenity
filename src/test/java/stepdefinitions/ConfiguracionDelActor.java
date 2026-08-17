package stepdefinitions;

import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

/**
 * Prepara el escenario de Screenplay antes de cada prueba.
 *
 * OnlineCast entrega a cada actor su propio navegador, lo que mantiene los
 * escenarios aislados entre si y habilita la ejecucion en paralelo.
 */
public class ConfiguracionDelActor {

    @Before
    public void prepararEscenario() {
        OnStage.setTheStage(new OnlineCast());
    }
}
