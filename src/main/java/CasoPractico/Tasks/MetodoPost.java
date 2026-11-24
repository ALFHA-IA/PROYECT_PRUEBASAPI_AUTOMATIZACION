package CasoPractico.Tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class MetodoPost implements Task {

    private final String nombre;
    private final String apellido;
    private final String precio;
    private final String pagado;
    private final String entrada;
    private final String salida;
    private final String extras;

    public MetodoPost(String nombre, String apellido, String precio, String pagado, String entrada, String salida, String extras) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.precio = precio;
        this.pagado = pagado;
        this.entrada = entrada;
        this.salida = salida;
        this.extras = extras;
    }

    public static Performable crearReserva(String nombre, String apellido, String precio, String pagado, String entrada, String salida, String extras) {
        return instrumented(MetodoPost.class, nombre, apellido, precio, pagado, entrada, salida, extras);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to("/booking").with(req -> req
                        .contentType(ContentType.JSON)
                        .header("Accept", "application/json")
                        .body("""
                              {
                                  "firstname": "%s",
                                  "lastname": "%s",
                                  "totalprice": %s,
                                  "depositpaid": %s,
                                  "bookingdates": {
                                      "checkin": "%s",
                                      "checkout": "%s"
                                  },
                                  "additionalneeds": "%s"
                              }""".formatted(nombre, apellido, precio, pagado, entrada, salida, extras))
                        .log().all()
                )
        );

        SerenityRest.lastResponse().body().prettyPrint();

        if (SerenityRest.lastResponse().statusCode() == 200) {
            OnStage.theActorInTheSpotlight().remember("idReserva", SerenityRest.lastResponse().path("bookingid").toString());
        }
    }
}