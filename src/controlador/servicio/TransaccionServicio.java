package controlador.servicio;

import controlador.dao.TransaccionDao;
import controlador.dao.TransaccionDao;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import modelo.Transaccion;

/**
 *
 * @author Usuario
 */
public class TransaccionServicio {
    private TransaccionDao obj = new TransaccionDao();

    public Transaccion getTransaccion() {
        return obj.getTransaccion();
    }

    public boolean guardar() {
        return obj.guardar();
    }

    public List<Transaccion> listado() {
        return obj.listar();
    }

    public Transaccion obtener(Long id) {
        return obj.obtener(id);
    }

    public void fijarTransaccion(Transaccion cuenta) {
        obj.setTransaccion(cuenta);
    }
    public List<Transaccion> ListarTransacciones(Integer tipo) {
       return obj.ListarTransacciones(tipo);
    }
}