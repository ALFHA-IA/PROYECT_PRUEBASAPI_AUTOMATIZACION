package CasoPractico.StepDefinitions;

import io.cucumber.java.ParameterType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;

public class DefinicionParametros {

    @ParameterType(".*")
    public Actor actor(String nombreActor) {
        return OnStage.theActorCalled(nombreActor);
    }
}