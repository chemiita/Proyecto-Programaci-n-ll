/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.dao;

import controlador.servicio.CuentaBancariaServicio;
import controlador.utilidades.Movimiento;
import controlador.utilidades.Utilidades;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import modelo.CuentaBancaria;
import modelo.enums.TipoMovimiento;

/**
 *
 * @author Usuario
 */
public class CuentaBancariaDao extends AdaptadorDao<CuentaBancaria> {

    private CuentaBancaria cuentaBancaria;
    private Movimiento mo = new Movimiento();

    public CuentaBancariaDao() {
        super(CuentaBancaria.class);
    }

    public CuentaBancaria getCuentaBancaria() {
        if (cuentaBancaria == null) {
            cuentaBancaria = new CuentaBancaria();
        }
        return cuentaBancaria;
    }

    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public boolean guardar() {
        boolean verificar = false;
        try {
            getManager().getTransaction().begin();
            //Abrimos la transaccion
            if (cuentaBancaria.getId() != null) {
                modificar(cuentaBancaria);
//                Utilidades.guardarHistoriAL("Se ha modificado", "La modificacion afecto a: " 
//                        + " " + cuentaBancaria.getId());
            } else {
                guardar(cuentaBancaria);
//                Utilidades.guardarHistoriAL("Se ha modificado", "La modificacion afecto a: " 
//                        + " " + cuentaBancaria.getId());
            }
            getManager().getTransaction().commit();
            //commit.-Enviar los cambios a la bbdd
            verificar = true;
        } catch (Exception e) {
            System.out.println("No se ha podido registrar " + e);
        }
        return verificar;
    }

    public CuentaBancaria obtnerCuentaBancaria(int cuenta) {
        CuentaBancaria p = null;
        try {
            Query q = getManager().createQuery("SELECT p FROM CuentaBancaria p where p.nroCuenta = :ced");
            q.setParameter("ced", cuenta);
            p = (CuentaBancaria) q.getSingleResult();
        } catch (Exception e) {
            // JOptionPane.showMessageDialog(null, "ERROR52");
        }
        return p;
        
    }

    public void acreditarCuetna(Double monto, CuentaBancariaServicio cbs, Integer nrCuenta, TipoMovimiento tip) {
        mo.promedioAcreditar(monto, cbs, nrCuenta, tip);
    }

    public void debitartarCuetna(Double monto, CuentaBancariaServicio cbs, Integer nrCuenta, TipoMovimiento tip) {
        mo.promedioDebitar(monto, cbs, nrCuenta, tip);
    }

}
