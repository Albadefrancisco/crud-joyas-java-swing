# Gestión de joyería – CRUD en Java Swing

Aplicación de escritorio para gestionar un catálogo de joyas: alta, edición, borrado, listado y búsqueda por tipo.

<img width="599" height="398" alt="image" src="https://github.com/user-attachments/assets/f24580a9-34a1-416d-a6e7-647f7fe90584" />

## Funcionalidades
- Alta, edición y borrado de joyas con confirmación al borrar
- Listado en tabla y búsqueda por tipo
- Validación de campos y control de duplicados

## Tecnologías
Java | Swing | JDBC | MySQL | HikariCP | Maven | Patrón DAO

## Estructura
- `modelo`: clase `Joya`
- `dao` / `impl`: acceso a datos (interfaz e implementación JDBC)
- `bd`: conexión a la base de datos con pool HikariCP
- `vistacontrolador`: interfaz gráfica
- `controlador`: clase `Principal` 

## Cómo ejecutarlo
Requisitos: Java 17 o superior, MySQL y Maven.

1. Ejecuta `joya.sql` en MySQL con un usuario administrador (por ejemplo `root`). Crea la base de datos `programacion`, el usuario `programacion` (contraseña `programacion`), la tabla `joya` y datos de ejemplo.
2. Compila: `mvn package`
3. Ejecuta: `java -jar target/ejemploDAO-1.0-SNAPSHOT-jar-with-dependencies.jar`

La conexión está definida en `src/main/java/bd/DataBase.java`.