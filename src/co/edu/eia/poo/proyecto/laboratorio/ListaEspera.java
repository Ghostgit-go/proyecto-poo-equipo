package co.edu.eia.poo.proyecto.laboratorio;

public class ListaEspera {
	private Usuario usuario;
	private Equipos equipos;
	private String franja;
	
	public ListaEspera(Usuario usuario, Equipos equipos, String franja) {
		this.usuario = usuario;
		this.equipos = equipos;
		this.franja = franja;
	}
	
	public void agragarListaEspera() {
		System.out.println(usuario.getNombre() + " esta en la lista de espera para "+ equipos.getCodigo());
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public Equipos getEquipos() {
		return equipos;
	}
	
	public String getFranja() {
		return franja;
	}

}
