package CasoPractico.Tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class MetodoAuth implements Task {

    private final String usuario;
    private final String clave;

    public MetodoAuth(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    public static Performable conCredenciales(String usuario, String clave) {
        return instrumented(MetodoAuth.class, usuario, clave);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to("/auth").with(req -> req
                        .contentType(ContentType.JSON)
                        .body("""
                              {
                                  "username": "%s",
                                  "password": "%s"
                              }""".formatted(usuario, clave))
                        .log().all()
                )
        );

        SerenityRest.lastResponse().body().prettyPrint();

        if (SerenityRest.lastResponse().statusCode() == 200 && SerenityRest.lastResponse().path("token") != null) {
            OnStage.theActorInTheSpotlight().remember("token", SerenityRest.lastResponse().path("token").toString());
        }
    }
}