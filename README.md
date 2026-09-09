## Cómo importar el proyecto

1. Abre Eclipse.
2. Ve a `File > Import > Git > Projects from Git > Clone URI`.
3. Pega la URL del repositorio (HTTPS o SSH) y sigue el asistente.
4. Cuando termine el clonado, selecciona **"Import as general project"** o **"Import existing Eclipse projects"** según corresponda.
5. Finaliza el asistente; el proyecto quedará visible en el **Package Explorer**.

## Cómo ejecutar el proyecto

1. Ubica la clase con el método `main` (por ejemplo `[NombreClasePrincipal].java`).
2. Click derecho sobre el archivo → `Run As > Java Application`.
3. La ejecución mostrará el menú/resultados por consola.

## Cómo probar el proyecto

1. Las pruebas se encuentran en la carpeta `src/test` (o `[ruta_de_pruebas]`).
2. Click derecho sobre la clase de prueba → `Run As > JUnit Test`.
3. Revisa la consola de resultados de JUnit en Eclipse (verde = pasó, rojo = falló).

## Capacidad N usada en arreglos y datos de prueba

- El proyecto utiliza arreglos de tamaño fijo definidos por la constante `N = [valor]`, ubicada en `[NombreClase].java`.
- Esta capacidad determina el número máximo de elementos que se pueden almacenar (ej. estudiantes, productos, vehículos, según el dominio del proyecto).
- **Datos de prueba:** se precargan `[cantidad]` registros de ejemplo al iniciar el programa, ubicados en `[NombreClase / método]`, para validar el correcto funcionamiento de las operaciones (agregar, buscar, eliminar, listar).


