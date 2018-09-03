/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.dao;

import controlador.utilidades.Utilidades;
import javax.persistence.Query;
import modelo.Movimientos;

/**
 *
 * @author Usuario
 */
public class MovimientoDao  extends AdaptadorDao<Movimientos>{
        private Movimientos movimiento;

    public MovimientoDao() {
        super(Movimientos.class);
    }

    public Movimientos getMovimientos() {
        if (movimiento == null) {
            movimiento = new Movimientos();
        }
        return movimiento;
    }

    public void setMovimientos(Movimientos movimiento) {
        this.movimiento = movimiento;
    }

    public boolean guardar() {
        boolean verificar = false;
        try {
            getManager().getTransaction().begin();
            //Abrimos la transaccion
            if (movimiento.getId() != null) {
                modificar(movimiento);
//                Utilidades.guardarHistoriAL("Se ha modificado", "La modificacion afecto a: " +movimiento.getSiglas()
//                + " "+ movimiento.getId());
            } else {
                guardar(movimiento);
//                Utilidades.guardarHistoriAL("Se ha guardado", "Se guardo a: " +movimiento.getSiglas()
//                + " "+ movimiento.getId());
            }
            getManager().getTransaction().commit();
            //commit.-Enviar los cambios a la bbdd
            verificar = true;
        } catch (Exception e) {
            System.out.println("No se ha podido registrar " + e);
        }
        return verificar;
    }
}
