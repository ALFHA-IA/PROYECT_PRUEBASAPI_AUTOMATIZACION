package CasoPractico.StepDefinitions;

import CasoPractico.Questions.CodigoRespuesta;
import CasoPractico.Tasks.MetodoGet;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.CoreMatchers.equalTo;

public class DefinicionesGet {

    @When("el actor consulta la reserva con identificador {string}")
    public void elActorConsultaLaReservaConIdentificador(String id) {
        theActorInTheSpotlight().attemptsTo(
                MetodoGet.consultarPorId(id)
        );
    }

    @Then("el estado de la consulta HTTP debe ser {int} por que no existe")
    public void elEstadoDeLaConsultaHTTPDebeSerPorQueNoExiste(int codigoRespuesta) {
        theActorInTheSpotlight().should(
                seeThat("El codigo de respuesta ", CodigoRespuesta.obtenido(), equalTo(codigoRespuesta))
        );
    }
}