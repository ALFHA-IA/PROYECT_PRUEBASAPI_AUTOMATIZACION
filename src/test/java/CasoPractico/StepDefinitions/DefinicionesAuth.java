package CasoPractico.StepDefinitions;

import CasoPractico.Questions.CampoRespuesta;
import CasoPractico.Questions.CodigoRespuesta;
import CasoPractico.Tasks.MetodoAuth;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class DefinicionesAuth {

    @When("el actor genera un token con usuario {string} y clave {string}")
    public void elActorGeneraUnTokenConUsuarioYClave(String usuario, String clave) {
        theActorInTheSpotlight().attemptsTo(
                MetodoAuth.conCredenciales(usuario, clave)
        );
    }

    @Then("el estado de la autenticacion HTTP debe ser {int}")
    public void elEstadoDeLaAutenticacionHTTPDebeSer(int codigoRespuesta) {
        theActorInTheSpotlight().should(
                seeThat("El codigo de respuesta ", CodigoRespuesta.obtenido(), equalTo(codigoRespuesta))
        );
    }

    @And("el campo {string} del cuerpo no debe ser nulo para que todo quede correcto")
    public void elCampoDelCuerpoNoDebeSerNuloParaQueTodoQuedeCorrecto(String campo) {
        theActorInTheSpotlight().should(
                seeThat("El campo " + campo, CampoRespuesta.conRuta(campo), notNullValue())
        );
    }
}