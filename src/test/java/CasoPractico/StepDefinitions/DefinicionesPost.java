package CasoPractico.StepDefinitions;

import CasoPractico.Questions.CampoRespuesta;
import CasoPractico.Questions.CodigoRespuesta;
import CasoPractico.Tasks.MetodoPost;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.CoreMatchers.equalTo;

public class DefinicionesPost {

    @When("el actor registra una reserva con {string} {string} {string} {string} {string} {string} {string}")
    public void elActorRegistraUnaReservaCon(String nombre, String apellido, String precio, String pagado, String entrada, String salida, String extras) {
        theActorInTheSpotlight().attemptsTo(
                MetodoPost.crearReserva(nombre, apellido, precio, pagado, entrada, salida, extras)
        );
    }

    @Then("el estado del registro HTTP debe ser {int}")
    public void elEstadoDelRegistroHTTPDebeSer(int codigoRespuesta) {
        theActorInTheSpotlight().should(
                seeThat("El codigo de respuesta ", CodigoRespuesta.obtenido(), equalTo(codigoRespuesta))
        );
    }

    @And("el campo {string} de la reserva creada debe ser {string}")
    public void elCampoDeLaReservaCreadaDebeSer(String campo, String valorEsperado) {
        theActorInTheSpotlight().should(
                seeThat("El campo " + campo, CampoRespuesta.conRuta(campo), equalTo(valorEsperado))
        );
    }
}