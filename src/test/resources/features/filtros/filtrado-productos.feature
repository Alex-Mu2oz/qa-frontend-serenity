# language: es
@filtros
Característica: Ordenamiento y filtrado del catálogo

  Como comprador
  quiero ordenar el catálogo por nombre o por precio
  para encontrar más rápido lo que busco

  Antecedentes:
    Dado que Wilson ha iniciado sesion con el usuario estandar

  @smoke
  Escenario: Ordenar de la A a la Z
    Cuando ordena el catalogo por "nombre ascendente"
    Entonces los productos deberian quedar ordenados alfabeticamente de forma ascendente
    Y el primer producto listado deberia ser "Sauce Labs Backpack"

  Escenario: Ordenar de la Z a la A
    Cuando ordena el catalogo por "nombre descendente"
    Entonces los productos deberian quedar ordenados alfabeticamente de forma descendente

  Escenario: Ordenar por precio de menor a mayor
    Cuando ordena el catalogo por "precio ascendente"
    Entonces los precios deberian quedar ordenados de menor a mayor

  Escenario: Ordenar por precio de mayor a menor
    Cuando ordena el catalogo por "precio descendente"
    Entonces los precios deberian quedar ordenados de mayor a menor

  Escenario: El ordenamiento no altera la cantidad de productos del catálogo
    Cuando ordena el catalogo por "precio descendente"
    Entonces el catalogo deberia mostrar 6 productos

  Escenario: Cambiar de criterio reordena el catálogo sin perder productos
    Cuando ordena el catalogo por "nombre descendente"
    Y ordena el catalogo por "nombre ascendente"
    Entonces los productos deberian quedar ordenados alfabeticamente de forma ascendente
    Y el catalogo deberia mostrar 6 productos
