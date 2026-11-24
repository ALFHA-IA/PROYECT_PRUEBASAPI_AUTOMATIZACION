package CasoPractico.StepDefinitions;

import CasoPractico.Questions.CampoRespuesta;
import CasoPractico.Questions.CodigoRespuesta;
import CasoPractico.Tasks.MetodoPatch;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.CoreMatchers.equalTo;

public class DefinicionesPatch {

    @When("el actor modifica el nombre a {string} y apellido a {string}")
    public void elActorModificaElNombreAYApellidoA(String nombre, String apellido) {
        theActorInTheSpotlight().attemptsTo(
                MetodoPatch.actualizarNombres(nombre, apellido)
        );
    }

    @Then("el estado de la actualizacion HTTP debe ser {int}")
    public void elEstadoDeLaActualizacionHTTPDebeSer(int codigoRespuesta) {
        theActorInTheSpotlight().should(
                seeThat("El codigo de respuesta ", CodigoRespuesta.obtenido(), equalTo(codigoRespuesta))
        );
    }

    @And("el campo {string} actualizado debe ser {string}")
    public void elCampoActualizadoDebeSer(String campo, String valorEsperado) {
        theActorInTheSpotlight().should(
                seeThat("El campo " + campo, CampoRespuesta.conRuta(campo), equalTo(valorEsperado))
        );
    }
}