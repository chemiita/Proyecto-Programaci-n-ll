package controlador.servicio;

import controlador.dao.CuentaBancariaDao;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import modelo.CuentaBancaria;
import modelo.enums.TipoMovimiento;

/**
 *
 * @author Usuario
 */
public class CuentaBancariaServicio {
    private CuentaBancariaDao obj = new CuentaBancariaDao();

    public CuentaBancaria getCuentaBancaria() {
        return obj.getCuentaBancaria();
    }

    public boolean guardar() {
        return obj.guardar();
    }

    public List<CuentaBancaria> listado() {
        return obj.listar();
    }

    public CuentaBancaria obtener(Long id) {
        return obj.obtener(id);
    }

    public void fijarCuentaBancaria(CuentaBancaria cuentaBancaria) {
        obj.setCuentaBancaria(cuentaBancaria);
    }
    
    public CuentaBancaria obtenerCuentaBancaria(Integer cuenta) {
        return obj.obtnerCuentaBancaria(cuenta);
    }

    public void acreditarCuetna (Double monto, CuentaBancariaServicio cbs, Integer nrCuenta,TipoMovimiento tip){
    obj.acreditarCuetna(monto, cbs, nrCuenta,tip);
     }
    public void debitartarCuetna (Double monto, CuentaBancariaServicio cbs, Integer nrCuenta,TipoMovimiento tip){
     obj.debitartarCuetna(monto, cbs, nrCuenta,tip);
     }
}
