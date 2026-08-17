# language: es
@productos
Característica: Gestión de productos del catálogo

  Como comprador autenticado
  quiero agregar y quitar productos del carrito
  para armar mi pedido antes de comprar

  Antecedentes:
    Dado que Wilson ha iniciado sesion con el usuario estandar

  @smoke
  Escenario: El catálogo muestra todos los productos con su información
    Entonces el catalogo deberia mostrar 6 productos
    Y cada producto deberia mostrar nombre y precio

  @smoke
  Escenario: Agregar un producto actualiza el contador del carrito
    Cuando agrega al carrito el producto "Sauce Labs Backpack"
    Entonces el contador del carrito deberia mostrar 1
    Y el producto "Sauce Labs Backpack" deberia ofrecer la opcion de removerlo

  Escenario: Agregar varios productos acumula el contador
    Cuando agrega al carrito los productos:
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
      | Sauce Labs Onesie     |
    Entonces el contador del carrito deberia mostrar 3

  Escenario: Remover un producto descuenta el contador
    Cuando agrega al carrito los productos:
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
    Y remueve del catalogo el producto "Sauce Labs Backpack"
    Entonces el contador del carrito deberia mostrar 1
    Y el producto "Sauce Labs Backpack" deberia ofrecer la opcion de agregarlo

  @negativo
  Escenario: Remover el último producto deja el carrito sin contador
    Cuando agrega al carrito el producto "Sauce Labs Backpack"
    Y remueve del catalogo el producto "Sauce Labs Backpack"
    Entonces el carrito deberia quedar sin contador visible
