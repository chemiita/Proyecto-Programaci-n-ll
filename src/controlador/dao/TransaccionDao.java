/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import modelo.Transaccion;

/**
 *
 * @author Usuario
 */
public class TransaccionDao  extends AdaptadorDao<Transaccion>{
        private Transaccion transaccion;

    public TransaccionDao() {
        super(Transaccion.class);
    }

    public Transaccion getTransaccion() {
        if (transaccion == null) {
            transaccion = new Transaccion();
        }
        return transaccion;
    }

    public void setTransaccion(Transaccion transaccion) {
        this.transaccion = transaccion;
    }

    public boolean guardar() {
        boolean verificar = false;
        try {
            getManager().getTransaction().begin();
            //Abrimos la transaccion
            if (transaccion.getId() != null) {
                modificar(transaccion);
            } else {
                guardar(transaccion);
            }
            getManager().getTransaction().commit();
            //commit.-Enviar los cambios a la bbdd
            verificar = true;
        } catch (Exception e) {
            System.out.println("No se ha podido registrar " + e);
        }
        return verificar;
    }
    
     public List<Transaccion> ListarTransacciones(Integer tipo) {
//        List<Transaccion> lista = new ArrayList<>();
//        try {
//            Query q = getManager().createQuery("SELECT p FROM Transaccion p WHERE p.rol.nombre != :nombre and p.nroCuenta = :tipo"); //:parametro
//            q.setParameter("nombre", "Administrador");
//            q.setParameter("tipo", tipo);
//            lista = q.getResultList();
//        } catch (Exception e) {
//        }
//        return lista;
          List<Transaccion> lista = new ArrayList<>();
        try {
            Query q = getManager().createQuery("SELECT p FROM Transaccion p WHERE p.nroCuenta = :nombre"
                     + " ORDER BY p.id DESC");//:parametro
            q.setParameter("nombre", tipo);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
        
    }
    
    
    
}
