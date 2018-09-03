/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.servicio;

import controlador.dao.MovimientoDao;
import controlador.dao.MovimientoDao;
import java.util.List;
import modelo.Movimientos;
import modelo.Movimientos;

/**
 *
 * @author Usuario
 */
public class MovimientoServicio  {
    
    private MovimientoDao obj = new MovimientoDao();

    public Movimientos getMovimientos() {
        return obj.getMovimientos();
    }

    public boolean guardar() {
        return obj.guardar();
    }

    public List<Movimientos> listado() {
        return obj.listar();
    }

    public Movimientos obtener(Long id) {
        return obj.obtener(id);
    }

    public void fijarMovimientos(Movimientos cuenta) {
        obj.setMovimientos(cuenta);
    }
}
