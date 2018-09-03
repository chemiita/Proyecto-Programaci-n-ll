package controlador.servicio;

import controlador.dao.PersonaDao;
import java.util.List;
import modelo.Persona;

/**
 *
 * @author Usuario
 */
public class PersonaServicio {
    private PersonaDao obj = new PersonaDao();

    public Persona getPersona() {
        return obj.getPersona();
    }

    public boolean guardar() {
        return obj.guardar();
    }

    public List<Persona> listado() {
        return obj.listar();
    }

    public Persona obtener(Long id) {
        return obj.obtener(id);
    }

    public void fijarPersona(Persona persona) {
        obj.setPersona(persona);
    }
    public List<Persona> listarSinAdministrador() {
        return obj.listarSinAdministrador();
    }

    public Persona getPersonaCedula(String cedula) {
        return obj.getPersonaCedula(cedula);
    }
public List<Persona> ListarACliente( ) {
    return obj.ListarACliente();
}
    public List<Persona> listarSinAdministradorTipo(String tipo) {
        return obj.listarSinAdministradorTipo(tipo);
    }
    public List<Persona> listarSinAdministradorLike(String texto) {
        return obj.listarSinAdministradorLike(texto);
    }
    public List<Persona> listarSinAdministradorTipoLike(String tipo, String texto) {
        return obj.listarSinAdministradorTipoLike(tipo, texto);
    }
     public List<Persona> ListarAClientePorCedula(String tipo) {
     return obj.ListarAClientePorCedula(tipo);
    }
      public List<Persona> listarSinAdministradorYsinClientes() {
       return obj.listarSinAdministradorYsinClientes();
    }
      public List<Persona> listarSinAdministradorTipoLikeCliente(String tipo, String texto) {
        return obj.listarSinAdministradorTipoLikeCliente(tipo, texto);
    }
}
