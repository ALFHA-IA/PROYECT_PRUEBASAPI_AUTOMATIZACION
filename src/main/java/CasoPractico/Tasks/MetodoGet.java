package CasoPractico.Tasks;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class MetodoGet implements Task {

    private final String idReserva;

    public MetodoGet(String idReserva) {
        this.idReserva = idReserva;
    }

    public static Performable consultarPorId(String idReserva) {
        return instrumented(MetodoGet.class, idReserva);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/booking/" + idReserva).with(req -> req
                        .header("Accept", "application/json")
                        .log().all()
                )
        );

        SerenityRest.lastResponse().body().prettyPrint();
    }
}