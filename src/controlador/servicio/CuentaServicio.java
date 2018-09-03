package controlador.servicio;

import controlador.dao.CuentaDao;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import modelo.Cuenta;

/**
 *
 * @author Usuario
 */
public class CuentaServicio {
    private CuentaDao obj = new CuentaDao();

    public Cuenta getCuenta() {
        return obj.getCuenta();
    }

    public boolean guardar() {
        return obj.guardar();
    }

    public List<Cuenta> listado() {
        return obj.listar();
    }

    public Cuenta obtener(Long id) {
        return obj.obtener(id);
    }

    public void fijarCuenta(Cuenta cuenta) {
        obj.setCuenta(cuenta);
    }

    public void crearCuentaAdmin() {
        if (listado().isEmpty()) {
            PersonaServicio persona = new PersonaServicio();
            persona.getPersona().setNombre("Maria");
            persona.getPersona().setApellido("Encalada");
            persona.getPersona().setCedula("0707031662");
            persona.getPersona().setDireccion("Loja");
            persona.getPersona().setExternal_id(UUID.randomUUID().toString());
            persona.getPersona().setTelefono("2721869");
            persona.getPersona().setNacionalidad("Ecuatoriana");
            persona.getPersona().setCorreo("maria.a.encalada@unl.edu.ec");
            persona.getPersona().setRol(new RolServicio().buscarRolNombre("Administrador"));
            Cuenta c = new Cuenta();
            c.setClave("2607anabel2016");
            c.setUsuario("anabel");
            c.setEstado(true);
            c.setCreated_at(new Date());
            c.setUpdate_at(new Date());
            c.setExternal_id(UUID.randomUUID().toString());
            c.setPersona(persona.getPersona());
            persona.getPersona().setCuenta(c);
            persona.guardar();
        }
    }
     public Cuenta inicioSesion(String usuario, String clave) {
        return obj.inicioSesion(usuario, clave);
    }
}
