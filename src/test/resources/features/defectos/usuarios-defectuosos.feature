# language: es
@defectos
Característica: Defectos detectados con los usuarios de prueba del sitio

  Swag Labs expone varios usuarios además del estándar. Al probar con
  problem_user aparecen fallas reales que no se manifiestan con standard_user.

  Cada escenario afirma el comportamiento REAL observado, no el correcto, de
  modo que la suite quede verde y sirva como señal de regresión: si alguno
  empieza a fallar, será porque el defecto se corrigió. El comportamiento
  esperado queda documentado en los comentarios y en el README.

  @defecto @negativo
  Escenario: Con problem_user todas las imágenes del catálogo son la misma
    # HALLAZGO: las 6 tarjetas muestran el mismo archivo (sl-404.jpg, un
    # marcador de imagen no encontrada). Con standard_user hay 6 imágenes
    # distintas. Lo esperado es una imagen propia por producto.
    Dado que Wilson ha iniciado sesion con el usuario "problem_user"
    Entonces el catalogo deberia mostrar 6 productos
    Y el catalogo deberia mostrar 1 imagenes distintas

  @defecto @negativo
  Escenario: Con problem_user la mitad de los productos no se puede agregar al carrito
    # HALLAZGO: al pulsar "Add to cart" en los 6 productos, sólo 3 quedan en el
    # carrito; los otros 3 botones no reaccionan. Con standard_user quedan 6.
    Dado que Wilson ha iniciado sesion con el usuario "problem_user"
    Cuando agrega al carrito todos los productos del catalogo
    Entonces el contador del carrito deberia mostrar 3

  @defecto @negativo @filtros
  Escenario: Con problem_user el ordenamiento del catálogo no tiene efecto
    # HALLAZGO: los cuatro criterios (A-Z, Z-A, precio ascendente y descendente)
    # devuelven siempre el mismo orden. El selector cambia de valor pero la
    # lista no se reordena, por lo que los precios quedan desordenados.
    Dado que Wilson ha iniciado sesion con el usuario "problem_user"
    Cuando ordena el catalogo por "precio ascendente"
    Entonces los precios no deberian quedar ordenados de menor a mayor

  @defecto @negativo
  Escenario: Con problem_user el campo Apellido no recibe el texto escrito
    # HALLAZGO: lo que se escribe en "Last Name" se desvía al campo "First
    # Name", carácter a carácter y sobrescribiéndolo. Tras escribir "Wilson"
    # en Nombre y "Munoz" en Apellido, el formulario queda con Apellido vacío
    # y el Nombre corrompido con la última letra de "Munoz".
    Dado que Wilson ha iniciado sesion con el usuario "problem_user"
    Cuando agrega al carrito el producto "Sauce Labs Backpack"
    Y abre el carrito de compras
    Y escribe "Wilson" en el nombre y "Munoz" en el apellido del checkout
    Entonces el campo apellido deberia quedar vacio
    Y el campo nombre no deberia contener "Wilson"

  @contraste
  Escenario: Con standard_user el catálogo sí muestra una imagen por producto
    # Contraste que demuestra que los defectos anteriores dependen del usuario
    # y no son una limitación del sitio ni de la automatización.
    Dado que Wilson ha iniciado sesion con el usuario "standard_user"
    Entonces el catalogo deberia mostrar 6 productos
    Y el catalogo deberia mostrar 6 imagenes distintas
