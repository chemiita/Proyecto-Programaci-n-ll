/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.servicio.CuentaServicio;
import controlador.servicio.RolServicio;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexion {

    private static EntityManager manager;
    private static final String NAME_EMPU = "bancabasePU";
    
    public static EntityManagerFactory sesion() {        
        return Persistence.createEntityManagerFactory(NAME_EMPU);
    }
    
    public static EntityManager getManager() {
        if(manager == null) 
            manager = sesion().createEntityManager();
        return manager;
    }
    
    public static void main(String[] args) {
        new RolServicio().crearRoles();
        new CuentaServicio().crearCuentaAdmin();
    }
}
