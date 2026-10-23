package co.edu.eia.poo.proyecto.laboratorio;
import java.time.LocalDate;

public class Prueba {

	public static void main(String[] args) {

		// se crean los tipos de usuarios
		System.out.println("Registrar usuarios");
		Estudiante estudiante = new Estudiante("Natalia Diaz", "U001", "natalia@eia.edu.co", "Ing. de Sistemas", "5");
		Profesor profesor = new Profesor("Carlos Ruiz", "U002", "cruiz@eia.edu.co", "Mecanica");
		Tecnico tecnico = new Tecnico("Laura Gomez", "U003", "lgomez@eia.edu.co", "Electronica");
		AuxiliarLaboratorio auxiliar = new AuxiliarLaboratorio("Pedro Diaz", "U004", "pdiaz@eia.edu.co", "mañana");

		// se llaman los metodos para mostrar en pantalla
		System.out.println(estudiante);
		System.out.println(profesor);
		System.out.println(tecnico);
		System.out.println(auxiliar);

		// se crea el laboratorio y se ve si esta abierto
		System.out.println("\nRegistrar laboratorio");
		Laboratorios labFabricacion = new Laboratorios("LAB01", "Laboratorio de Fabricacion", "Bloque 5, piso 2", "07:00-19:00", true);
		System.out.println("Laboratorio abierto? " + labFabricacion.estaAbierto(10));
		
		// se crean los tipos de equipos
		System.out.println("\nRegistrar equipos (con subtipos)");
		Impresora3D impresora = new Impresora3D("EQ001", "Impresora Ender 3", 1, "disponible", labFabricacion, "PLA");
		CortadoraLaser cortadora = new CortadoraLaser("EQ002", "Cortadora K40", 3, "disponible", labFabricacion, 40.0);
		Multimetro multimetro = new Multimetro("EQ003", "Multimetro Fluke 117", 1, "disponible", labFabricacion, "0-600V", 0.5);
		
		// se llaman los metodos para mostrar en terminal
		impresora.mostrarEstado();
		cortadora.mostrarEstado();
		multimetro.mostrarEstado();

		// esto lo creamos pero no lo hemos usado, solo lo mostramos en pantalla
		System.out.println("Potencia laser: " + cortadora.getpotenciaLaser());

		// creamos la certificacion y formacion, para luego exigir la certificacion
		System.out.println("\nCapacitacion y certificacion");
		Capacitacion capacitacionLaser = new Capacitacion("Uso seguro de cortadora laser", 4, "CAP-001", "Cortadora Laser");
		Certificacion certificacionLaser = new Certificacion("CER-001", LocalDate.of(2025, 1, 10), 360, profesor, "CortadoraLaser");
		
		estudiante.inscribirCapacitacion(capacitacionLaser);
		capacitacionLaser.registrarAsistencia(estudiante);
		System.out.println("Certificacion vigente? "+ certificacionLaser.validarCertificacionVigente(LocalDate.now()));
		auxiliar.registrarCapacitacionesCertificaciones(capacitacionLaser, certificacionLaser);

		// se crea una reserva, luego aqu[i ira si se cruzan las horas
		System.out.println("\nCrear reserva");
		Reserva reserva = new Reserva(estudiante, cortadora, "2024-05-10", 10, 12, "Proyecto final de curso", "pendiente");
		System.out.println("Disponible para reservar? " + reserva.disponibilidad(labFabricacion, "2024-05-10 10:00-12:00"));
		System.out.println("Proposito registrado: " + reserva.getProposito());
		auxiliar.registrarReservas(reserva);
		auxiliar.validarReserva(reserva);

		// se valida la disponibilidad y se crea una sesion de uso
		System.out.println("\nValidar disponibilidad y abrir sesion de uso");
		tecnico.validarDisponibilidadEquipo(cortadora);
		SesionUso sesion = new SesionUso(reserva);
		sesion.abrirSesion(reserva, 10);
		cortadora.cambiarEstado("en_uso");
		cortadora.mostrarEstado();

		// cerramos sesion de uso y mostramos lo que nos piden
		System.out.println("\nCerrar sesion de uso");
		sesion.cerrarSesion(12, "1 lamina de acrilico", "Sin novedades", "Ninguno");
		cortadora.cambiarEstado("disponible");
		System.out.println("Consumibles registrados: " + sesion.getConsumibles());
		System.out.println("Hora de inicio: " + sesion.getHoraIncio());
		System.out.println("Hora de cierre: " + sesion.getHoraFin());

		//creamos la lista de espera cuando el equipo esta ocupado
		System.out.println("\nLista de espera (equipo ocupado)");
		ListaEspera espera = new ListaEspera(profesor, cortadora, "2024-05-10 14:00-16:00");
		espera.agragarListaEspera();

		// se crea un ticket de mantenimiento, y el equipo no debe tener resrvas, y el tecnico agrega al historial de cambios
		System.out.println("\nTicket de mantenimiento");
		TicketMantenimiento ticket = new TicketMantenimiento(2, "Ruido anormal en el motor", cortadora, tecnico, "abierto", "Ticket creado");
		System.out.println("Historial inicial: " + ticket.getHistorialCambios());
		cortadora.cambiarEstado("mantenimiento");
		tecnico.cerrarTicket(ticket, cortadora);
		cortadora.cambiarEstado("disponible");
		System.out.println("Historial final: " + ticket.getHistorialCambios());

		// se crea una notificacion y se le avisa al estudiante que el equipo volvio a estar disponible
		System.out.println("\n10. Notificacion");
		Notificacion notificacion = new Notificacion(
				"Tu equipo reservado (" + cortadora.getNombre() + ") volvio a estar disponible.",
				estudiante
		);
		notificacion.enviarNotificacion();
	}

}