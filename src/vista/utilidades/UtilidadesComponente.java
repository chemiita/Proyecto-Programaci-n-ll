/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.utilidades;

import controlador.servicio.PersonaServicio;
import controlador.utilidades.Utilidades;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;
import modelo.Persona;

/**
 *
 * @author Víctor Andrés Rojas
 */
public class UtilidadesComponente {

    public static boolean mostrarError(JComponent componente, String mensaje, char tipoValidacion) {
        boolean band = false;
        switch (tipoValidacion) {
            case 'r':
                if (componente instanceof JTextComponent) {
                    JTextComponent txt = (JTextComponent) componente;
                    if (Utilidades.isEmpty(txt.getText())) {
                        componente.setBorder(BorderFactory.createLineBorder(Color.RED));
                        componente.setToolTipText(mensaje);
                        band = true;
                    } else {
                        componente.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                        componente.setToolTipText(null);
                    }
                }
        }

        return band;
    }

    public static void mensajeError(String titulo, String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
    }

    public static void mensajeOK(String titulo, String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.WARNING_MESSAGE);
    }
    public static void llenarCombo(PersonaServicio ps, JComboBox<Persona> cbx){
        cbx.removeAllItems();
        for(Persona p : ps.listarSinAdministradorTipo("Propietario")){
            cbx.addItem(p);
        }
    }
    public static Persona obtenerPersonaCombo(JComboBox<Persona> cbx){
        return (Persona)cbx.getSelectedItem();
    }
}
