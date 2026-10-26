package co.edu.eia.poo.proyecto.laboratorio;

import java.time.LocalDate;
import java.util.Collection;

//registra y consulta usuarios, laboratorios, equipos y certificaciones,
//validando que los identificadores no se repitan
public class GestorRegistro {
    private Repositorio<Usuario> usuarios = new Repositorio<>();
    private Repositorio<Laboratorio> laboratorios = new Repositorio<>();
    private Repositorio<Equipo> equipos = new Repositorio<>();
    private Repositorio<Certificacion> certificaciones = new Repositorio<>();

    //registra un usuario devuelve false y avisa si el id ya existe
    public boolean registrarUsuario(Usuario u) {
        try {
            usuarios.agregar(u);
            return true;
        } catch (IdDuplicadoException e) {
            System.out.println("egistro rechazado " + e.getMessage());
            return false;
        }
    }

    //registra varios usuarios a la vez, todo o nada si uno falla, no se registra ninguno
    public boolean registrarUsuarios(Collection<? extends Usuario> lote) {
        try {
            usuarios.agregarTodos(lote);
            return true;
        } catch (IdDuplicadoException e) {
            System.out.println("lote rechazado " + e.getMessage());
            return false;
        }
    }

    //registra un laboratorio devuelve false y avisa si el codigo ya existe
    public boolean registrarLaboratorio(Laboratorio l) {
        try {
            laboratorios.agregar(l);
            return true;
        } catch (IdDuplicadoException e) {
            System.out.println("registro rechazado: " + e.getMessage());
            return false;
        }
    }

    //registra un equipo de cualquier subtipo devuelve false y avisa si el codigo ya existe
    public boolean registrarEquipo(Equipo e) {
        try {
            equipos.agregar(e);
            return true;
        } catch (IdDuplicadoException ex) {
            System.out.println("registro rechazado " + ex.getMessage());
            return false;
        }
    }

    //registra una certificacion devuelve false y avisa si el id ya existe
    public boolean registrarCertificacion(Certificacion c) {
        try {
            certificaciones.agregar(c);
            return true;
        } catch (IdDuplicadoException e) {
            System.out.println("registro rechazado " + e.getMessage());
            return false;
        }
    }

    //busca un usuario por id null si no existe
    public Usuario buscarUsuario(String id) {
        return usuarios.buscar(id);
    }

    // exige que el usuario tenga una certificacion vigente en esa fecha para el tipo del equipo
    // si no la tiene, o la que tiene está vencida, lanza CertificacionVencidaException
    // asi la reserva se rechaza
    public void exigirCertificacion(Usuario u, Equipo e, LocalDate f) throws CertificacionVencidaException {
        boolean tieneDelTipo = false;
        for (Certificacion c : certificaciones.listar()) {
            if (c.getUsuario().getId().equals(u.getId())
                    && c.getTipoEquipoAutorizado().equals(e.getTipoEquipo())) {
                tieneDelTipo = true;
                if (c.autorizaPara(e, f)) {
                    return;
                }
            }
        }
        if (tieneDelTipo) {
            throw new CertificacionVencidaException("la certificación de " + u.getNombre()
                    + " para " + e.getTipoEquipo() + " esta vencida");
        }
        throw new CertificacionVencidaException(u.getNombre()
                + " no tiene certificacion para " + e.getTipoEquipo() + "");
    }

    // copia en destino todo lo registrado usuarios, laboratorios, equipos y certificaciones
    // para poder recorrerlo y mostrarlo junto, porque todo es Identificable
    public void reporteGeneral(Collection<Identificable> destino) {
        usuarios.copiarEn(destino);
        laboratorios.copiarEn(destino);
        equipos.copiarEn(destino);
        certificaciones.copiarEn(destino);
    }
}