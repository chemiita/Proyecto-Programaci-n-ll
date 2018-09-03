/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.dao;

import controlador.utilidades.Utilidades;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import modelo.Persona;

/**
 *
 * @author Usuario
 */
public class PersonaDao extends AdaptadorDao<Persona> {

    private Persona persona;

    public PersonaDao() {
        super(Persona.class);
    }

    public Persona getPersona() {
        if (persona == null) {
            persona = new Persona();
        }
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public boolean guardar() {
        boolean verificar = false;
        try {
            getManager().getTransaction().begin();
            //Abrimos la transaccion
            if (persona.getId() != null) {
                modificar(persona);
//                Utilidades.guardarHistoriAL("Se ha modificado registro de persona", "La modificacion afecto a: " +persona.getNombre()
//                + " "+ persona.getId());
                //Utilidades.guardarHistoriAL("Modificacion de cuenta", "Se ha modificado una cuenta "+ cuenta.getId());
            } else {
                guardar(persona);
//                Utilidades.guardarHistoriAL("Se ha guardado una persona", "Se guardo a: " +persona.getNombre()
//                + " "+ persona.getId());
                //Utilidades.guardarHistoriAL("Guardo cuenta", "Se ha guardado una cuenta "+cuenta.getId());
            }
            getManager().getTransaction().commit();
            //commit.-Enviar los cambios a la bbdd
            verificar = true;
        } catch (Exception e) {
            System.out.println("No se ha podido registrar " + e);
        }
        return verificar;
    }

    public List<Persona> listarSinAdministrador() {
        List<Persona> lista = new ArrayList<>();
        try {
            Query q = getManager().createQuery("SELECT p FROM Persona p WHERE p.rol.nombre != :nombre"); //:parametro
            q.setParameter("nombre", "Administrador");
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }

    public List<Persona> listarSinAdministradorYsinClientes() {
        List<Persona> lista = new ArrayList<>();
        try {
            Query q = getManager().createQuery("SELECT p FROM Persona p WHERE p.rol.nombre != :nombre "
                    + "and p.rol.nombre != :tipo"); //:parametro
            q.setParameter("nombre", "Administrador");
            q.setParameter("tipo", "Cliente");
            lista = q.getResultList();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al listar tipo");

        }
        return lista;
    }

    public Persona getPersonaCedula(String cedula) {
        Persona p = null;
        try {
            Query q = getManager().createQuery("SELECT p FROM Persona p where p.cedula=:ced");
            q.setParameter("ced", cedula);
            p = (Persona) q.getSingleResult();
        } catch (Exception e) {
        }
        return p;
    }

    public List<Persona> listarSinAdministradorTipo(String tipo) {
        List<Persona> lista = new ArrayList<>();
        try {
            Query q = getManager().createQuery("SELECT p FROM Persona p WHERE p.rol.nombre != :nombre "
                    + "and p.rol.nombre = :tipo"); //:parametro
            q.setParameter("nombre", "Administrador");
            q.setParameter("tipo", tipo);
            lista = q.getResultList();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al listar tipo");

        }
        return lista;
    }

 

    public List<Persona> ListarACliente() {
        List<Persona> lista = new ArrayList<>();
        try {
            Query q = getManager().createQuery("SELECT p FROM Persona p WHERE p.rol.nombre != :nombre "
                    + "and p.rol.nombre = :tipo"); //:parametro
            q.setParameter("nombre", "Administrador");
            q.setParameter("tipo", "Cliente");
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }
//    public List<Persona> listarSinAdministradorTipoLike(String tipo, String texto) {
//        List<Persona> lista = new ArrayList<>();
//        try {
//            Query q = getManager().createQuery("SELECT p FROM Persona p where "
//                    + "p.rol.nombre != :nombre "
//                    + "and p.rol.nombre = :tipo "
//                    + "and ((lower(p.apellido) LIKE CONCAT('%', :texto, '%')))");
//            q.setParameter("nombre", "Administrador");
//            q.setParameter("tipo", tipo);
//            q.setParameter("texto", texto);
//            lista = q.getResultList();
//        } catch (Exception e) {
//            System.out.println("error "+e);
//        }
//        return lista;
//    }

   

    public List<Persona> ListarAClientePorCedula(String tipo) {
        List<Persona> lista = new ArrayList<>();
        try {
            Query q = getManager().createQuery("SELECT p FROM Persona p WHERE p.rol.nombre != :nombre and p.cedula = :tipo"); //:parametro
            q.setParameter("nombre", "Administrador");
            q.setParameter("tipo", tipo);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;

    }
    
    
    //TODOS
//       public List<Persona> listarSinAdministradorLike(String texto) {
//        List<Persona> lista = new ArrayList<>();
//        try {
//            Query q = getManager()
//                    .createQuery("SELECT p FROM Persona p where "
//                            + "p.rol.nombre != :nombre and (lower(p.apellido) LIKE CONCAT('%', :texto, '%'))"
//                     + " or ( (p.cedula)LIKE CONCAT ('%', :texto1, '%')))");
//            q.setParameter("nombre", "Administrador");
//            q.setParameter("texto", texto);
//            q.setParameter("texto1", texto);
//            lista = q.getResultList();
//        } catch (Exception e) {
//            System.out.println("error " + e);
//        }
//        return lista;
//    }
    public List<Persona> listarSinAdministradorLike(String texto) {
        List<Persona> lista = new ArrayList<>();
        try {
            Query q = getManager().createQuery("SELECT p FROM Persona p where "
                    + "p.rol.nombre != :nombre "
                    + "and p.rol.nombre != :nombre1 "
                    + "and ((lower(p.apellido) LIKE CONCAT('%', :texto, '%'))"
                    + " or ( (p.cedula)LIKE CONCAT ('%', :texto1, '%')))");
            q.setParameter("nombre", "Administrador");
            q.setParameter("nombre1", "Cliente");
            q.setParameter("texto", texto);
            q.setParameter("texto1", texto);
            lista = q.getResultList();
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return lista;
    }
    
       
       
       //PREFERECIA
        public List<Persona> listarSinAdministradorTipoLike(String tipo, String texto) {
        List<Persona> lista = new ArrayList<>();
        try {
            Query q = getManager().createQuery("SELECT p FROM Persona p where "
                    + "p.rol.nombre != :nombre "
                    + "and p.rol.nombre != :nombre1 "
                    + "and p.rol.nombre = :tipo "
                    + "and ((lower(p.apellido) LIKE CONCAT('%', :texto, '%'))"
                    + " or ( (p.cedula)LIKE CONCAT ('%', :texto1, '%')))");
            q.setParameter("nombre", "Administrador");
            q.setParameter("nombre1", "Cliente");
            q.setParameter("tipo", tipo);
            q.setParameter("texto", texto);
            q.setParameter("texto1", texto);
            lista = q.getResultList();
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return lista;
    }
        
        public List<Persona> listarSinAdministradorTipoLikeCliente(String tipo, String texto) {
        List<Persona> lista = new ArrayList<>();
        try {
            Query q = getManager().createQuery("SELECT p FROM Persona p where "
                    + "p.rol.nombre != :nombre "
                    + "and p.rol.nombre = :tipo "
                    + "and ((lower(p.apellido) LIKE CONCAT('%', :texto, '%'))"
                    + " or ( (p.cedula)LIKE CONCAT ('%', :texto1, '%')))");
            q.setParameter("nombre", "Administrador");
            q.setParameter("tipo", tipo);
            q.setParameter("texto", texto);
            q.setParameter("texto1", texto);
            lista = q.getResultList();
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return lista;
    }
}
