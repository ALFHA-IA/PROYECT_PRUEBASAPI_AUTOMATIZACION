@CasoPractico
Feature: Realizar peticiones con los verbos de las APIS
  Como administrador del sistema
  Quiero validar las operaciones de autenticacion y las peticiones
  Para garantizar el buen funcionamiento de las APIS REST

  Background:
    Given que el actor configura la url base del servicio

  @CP01_Positivo
  Scenario: Generar token de acceso correctamente
    When el actor genera un token con usuario "admin" y clave "password123"
    Then el estado de la autenticacion HTTP debe ser 200
    And el campo "token" del cuerpo no debe ser nulo para que todo quede correcto

  @CP02_Positivo
  Scenario Outline: Registrar otra reserva en este caso con mis nombres y mis apellidos
    When el actor registra una reserva con "<nombre>" "<apellido>" "<precio>" "<pagado>" "<entrada>" "<salida>" "<extras>"
    Then el estado del registro HTTP debe ser 200
    And el campo "booking.firstname" de la reserva creada debe ser "<nombre>"

    Examples:
      | nombre    | apellido | precio | pagado | entrada    | salida     | extras    |
      | Alexander | Urquiaga | 250    | true   | 2026-11-21 | 2026-11-25 | Breakfast |

  @CP03_Positivo
  Scenario: Actualizar una reserva que ya existe usando token para asi tener mas seguridad
    Given el actor genera un token con usuario "admin" y clave "password123"
    And el actor registra una reserva con "Yanet" "Urquiaga" "111" "true" "2026-11-21" "2026-11-22" "Lunch"
    When el actor modifica el nombre a "Mili" y apellido a "Gutierrez"
    Then el estado de la actualizacion HTTP debe ser 200
    And el campo "firstname" actualizado debe ser "Mili"

  @CP04_Negativo
  Scenario: Consultar una reserva con un identificador o id que no existe en el sistema
    When el actor consulta la reserva con identificador "99999999"
    Then el estado de la consulta HTTP debe ser 404 por que no existe

  @CP05_Positivo
  Scenario: Eliminar una reserva existente usando token de autorizacion
    Given el actor genera un token con usuario "admin" y clave "password123"
    And el actor registra una reserva con "Yanet" "Urquiaga" "111" "true" "2026-11-21" "2026-11-22" "Lunch"
    When el actor elimina la reserva registrada previamente
    Then el estado de la eliminacion HTTP debe ser 201