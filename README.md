# Automatización API REST - ALEXANDER URQUIAGA GUTIERREZ

Proyecto de automatización de pruebas API REST utilizando **Java 21**, **Serenity BDD**, **Cucumber** y el patrón **Screenplay** sobre la API pública [Restful Booker](https://restful-booker.herokuapp.com).

## Estructura del Proyecto

* `src/main/java/CasoPractico/Tasks`: Peticiones HTTP (`MetodoAuth`, `MetodoPost`, `MetodoPatch`, `MetodoGet`, `MetodoDelete`).
* `src/main/java/CasoPractico/Questions`: Validaciones de respuesta (`CodigoRespuesta`, `CampoRespuesta`).
* `src/test/java/CasoPractico/StepDefinitions`: Definición de pasos Gherkin y configuración del actor.
* `src/test/java/CasoPractico/EjecutorTest.java`: Runner principal de Cucumber con Serenity.
* `src/test/resources/features/mapeo.feature`: Escenarios de prueba BDD.

## Escenarios Automatizados

1. **`@CP01_Positivo`**: Generación de token (`POST /auth` → `200`).
2. **`@CP02_Positivo`**: Registro de reserva (`POST /booking` → `200`).
3. **`@CP03_Positivo`**: Actualización parcial con token (`PATCH /booking/{id}` → `200`).
4. **`@CP04_Negativo`**: Consulta de reserva inexistente (`GET /booking/99999999` → `404`).
5. **`@CP05_Positivo`**: Eliminación de reserva con token (`DELETE /booking/{id}` → `201`).

## Ejecución y Reportes

Ejecutar pruebas y generar el reporte HTML desde la terminal:

```bash
mvn clean test serenity:aggregate
