package controlador.servicio;

import controlador.dao.RolDao;
import java.util.List;
import modelo.Rol;

/**
 *
 * @author Programacion I 2B
 */
public class RolServicio {

    private RolDao obj = new RolDao();

    public Rol getRol() {
        return obj.getRol();
    }

    public boolean guardar() {
        return obj.guardar();
    }

    public List<Rol> listado() {
        return obj.listar();
    }

    public Rol obtener(Long id) {
        return obj.obtener(id);
    }

    public void fijarRol(Rol rol) {
        obj.setRol(rol);
    }

    public Rol buscarRolNombre(String nombre) {
        return obj.buscarRolNombre(nombre);
    }

    public void crearRoles() {
        if (listado().isEmpty()) {
            getRol().setNombre("Administrador");
            guardar();
            fijarRol(null);
            getRol().setNombre("Servicio");
            guardar();
            fijarRol(null);
            getRol().setNombre("Cajero");
            guardar();
            fijarRol(null);
            getRol().setNombre("Cliente");
            guardar();
            fijarRol(null);
        }
    }

}
