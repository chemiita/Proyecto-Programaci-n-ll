/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.utilidades;

import controlador.servicio.CuentaBancariaServicio;
import controlador.servicio.MovimientoServicio;
import controlador.servicio.TransaccionServicio;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Transaccion;
import modelo.enums.AccionMovimiento;
import modelo.enums.TipoMovimiento;
import vista.utilidades.UtilidadesComponente;

/**
 *
 * @author Usuario
 */
public class Movimiento {

     public void promedioAcreditar(Double monto, CuentaBancariaServicio cbs, Integer nrCuenta,TipoMovimiento tip) {
        cbs.fijarCuentaBancaria(cbs.obtenerCuentaBancaria(nrCuenta));
        PromedioCuadrado promedio = (a, b) -> {
            Double aux = (a + b);
            return aux;
        };
       cbs.fijarCuentaBancaria(cbs.obtenerCuentaBancaria(nrCuenta));
         TransaccionServicio t = new TransaccionServicio();
          MovimientoServicio m = new MovimientoServicio();
          m.getMovimientos().setTransaccion(t.getTransaccion());
          t.getTransaccion().setCuentaBancaria(cbs.getCuentaBancaria());
          t.getTransaccion().setMovimiento(m.getMovimientos());
          m.getMovimientos().setAccion(AccionMovimiento.ACREDITAR);
          
          
         t.getTransaccion().setLugarFecha(new Date());
         t.getTransaccion().setValor(monto);
         t.getTransaccion().setNroCuenta(nrCuenta);
         m.getMovimientos().setTipo(tip);
         
        cbs.getCuentaBancaria().setSaldo(promedio.promedio(cbs.getCuentaBancaria().getSaldo(), monto));
        t.guardar();
       // m.guardar();
        guardar(cbs);
      
        
     }
     
     
     public void promedioDebitar(Double monto, CuentaBancariaServicio cbs, Integer nrCuenta,TipoMovimiento tip) {
        cbs.fijarCuentaBancaria(cbs.obtenerCuentaBancaria(nrCuenta));
        PromedioCuadrado promedio = (a, b) -> {
            Double aux = (a - b);
            return aux;
        };
        cbs.fijarCuentaBancaria(cbs.obtenerCuentaBancaria(nrCuenta));
         TransaccionServicio t = new TransaccionServicio();
          MovimientoServicio m = new MovimientoServicio();
          m.getMovimientos().setTransaccion(t.getTransaccion());
          t.getTransaccion().setCuentaBancaria(cbs.getCuentaBancaria());
          t.getTransaccion().setMovimiento(m.getMovimientos());
          m.getMovimientos().setAccion(AccionMovimiento.DEBITAR);
          
          
         t.getTransaccion().setLugarFecha(new Date());
         t.getTransaccion().setValor(monto);
         t.getTransaccion().setNroCuenta(nrCuenta);
         m.getMovimientos().setTipo(tip);
         
       cbs.getCuentaBancaria().setSaldo(promedio.promedio(cbs.getCuentaBancaria().getSaldo(), monto));
      
        t.guardar();
       // m.guardar();
        guardar(cbs);
        

    }
     
     
     private void guardar(CuentaBancariaServicio cbs){
     if (cbs.getCuentaBancaria().getId() != null) {
            if (cbs.guardar()) {
               // cbs.fijarCuentaBancaria(null);
            } else {
                UtilidadesComponente.mensajeError("Error", "No se pudo guardar");
            }
        } else {
            UtilidadesComponente.mensajeError("Error", "No Cuetna con el suficiente saldo para realizar esta accion");
        }
     
     }

    @FunctionalInterface
    public interface PromedioCuadrado {

        public Double promedio(Double a, Double b);
    }

//    public static void main(String[] args) {
//        Movimiento m = new Movimiento();
//        m.promedioAcreditar(12.00, 5270);
//    }

}
