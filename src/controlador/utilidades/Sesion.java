/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.utilidades;

import modelo.Cuenta;

/**
 *
 * @author Usuario
 */
public class Sesion {
    private static Cuenta cuenta;

    public static Cuenta getCuenta() {
        return cuenta;
    }

    public static void setCuenta(Cuenta cuenta) {
        Sesion.cuenta = cuenta;
    }
}
