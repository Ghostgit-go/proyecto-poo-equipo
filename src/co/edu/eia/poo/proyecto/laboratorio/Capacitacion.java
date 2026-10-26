package co.edu.eia.poo.proyecto.laboratorio;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class Capacitacion  {
	private String id;
	private String tema;
	private int horasDuracion;
	private String tipoEquipo;
	private List<Usuario> asistentes;
	
	
	public Capacitacion(String id, String tema, int horasDuracion,  String tipoEquipo) {
		this.id=id;
		this.tema = tema;
		this.horasDuracion=horasDuracion;
		this.tipoEquipo=tipoEquipo;
		this.asistentes = new ArrayList<>();
	}
	
	 //anota a un usuario como asistente si ya estaba anotado no lo repite
    public void registrarAsistencia(Usuario u) {
        for (Usuario a : asistentes) {
            if (a.getId().equals(u.getId())) {
                System.out.println(u.getNombre() + "ya estaba registradoen " + tema);
                return;
            }
        }
        asistentes.add(u);
        System.out.println(u.getNombre() + " asistio a lacapacitacion: " + tema);
    }
    
 //genera una certificacion vigente 365 dias desde la fecha dada ara un usuario
    // que asistio a la capacitacion, si no asisto no se puede certificar
    public Certificacion generarCertificacion(Usuario u, LocalDate fecha, String tipoEquipo) {
        boolean asistio = false;
        for (Usuario a : asistentes) {
            if (a.getId().equals(u.getId())) {
                asistio = true;
            }
        }
        if (!asistio) {
            throw new IllegalStateException(u.getNombre() + " no asistio a la capacitacion " + tema);
        }
        return new Certificacion("CERT-" + id + "-" + u.getId(), fecha, 365, u, tipoEquipo);
    }
	
	public String getTema() {
		return tema;
	}
	
	public String getId() {
		return id;
	}
	
	public int getHorasDuracion() {
		return horasDuracion;
	}
	
	public String getTipoEquipo() {
		return tipoEquipo;
	}

}
