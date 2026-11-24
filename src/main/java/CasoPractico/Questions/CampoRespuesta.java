package CasoPractico.Questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Question;

public class CampoRespuesta {

    public static Question<String> conRuta(String campo) {
        return actor -> {
            Object valor = SerenityRest.lastResponse().path(campo);
            return valor != null ? valor.toString() : null;
        };
    }
}