# Prueba Técnica QA Frontend — Automatización web con Serenity BDD y Screenplay

Automatización de los flujos funcionales de [Swag Labs / SauceDemo](https://www.saucedemo.com/),
construida con **Java 21 + Gradle + Selenium WebDriver + Serenity BDD**, aplicando
el patrón **Screenplay** y escenarios en **Gherkin (español)**.

---

## Requisitos previos

| Herramienta | Versión | Nota |
|---|---|---|
| JDK | 21 (LTS) | `JAVA_HOME` debe apuntar a la instalación |
| Google Chrome | Reciente | Selenium Manager descarga el driver automáticamente |
| Gradle | — | **No es necesario instalarlo**: el proyecto incluye el wrapper |

No hay que descargar ni versionar `chromedriver`: Selenium Manager resuelve el
driver adecuado para el Chrome instalado.

---

## Ejecución

### Ejecutar toda la suite

```bash
./gradlew clean test
```

En Windows:

```bash
gradlew.bat clean test
```

> Se usa `clean` porque Serenity agrega los resultados de la carpeta de salida;
> sin limpiarla, una ejecución puede mezclarse con la anterior en el reporte.

### Ver el navegador durante la ejecución

Por defecto la suite corre en modo headless, que es más rápido y estable. Para
observar la interacción real:

```bash
./gradlew clean test -Dheadless.mode=false
```

### Ejecutar un subconjunto por tags

```bash
./gradlew clean test -Dcucumber.filter.tags="@autenticacion"
```

Tags disponibles:

| Tag | Qué agrupa |
|---|---|
| `@autenticacion` | Inicio y cierre de sesión |
| `@productos` | Gestión de productos y carrito desde el catálogo |
| `@filtros` | Ordenamiento del catálogo |
| `@carrito` | Carrito y proceso de checkout |
| `@smoke` | Escenarios críticos de cada módulo |
| `@negativo` | Escenarios de error y validaciones |

Combinaciones: `-Dcucumber.filter.tags="@smoke and not @negativo"`

---

## Reportes

El reporte de Serenity se genera automáticamente al finalizar `gradlew test`,
incluso si hay escenarios fallidos.

| Reporte | Ruta |
|---|---|
| **Serenity (HTML)** | `build/reports/serenity/index.html` |

Para imprimir la ruta por consola:

```bash
./gradlew reportPath
```

El reporte muestra el detalle paso a paso de cada escenario en el lenguaje del
negocio —gracias a las anotaciones `@Step` de las tareas de Screenplay— junto
con capturas de pantalla automáticas de los pasos fallidos.

---

## Estructura del proyecto

```
qa-frontend-serenity/
├── build.gradle                 Dependencias, tarea test y salida del reporte
├── settings.gradle
├── gradlew / gradlew.bat        Wrapper de Gradle (8.10.2)
├── README.md
└── src/
    ├── main/java/com/accenture/qa/       Implementación del patrón Screenplay
    │   ├── models/                Credencial, DatosDeCompra, CriterioDeOrden
    │   ├── userinterfaces/        Mapas de elementos por pantalla
    │   ├── tasks/                 Acciones de negocio del actor
    │   ├── questions/             Consultas sobre el estado de la interfaz
    │   ├── interactions/          Interacciones propias (reservado)
    │   └── utils/                 ConfiguracionDelSitio
    └── test/
        ├── java/
        │   ├── runners/           RunnerPruebas
        │   └── stepdefinitions/   Traducción de Gherkin a tareas y preguntas
        └── resources/
            ├── serenity.conf      Navegador, URL base y opciones de reporte
            └── features/
                ├── autenticacion/
                ├── productos/
                ├── filtros/
                └── carrito/
```

### Las tres capas de Screenplay

**Tasks** — lo que el actor *hace*, en lenguaje de negocio: `Autenticarse`,
`AgregarAlCarrito`, `OrdenarProductos`, `DiligenciarDatosDeCompra`,
`FinalizarCompra`, `CerrarSesion`.

**Questions** — lo que el actor *observa*: `MensajeDeError`,
`NombresDeProductos`, `PreciosDeProductos`, `CantidadEnElCarrito`,
`ProductosEnElCarrito`, `TituloDeLaSeccion`, `MensajeDeConfirmacion`.

**User interfaces** — dónde están los elementos. Los `Target` se definen una
sola vez por pantalla y se reutilizan en tareas y preguntas.

Las step definitions no contienen lógica de interacción: sólo traducen cada
paso de Gherkin a una tarea o una pregunta.

---

## Cobertura

Última ejecución: **20 escenarios (25 ejecuciones con los esquemas expandidos), 0 fallos**.

| Módulo | Escenarios | Negativos | Qué cubre |
|---|---|---|---|
| Autenticación | 3 (+4 ejemplos) | 5 | Login válido, logout, usuario bloqueado, credenciales inválidas y campos vacíos |
| Gestión de productos | 5 | 1 | Listado, agregar, agregar múltiples, remover, contador del carrito |
| Filtrado / ordenamiento | 6 | 0 | Orden A-Z, Z-A, precio ascendente y descendente, integridad del catálogo |
| Carrito y checkout | 4 (+3 ejemplos) | 3 | Contenido del carrito, compra completa, resumen y validación del formulario |

Todos los módulos exigidos por el enunciado están cubiertos e incluyen al menos
un escenario negativo, salvo el de ordenamiento, donde el catálogo no expone
entradas inválidas posibles; en su lugar se validan invariantes (que ordenar no
altere la cantidad de productos).

---

## Decisiones de diseño

**"Filtrado" se interpretó como ordenamiento.** SauceDemo no tiene buscador; su
única función de filtrado es el selector de orden. Es lo que cubre el módulo
`@filtros`.

**Los productos se localizan por su nombre visible**, no por el `id` derivado
del título (`add-to-cart-sauce-labs-backpack`). Los escenarios se leen en
lenguaje de negocio y no se rompen si cambia la convención de identificadores.

**Se prefieren los atributos `data-test`** sobre clases CSS o ids. El sitio los
expone justamente como puntos de anclaje para automatización, así que son los
selectores menos sensibles a cambios de maquetación.

**Configuración centralizada.** La URL base, el navegador, los timeouts y las
opciones de reporte viven en `serenity.conf`. Ninguna tarea ni step definition
contiene datos de entorno, y `ConfiguracionDelSitio` resuelve la URL respetando
la sección de ambiente activa.

**Los precios se comparan como `BigDecimal`.** Son valores monetarios y la
verificación del ordenamiento debe ser exacta, sin los errores de redondeo del
punto flotante.

**El ordenamiento se valida contra la lista realmente ordenada**, no contra una
lista fija esperada. Así la prueba sigue siendo válida si el catálogo cambia, y
verifica la propiedad que importa —que esté ordenado— en lugar de un resultado
memorizado.

**Un navegador por escenario** (`restart.browser.for.each = scenario`), de modo
que ningún escenario herede sesión o carrito de otro.

---

## Respuestas a las preguntas del enunciado

### a. ¿Cuáles fueron los principales desafíos al implementar las funcionalidades?

El principal desafío fue ajustar el código a la API real de Serenity 5 en lugar
de a la que uno recuerda de versiones anteriores. Aparecieron cuatro problemas
de compilación —`Text.of().asList()` que ya no existe, `Ensure` que exige
`Question<Collection<T>>` y no `Question<List<T>>`, el engine de Cucumber
ausente del classpath y el paquete equivocado del reporter—. En vez de probar
combinaciones a ciegas, se inspeccionaron los `.jar` con `javap` para leer las
firmas reales antes de cada corrección. Fue notablemente más rápido que iterar
por ensayo y error.

El segundo desafío fue la resolución de la URL base. Inicialmente se usó un
`PageObject` con `@DefaultUrl("#{webdriver.base.url}")`, pero el placeholder
sólo se expande cuando Serenity instancia la clase por reflexión, no al crearla
con `new`. Los 25 escenarios fallaron con `Invalid URL`. Se reemplazó por una
utilidad que lee la configuración de forma explícita, lo que además respeta la
sección de ambiente activa.

El tercero fue de diseño: decidir cómo localizar los productos. Usar los `id`
generados a partir del título habría sido más directo, pero acopla las pruebas
a una convención interna del sitio; localizar por nombre visible mantiene los
escenarios legibles y más estables.

### b. ¿Qué técnicas de pruebas se usaron y qué enfoque se le dio a la automatización?

Se aplicaron técnicas de **caja negra** desde la perspectiva del usuario:

- **Partición de equivalencia:** credenciales válidas frente a inválidas, y
  dentro de las inválidas, clave errada, usuario inexistente y campos vacíos.
- **Pruebas de flujo end-to-end:** el recorrido completo de compra, desde el
  login hasta la confirmación del pedido.
- **Validación de invariantes:** que ordenar el catálogo no altere la cantidad
  de productos, y que la lista mostrada coincida con su versión ordenada.
- **Pruebas negativas:** usuario bloqueado, credenciales incorrectas y campos
  obligatorios del checkout.
- **Verificación de consistencia entre pantallas:** lo agregado en el catálogo
  debe aparecer en el carrito y conservarse en el resumen previo a confirmar.

El enfoque fue **Screenplay** para separar responsabilidades: las tareas
describen intenciones de negocio, las preguntas observan el estado y los mapas
de interfaz concentran los selectores. Esa separación es lo que permite que un
cambio de maquetación se resuelva en un solo archivo.

### c. ¿Cómo validaste la experiencia del usuario durante la ejecución de los flujos automatizados?

En tres niveles.

**Estado visible de la interfaz:** no se verificó únicamente que una acción no
lanzara error, sino que la pantalla reflejara su efecto. Al agregar un producto
se comprueba que el contador aumente *y* que el botón cambie a la opción de
removerlo, que es la retroalimentación que realmente percibe el usuario.

**Consistencia entre pantallas:** los productos agregados en el catálogo se
verifican de nuevo en el carrito y en el resumen del checkout, confirmando que
la información acompaña al usuario a lo largo del flujo.

**Mensajes de error:** en los escenarios negativos se valida el texto concreto
que ve el usuario —"Sorry, this user has been locked out", "First Name is
required"— y no solo que la operación falle. Un error sin mensaje claro es un
problema de experiencia aunque el sistema técnicamente se comporte bien.

Adicionalmente, Serenity captura pantallazos automáticos de los pasos fallidos,
lo que permite revisar visualmente qué veía el usuario en el momento del fallo.

### d. ¿Qué fue lo que más te llamó la atención o te pareció interesante?

> **Nota:** esta respuesta es personal. Lo que sigue es un punto de partida
> basado en el trabajo realizado; conviene ajustarlo a tu propia experiencia
> antes de entregar.

Lo más interesante fue comprobar cuánto mejora la legibilidad al aplicar
Screenplay de forma estricta. Al leer una step definition no se ve un solo
selector: se lee que el actor se autentica, agrega productos y finaliza la
compra. El reporte de Serenity hereda esa misma narrativa, de modo que una
persona sin conocimientos técnicos puede seguir exactamente qué se probó.

También resultó revelador el contraste con la prueba de backend. Allí el valor
estuvo en encontrar defectos del servicio; aquí, en construir una estructura que
resista los cambios de una interfaz, que es lo que suele romper las suites de
UI. Son dos formas distintas de aportar calidad y el ejercicio permitió
practicar ambas.
