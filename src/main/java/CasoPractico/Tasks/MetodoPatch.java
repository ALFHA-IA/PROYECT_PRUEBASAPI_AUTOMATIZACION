package CasoPractico.Tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Patch;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class MetodoPatch implements Task {

    private final String nuevoNombre;
    private final String nuevoApellido;

    public MetodoPatch(String nuevoNombre, String nuevoApellido) {
        this.nuevoNombre = nuevoNombre;
        this.nuevoApellido = nuevoApellido;
    }

    public static Performable actualizarNombres(String nuevoNombre, String nuevoApellido) {
        return instrumented(MetodoPatch.class, nuevoNombre, nuevoApellido);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String idReserva = actor.recall("idReserva");
        String token = actor.recall("token");

        actor.attemptsTo(
                Patch.to("/booking/" + idReserva).with(req -> req
                        .contentType(ContentType.JSON)
                        .header("Accept", "application/json")
                        .header("Cookie", "token=" + token)
                        .body("""
                              {
                                  "firstname": "%s",
                                  "lastname": "%s"
                              }""".formatted(nuevoNombre, nuevoApellido))
                        .log().all()
                )
        );

        SerenityRest.lastResponse().body().prettyPrint();
    }
}