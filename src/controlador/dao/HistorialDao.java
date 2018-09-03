/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import modelo.Historial;

/**
 *
 * @author Usuario
 */
public class HistorialDao extends AdaptadorDao<Historial> {

    private Historial historial;

    public HistorialDao() {
        super(Historial.class);
    }

    public Historial getHistorial() {
        if (historial == null) {
            historial = new Historial();
        }
        return historial;
    }

    public void setHistorial(Historial historial) {
        this.historial = historial;
    }

    public boolean guardar() {
        boolean verificar = false;
        try {
            getManager().getTransaction().begin();
            //Abrimos la transaccion
            if (historial.getId() != null) {
                modificar(historial);
            } else {
                guardar(historial);
            }
            getManager().getTransaction().commit();
            //commit.-Enviar los cambios a la bbdd
            verificar = true;
        } catch (Exception e) {
            System.out.println("No se ha podido registrar " + e);
        }
        return verificar;
    }

    public List<Historial> listasregistro() {
        List<Historial> lista = new ArrayList<>();
        try {
            Query q = getManager().createQuery("SELECT p FROM Historial p WHERE p.tipo != :nombre"
                     + " ORDER BY p.id DESC");//:parametro
            q.setParameter("nombre", "inicio");
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }

    public List<Historial> cambio() {
        List<Historial> lista = new ArrayList<>();
        try {
            Query q = getManager().createQuery("SELECT p FROM Historial p WHERE p.tipo = :nombre"
                     + " ORDER BY p.id DESC");//:parametro
            q.setParameter("nombre", "estado");
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }

    public List<Historial> listarinicio() {
        List<Historial> lista = new ArrayList<>();
        try {
            Query q = getManager().createQuery("SELECT p FROM Historial p WHERE p.tipo = :nombre"
                    + " ORDER BY p.id DESC");//:parametro
            q.setParameter("nombre", "inicio");
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }

    public List<Historial> ListarFecha() {
        List<Historial> lista = new ArrayList<>();
        try {
            Query q = getManager().createQuery("SELECT p FROM Historial p ORDER BY p.id DESC"); //:parametro
            //           q.setParameter("nombre", "inicio");
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }
}
