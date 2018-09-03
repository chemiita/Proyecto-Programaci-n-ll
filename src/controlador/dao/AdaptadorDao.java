package controlador.dao;

import controlador.Conexion;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class AdaptadorDao<T> implements InterfazDao<T> {

    private Class clazz;

    public AdaptadorDao(Class clazz) {

        this.clazz = clazz;
    }

    public EntityManager getManager() {
        return Conexion.getManager();
    }

    @Override
    public List<T> listar() {
        List<T> lista = new ArrayList<>();
        try {
            Query query = getManager().createQuery("SELECT a FROM " + clazz.getSimpleName() + " a");
            lista = query.getResultList();

        } catch (Exception e) {
            System.out.println("Error al listar datos " + e);
        }
        return lista;
    }

    @Override
    public void guardar(T obj) throws Exception {
        getManager().persist(obj);
    }

    @Override
    public void modificar(T obj) throws Exception {
        getManager().merge(obj);
    }

    @Override
    public T obtener(Long id) {
        T obj = null;
        try {
            obj = (T) getManager().find(clazz, id);
        } catch (Exception e) {
            System.out.println("No se encuentar el identificador id " + e);
        }
        return obj;
    }
     
}
