Proyecto_Laboratorio

University project for object-oriented programming, laboratory and equipment management.

Cómo importar el proyecto
Abre Eclipse.
Ve a File > Import > Git > Projects from Git > Clone URI.
Pega la URL del repositorio (HTTPS o SSH) y sigue el asistente.
Cuando termine el clonado, selecciona "Import existing Eclipse projects".
Finaliza el asistente; el proyecto Proyecto_Laboratorio quedará visible en el Package Explorer.
El proyecto usa Java SE 21 como JRE del classpath; verifica que tengas ese JDK instalado (o uno compatible) en Eclipse.
Cómo ejecutar el proyecto
Ubica la clase Prueba.java en el paquete co.edu.eia.poo.proyecto.laboratorio — contiene el método main.
Click derecho sobre el archivo → Run As > Java Application.
La consola mostrará, en orden, el registro de usuarios, laboratorios, equipos, capacitaciones/certificaciones, una reserva, la apertura y cierre de una sesión de uso, una lista de espera y un ticket de mantenimiento con su historial de cambios.
Cómo probar el proyecto

Actualmente el proyecto no cuenta con pruebas unitarias (JUnit); la validación se hace ejecutando Prueba.java, que actúa como una prueba de integración manual: crea instancias de cada clase del modelo (Estudiante, Profesor, Tecnico, AuxiliarLaboratorio, Laboratorios, Impresora3D, CortadoraLaser, Multimetro, Capacitacion, Certificacion, Reserva, SesionUso, ListaEspera, TicketMantenimiento, Notificacion) y llama sus métodos principales, imprimiendo los resultados por consola para verificar que el flujo completo (registro → reserva → uso → mantenimiento → notificación) funcione correctamente.

Para probar manualmente:

Ejecuta Prueba.java como se indicó arriba.
Revisa la salida en la consola de Eclipse y compárala con el comportamiento esperado de cada método (por ejemplo, estaAbierto(), disponibilidad(), estaVigente()).
Si modificas alguna clase, puedes agregar nuevos objetos/llamados en Prueba.java para validar el cambio, o crear pruebas JUnit en una carpeta test si se requiere formalizar la validación.
Capacidad N usada en arreglos y datos de prueba

Este proyecto no utiliza arreglos ni una capacidad fija N: no hay array[] ni colecciones (ArrayList, List, etc.) para almacenar usuarios, equipos o reservas. Cada objeto se crea de forma individual (una sola instancia por entidad) directamente en el método main() de Prueba.java.

Los datos de prueba son los valores hardcodeados en Prueba.java, por ejemplo:

Entidad	Datos de prueba
Estudiante	Natalia (U1, Ing. de Sistemas, semestre 5)
Profesor	Carlos (U2, Mecánica)
Técnico	Laura Gómez (U3, Electrónica)
Auxiliar de laboratorio	Pedro Díaz (U4, turno mañana)
Laboratorio	LAB01 – Laboratorio de Fabricación (Bloque 5, piso 2)
Equipos	Impresora 3D Ender 3 (EQ001), Cortadora Láser K40 (EQ002), Multímetro Fluke 117 (EQ003)
Capacitación / Certificación	Uso seguro de cortadora láser (estudiante Natalia)
Reserva	Cortadora láser, 2024-05-10, 10:00–12:00
Ticket de mantenimiento	Ruido anormal en el motor (cortadora láser)

Si el proyecto evoluciona a manejar múltiples usuarios/equipos con arreglos o listas de tamaño limitado, actualiza esta sección indicando el valor de N, dónde se define y cuántos registros de prueba se precargan.
