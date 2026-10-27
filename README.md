## Cómo ejecutar el proyecto

**Requisitos:** JDK 21, Eclipse con soporte Maven, PostgreSQL (con pgAdmin).

1. Clonar el repositorio, importarlo en Eclipse (File > Import > Maven > Existing Maven Projects) y cambiar a la rama `front`.
2. Instalar PostgreSQL y crear en pgAdmin una base de datos llamada `laboratorios`.
3. Abrir la Query Tool sobre esa base, cargar el archivo `bd/esquema.sql` y ejecutarlo. Si ya se había ejecutado antes, borrar la base y crearla de nuevo.
4. Copiar `config.properties.example` a un archivo `config.properties` en la raíz del proyecto y poner allí su puerto y su contraseña de PostgreSQL. Este archivo no se sube a GitHub.
5. Clic derecho en el proyecto > Maven > Update Project.
6. Ejecutar la clase `co.edu.eia.poo.proyecto.vista.Main` con Run As > Java Application.
