package CasoPractico.Tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class MetodoDelete implements Task {

    public static Performable eliminarReserva() {
        return instrumented(MetodoDelete.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String idReserva = actor.recall("idReserva");
        String token = actor.recall("token");

        actor.attemptsTo(
                Delete.from("/booking/" + idReserva).with(req -> req
                        .contentType(ContentType.JSON)
                        .header("Cookie", "token=" + token)
                        .log().all()
                )
        );

        SerenityRest.lastResponse().body().prettyPrint();
    }
}