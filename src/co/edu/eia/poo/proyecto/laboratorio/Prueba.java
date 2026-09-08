package co.edu.eia.poo.proyecto.laboratorio;

public class Prueba {

	public static void main(String[] args) {
		//aqui creamos los objetos tipo usuario
		System.out.println("Registrar usuarios");
		Estudiante estudiante = new Estudiante("Natalia", "U1", "Natalia@eia.edu.co", "estudiante", "Ing. de Sistemas", "5");
		Profesor profesor = new Profesor("Carlos", "U2", "carlos@eia.edu.co", "profesor", "Mecanica");
		Tecnico tecnico = new Tecnico("U3", "Laura Gomez", "lgomez@eia.edu.co", "tecnico", "Electronica"); //aqui puse el orden mal sin querer, por el momento estará así
		AuxiliarLaboratorio auxiliar = new AuxiliarLaboratorio("Pedro Diaz", "U4", "pdiaz@eia.edu.co", "auxiliar", "manana");
		
		// llamamos el metodo mostrarUsuario con cada objeto para mostrarlo en la terminal
		estudiante.mostrarUsuario();
		profesor.mostrarUsuario();
		tecnico.mostrarUsuario();
		auxiliar.mostrarUsuario();
		
		//creamos el objeto de laboratorio y decir si está abierto o cerrado
		System.out.println("\nRegistrar laboratorio");
		Laboratorios labFabricacion = new Laboratorios("LAB01", "Laboratorio de Fabricacion", "Bloque 5, piso 2", "07:00-19:00", true);
		System.out.println("Laboratorio abierto? " + labFabricacion.estaAbierto(10));

		// creamos los objetos de los equipos
		System.out.println("\nRegistrar equipos (con subtipos)");
		Impresora3D impresora = new Impresora3D("EQ001", "Impresora Ender 3", 1, "disponible", labFabricacion, "PLA");
		CortadoraLaser cortadora = new CortadoraLaser("EQ002", "Cortadora K40", 3, "disponible", labFabricacion, 40.0);
		Multimetro multimetro = new Multimetro("EQ003", "Multimetro Fluke 117", 1, "disponible", labFabricacion, "0-600V", 0.5);

		// llamamos el metodo mostrarEstado de la clase Equipos para mostrar los equipos
		impresora.mostrarEstado();
		cortadora.mostrarEstado();
		multimetro.mostrarEstado();
		
		// creamos los objetos de capacitacion y certificacion, por ekemplo, una formacion con la cortadora Laser
		System.out.println("\nCapacitacion y certificacion");
		Capacitacion capacitacionLaser = new Capacitacion("2024-01-10", "2025-01-10", estudiante, cortadora, "Uso seguro de cortadora laser", 4);
		Certificacion certificacionLaser = new Certificacion("2024-01-10", "2025-01-10", estudiante, cortadora, "CortadoraLaser");

		// llamamos inscribimos a el estudiante, con el metodo y a que capacitacion
		estudiante.inscribirCapacitacion(capacitacionLaser);
		capacitacionLaser.registrarAsistencia(estudiante); // registramos su asistencia 
		System.out.println("Certificacion vigente? " + certificacionLaser.estaVigente());
		auxiliar.registrarCapacitacionesCertificaciones(capacitacionLaser, certificacionLaser); //el auxiliar registra

		// creamos una reserva
		System.out.println("\nCrear reserva");
		Reserva reserva = new Reserva(estudiante, cortadora, "2024-05-10", 10, 12, "Proyecto final de curso", "pendiente");
		System.out.println("Disponible para reservar? " + reserva.disponibilidad(labFabricacion, "2024-05-10 10:00-12:00"));
		System.out.println("Proposito registrado: " + reserva.getProposito());
		auxiliar.registrarReservas(reserva);
		auxiliar.validarReserva(reserva);

		// llamamos los metodos correspondientes y creamos un nuevo objeto para SesionUso
		System.out.println("\nValidar disponibilidad y abrir sesion de uso");
		tecnico.validarDisponibilidadEquipo(cortadora);
		SesionUso sesion = new SesionUso(reserva);
		sesion.abrirSesion(reserva, 10);
		cortadora.cambiarEstado("en_uso");
		cortadora.mostrarEstado();

		System.out.println("\nCerrar sesion de uso");
		sesion.cerrarSesion(12, "1 lamina de acrilico", "Sin novedades", "Ninguno");
		cortadora.cambiarEstado("disponible");
		System.out.println("Consumibles registrados: " + sesion.getConsumibles());
		System.out.println("Hora de inicio: " + sesion.getHoraIncio());
		System.out.println("Hora de cierre: " + sesion.getHoraFin());

		System.out.println("\nLista de espera (equipo ocupado)");
		ListaEspera espera = new ListaEspera(profesor, cortadora, "2024-05-10 14:00-16:00");
		espera.agragarListaEspera();

		System.out.println("\nTicket de mantenimiento");
		TicketMantenimiento ticket = new TicketMantenimiento(2, "Ruido anormal en el motor", cortadora, tecnico, "abierto", "Ticket creado");
		System.out.println("Historial inicial: " + ticket.getHistorialCambios());
		cortadora.cambiarEstado("mantenimiento");
		tecnico.cerrarTicket(ticket, cortadora);
		cortadora.cambiarEstado("disponible");
		System.out.println("Historial final: " + ticket.getHistorialCambios());

		System.out.println("\nNotificacion");
		Notificacion notificacion = new Notificacion(
				"Tu equipo reservado (" + cortadora.getNombre() + ") volvio a estar disponible.",
				estudiante
		);
		notificacion.enviarNotificacion();
	}

}