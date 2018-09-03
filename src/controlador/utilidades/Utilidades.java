/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.utilidades;

import controlador.servicio.HistorialServicio;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Usuario
 */
public class Utilidades extends StringUtils{
    public static String formatearFecha(Date fecha) {
        String fechaSalida = "";
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            fechaSalida = formato.format(fecha);
        } catch (Exception e) {
            System.out.println("No se pudo dar formato a la fecha");
        }
        return fechaSalida;
    }

    public static boolean validadorDeCedula(String cedula) {
        boolean cedulaCorrecta = false;
        try {

            if (cedula.length() == 10) // ConstantesApp.LongitudCedula
            {
                int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
                if (tercerDigito < 6) {
                    // Coeficientes de validación cédula
                    // El decimo digito se lo considera dígito verificador
                    int[] coefValCedula = {2, 1, 2, 1, 2, 1, 2, 1, 2};
                    int verificador = Integer.parseInt(cedula.substring(9, 10));
                    int suma = 0;
                    int digito = 0;
                    for (int i = 0; i < (cedula.length() - 1); i++) {
                        digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
                        suma += ((digito % 10) + (digito / 10));
                    }

                    if ((suma % 10 == 0) && (suma % 10 == verificador)) {
                        cedulaCorrecta = true;
                    } else if ((10 - (suma % 10)) == verificador) {
                        cedulaCorrecta = true;
                    } else {
                        cedulaCorrecta = false;
                    }
                } else {
                    cedulaCorrecta = false;
                }
            } else {
                cedulaCorrecta = false;
            }
        } catch (NumberFormatException nfe) {
            cedulaCorrecta = false;
        } catch (Exception err) {
            System.out.println("Una excepcion ocurrio en el proceso de validadcion");
            cedulaCorrecta = false;
        }
//                    if (!cedulaCorrecta) {
//                    System.out.println("La Cédula ingresada es Incorrecta");
//                }
        return cedulaCorrecta;
    }
    
    //validar solo letras
    public static void valiarSoloLetras(JTextField campo){
        campo.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e){
            char c= e.getKeyChar();
            if(Character.isDigit(c)){ e.consume();}
            }  });
    }
    
    //validar solo numeros
    public static void valiarSoloNumeros(JTextField campo){
        campo.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e){
            char c= e.getKeyChar();
            if(!Character.isDigit(c)){  e.consume();}
               
            }  });
    }
    //limitar caracteres
    public static void LimitarCaracterers(JTextField campo, int cantidad){
        campo.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e){
            char c= e.getKeyChar();
            int cant = campo.getText().length();
            if(cant>=cantidad){e.consume();}
                 
            }  });
    }
    public static boolean esEmail(String correo) {
		
        // Patrón para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		
        Matcher mather = pattern.matcher(correo);
		
        return mather.find();
		
}
   public static void guardarHistoriAL(String accion, String Descipcion,String tipo){
        HistorialServicio hs= new HistorialServicio();
        hs.getHistorial().setAccion(accion);
        hs.getHistorial().setDescripcion(Descipcion);
        hs.getHistorial().setFechaHora(new Date());
        hs.getHistorial().setTipo(tipo);
        hs.getHistorial().setUsuario(Sesion.getCuenta().getPersona().getExternal_id());
        
        hs.guardar();
           // JOptionPane.showMessageDialog(null, "Agregado");
        
           //JOptionPane.showMessageDialog(null, "Error");

        }
    }
