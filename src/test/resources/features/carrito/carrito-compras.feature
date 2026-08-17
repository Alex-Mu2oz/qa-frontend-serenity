# language: es
@carrito
Característica: Carrito de compras y proceso de checkout

  Como comprador autenticado
  quiero revisar mi carrito y completar la compra
  para recibir la confirmación de mi pedido

  Antecedentes:
    Dado que Wilson ha iniciado sesion con el usuario estandar

  @smoke
  Escenario: El carrito refleja los productos agregados desde el catálogo
    Cuando agrega al carrito los productos:
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
    Y abre el carrito de compras
    Entonces deberia ver la seccion "Your Cart"
    Y el carrito deberia contener los productos:
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |

  @smoke
  Escenario: Completar la compra muestra la confirmación del pedido
    Cuando agrega al carrito el producto "Sauce Labs Backpack"
    Y abre el carrito de compras
    Y diligencia los datos de compra validos
    Y finaliza la compra
    Entonces deberia ver la confirmacion "Thank you for your order!"

  Escenario: El resumen previo a confirmar conserva los productos seleccionados
    Cuando agrega al carrito los productos:
      | Sauce Labs Backpack   |
      | Sauce Labs Onesie     |
    Y abre el carrito de compras
    Y diligencia los datos de compra validos
    Entonces deberia ver la seccion "Checkout: Overview"
    Y el carrito deberia contener 2 productos

  @smoke
  Escenario: El resumen calcula correctamente el subtotal y el total a pagar
    Cuando agrega al carrito los productos:
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
    Y abre el carrito de compras
    Y diligencia los datos de compra validos
    Entonces el subtotal deberia ser la suma de los articulos
    Y el total deberia ser el subtotal mas el impuesto

  Escenario: Comprar un solo producto llega hasta la confirmación
    Cuando agrega al carrito el producto "Sauce Labs Bike Light"
    Y abre el carrito de compras
    Y diligencia los datos de compra validos
    Y finaliza la compra
    Entonces deberia ver la confirmacion "Thank you for your order!"

  @negativo
  Esquema del escenario: El checkout exige los datos obligatorios (<caso>)
    Cuando agrega al carrito el producto "Sauce Labs Backpack"
    Y abre el carrito de compras
    Y diligencia los datos de compra con nombre "<nombre>", apellido "<apellido>" y codigo postal "<codigoPostal>"
    Entonces deberia ver el error del formulario "<mensaje>"

    Ejemplos:
      | caso                    | nombre | apellido | codigoPostal | mensaje                     |
      | sin nombre              |        | Munoz    | 050001       | First Name is required      |
      | sin apellido            | Wilson |          | 050001       | Last Name is required       |
      | sin codigo postal       | Wilson | Munoz    |              | Postal Code is required     |
