package controlador.servicio;

import controlador.dao.HistorialDao;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import modelo.Historial;

/**
 *
 * @author Usuario
 */
public class HistorialServicio {
    private HistorialDao obj = new HistorialDao();

    public Historial getHistorial() {
        return obj.getHistorial();
    }

    public boolean guardar() {
        return obj.guardar();
    }

    public List<Historial> listado() {
        return obj.listar();
    }

    public Historial obtener(Long id) {
        return obj.obtener(id);
    }

    public void fijarHistorial(Historial cuenta) {
        obj.setHistorial(cuenta);
    }
     public List<Historial> listasregistro() {
      
        return obj.listasregistro();
    }
    public List<Historial> listarinicio() {
       
        return obj.listarinicio();
    }
    public List<Historial> cambio() {
        
        return obj.cambio();
    }
    public List<Historial> ListarFecha() {
       return obj.ListarFecha();
    }
}