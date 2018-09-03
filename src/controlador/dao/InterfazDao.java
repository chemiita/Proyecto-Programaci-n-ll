package controlador.dao;

import java.util.List;

public interface InterfazDao<T> {

    public void guardar(T obj) throws Exception;

    public void modificar(T obj) throws Exception;

    public List<T> listar();

    public T obtener(Long id);

}
