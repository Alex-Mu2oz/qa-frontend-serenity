# language: es
@autenticacion
Característica: Autenticación en Swag Labs

  Como usuario de la tienda
  quiero iniciar y cerrar sesión
  para acceder al catálogo de productos de forma segura

  Antecedentes:
    Dado que Wilson se encuentra en la pagina de inicio de sesion

  @smoke
  Escenario: Ingresar con credenciales válidas da acceso al catálogo
    Cuando ingresa con el usuario estandar
    Entonces deberia ver el catalogo de productos

  Escenario: Cerrar sesión devuelve al usuario a la pantalla de acceso
    Cuando ingresa con el usuario estandar
    Y cierra su sesion
    Entonces deberia regresar a la pagina de inicio de sesion

  @negativo
  Escenario: Un usuario bloqueado no puede acceder
    Cuando ingresa con el usuario bloqueado
    Entonces deberia ver el mensaje de error "Sorry, this user has been locked out"
    Y no deberia acceder al catalogo

  @negativo
  Esquema del escenario: Credenciales inválidas son rechazadas (<caso>)
    Cuando ingresa con el usuario "<usuario>" y la clave "<clave>"
    Entonces deberia ver el mensaje de error "<mensaje>"
    Y no deberia acceder al catalogo

    Ejemplos:
      | caso                  | usuario        | clave         | mensaje                                                                   |
      | clave incorrecta      | standard_user  | clave_errada  | Username and password do not match any user in this service               |
      | usuario inexistente   | usuario_falso  | secret_sauce  | Username and password do not match any user in this service               |
      | usuario vacio         |                | secret_sauce  | Username is required                                                      |
      | clave vacia           | standard_user  |               | Password is required                                                      |
