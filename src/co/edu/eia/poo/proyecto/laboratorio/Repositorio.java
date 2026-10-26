package co.edu.eia.poo.proyecto.laboratorio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

//almacen generico de objetos con id unico T puede ser cualquier clase Identificable
// Usuario, Equipo, Reserva, etc, asi no se repite el codigo de guardar y buscar
public class Repositorio<T extends Identificable> {
    private Map<String, T> elementos = new LinkedHashMap<>();

    //garda un elemento si ya existe otro con el mismo id, lanza IdDuplicadoException
    public void agregar(T e) throws IdDuplicadoException {
        if (elementos.containsKey(e.getId())) {
            throw new IdDuplicadoException("Ya existe un elemento con id: " + e.getId());
        }
        elementos.put(e.getId(), e);
    }

    //guarda un lote completo todo o nada primero revisa que ningun id esté repetido
    //ni en el repositorio ni dentro del propio lote y solo entonces los agrega
    public void agregarTodos(Collection<? extends T> lote) throws IdDuplicadoException {
        Set<String> vistos = new HashSet<>();
        for (T e : lote) {
            if (elementos.containsKey(e.getId()) || !vistos.add(e.getId())) {
                throw new IdDuplicadoException("Id repetido en el lote: " + e.getId());
            }
        }
        for (T e : lote) {
            elementos.put(e.getId(), e);
        }
    }

    //busca por id devuelve null si no existe
    public T buscar(String id) {
        return elementos.get(id);
    }

    //dice si existe un elemento con ese id
    public boolean existe(String id) {
        return elementos.containsKey(id);
    }

    //devuelve una copia de la lista de elementos modificarla no afecta al repositorio
    public List<T> listar() {
        return new ArrayList<>(elementos.values());
    }

    //copia todos los elementos en otra coleccion que puede ser de un tipo mas general que T
    public void copiarEn(Collection<? super T> destino) {
        destino.addAll(elementos.values());
    }
}