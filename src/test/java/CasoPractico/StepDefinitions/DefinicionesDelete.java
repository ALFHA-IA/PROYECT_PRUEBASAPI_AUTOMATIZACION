package CasoPractico.StepDefinitions;

import CasoPractico.Questions.CodigoRespuesta;
import CasoPractico.Tasks.MetodoDelete;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.CoreMatchers.equalTo;

public class DefinicionesDelete {

    @When("el actor elimina la reserva registrada previamente")
    public void elActorEliminaLaReservaRegistradaPreviamente() {
        theActorInTheSpotlight().attemptsTo(
                MetodoDelete.eliminarReserva()
        );
    }

    @Then("el estado de la eliminacion HTTP debe ser {int}")
    public void elEstadoDeLaEliminacionHTTPDebeSer(int codigoRespuesta) {
        theActorInTheSpotlight().should(
                seeThat("El codigo de respuesta ", CodigoRespuesta.obtenido(), equalTo(codigoRespuesta))
        );
    }
}