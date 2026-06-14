# BibliotecaApp

Proyecto final de **Entornos de Desarrollo** basado en una aplicación Java para la gestión básica de libros de una biblioteca.

El objetivo del proyecto es demostrar un flujo de trabajo profesional con **Git**, **Maven**, **JUnit**, **Mockito**, **JaCoCo**, **SonarQube** y **Jenkins**.

## 1. Descripción del proyecto

BibliotecaApp permite gestionar libros dentro de una biblioteca. La lógica principal permite:

- Registrar libros.
- Consultar libros disponibles.
- Prestar libros.
- Devolver libros.
- Obtener un resumen de un libro.

El proyecto está planteado con una arquitectura sencilla por capas para separar el modelo, la lógica de negocio y el acceso a datos.

## 2. Tecnologías utilizadas

| Tecnología | Uso |
|---|---|
| Java 17 | Lenguaje principal |
| Maven | Gestión del proyecto y dependencias |
| JUnit 5 | Tests unitarios |
| Mockito | Simulación del repositorio en tests |
| JaCoCo | Cobertura de código |
| SonarQube | Análisis de calidad del código |
| Jenkins | Pipeline de integración continua |
| Git/GitHub | Control de versiones |

## 3. Estructura del proyecto

```text
PracticaSonarQube/
├── BibliotecaApp/
│   ├── pom.xml
│   └── src/
│       ├── main/java/com/biblioteca/
│       │   ├── model/
│       │   │   ├── Libro.java
│       │   │   └── Usuario.java
│       │   ├── repository/
│       │   │   └── LibroRepository.java
│       │   ├── service/
│       │   │   └── LibroService.java
│       │   └── util/
│       │       └── PasswordUtil.java
│       └── test/java/com/biblioteca/
│           ├── model/
│           ├── service/
│           └── util/
├── docs/
│   ├── informe_tecnico_bibliotecaapp.docx
│   ├── uml/
│   ├── checklist_entrega.md
│   └── release_notes.md
├── Jenkinsfile
├── .gitignore
└── README.md
```

## 4. Arquitectura

El proyecto usa una arquitectura por capas:

- **model**: contiene las entidades `Libro` y `Usuario`.
- **repository**: contiene la interfaz `LibroRepository`, que abstrae el acceso a datos.
- **service**: contiene `LibroService`, donde está la lógica de negocio.
- **util**: contiene utilidades reutilizables como `PasswordUtil`.

Esta separación facilita las pruebas unitarias y mejora el mantenimiento del código.

## 5. Principios de diseño aplicados

- **Single Responsibility Principle**: cada clase tiene una responsabilidad concreta.
- **Dependency Inversion Principle**: `LibroService` depende de la interfaz `LibroRepository`, no de una implementación concreta.
- **Open/Closed Principle**: se pueden añadir nuevas implementaciones de `LibroRepository` sin modificar `LibroService`.

## 6. Ejecución con Maven

Desde la carpeta `BibliotecaApp`:

```bash
mvn clean compile
```

Para ejecutar los tests:

```bash
mvn test
```

Para generar el paquete `.jar` y el reporte de cobertura:

```bash
mvn verify
mvn package
```

El artefacto se genera en:

```text
BibliotecaApp/target/BibliotecaApp-1.0-SNAPSHOT.jar
```

## 7. Testing

El proyecto incluye tests con JUnit 5 y Mockito.

Mockito se utiliza para simular `LibroRepository`, de forma que se puede probar `LibroService` sin depender de una base de datos real.

Ejemplo de mock utilizado:

```java
@Mock
private LibroRepository libroRepository;
```

Casos probados:

- Registro de libros.
- Búsqueda de libros disponibles.
- Préstamo de libros.
- Devolución de libros.
- Resumen de libros.
- Control de errores cuando un ISBN no existe.
- Utilidad de hash de contraseñas.

## 8. Cobertura con JaCoCo

Para generar el informe de cobertura:

```bash
mvn verify
```

El informe se genera en:

```text
BibliotecaApp/target/site/jacoco/index.html
```

En la versión inicial del proyecto se registraron 5 tests correctos, con 0 fallos y 0 errores. Tras la ampliación de tests se mejora la cobertura de las clases principales.

## 9. SonarQube

El análisis de SonarQube se utiliza para comprobar:

- Bugs.
- Vulnerabilidades.
- Code smells.
- Cobertura.
- Duplicación.
- Calidad general del código.

Comando orientativo:

```bash
mvn clean verify sonar:sonar \
  -Dsonar.projectKey=PruebaEntornos \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.token=TU_TOKEN
```

En la práctica original se configuró el proyecto con clave `PruebaEntornos`.

## 10. Jenkins

El repositorio incluye un `Jenkinsfile` con las siguientes etapas:

1. Checkout.
2. Build.
3. Test.
4. Verify.
5. Package.
6. Publish.

El pipeline compila el proyecto, ejecuta los tests, genera el `.jar` y publica los artefactos.

## 11. Documentación

La documentación adicional se encuentra dentro de la carpeta `docs`, tal como pide el enunciado del proyecto final.

Incluye:

- Informe técnico.
- Diagramas UML.
- Checklist de entrega.
- Release notes.

## 12. Autores

Proyecto realizado para la asignatura **Entornos de Desarrollo**.
