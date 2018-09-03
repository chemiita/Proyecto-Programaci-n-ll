package vista.tabla;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import lombok.Getter;
import lombok.Setter;
import modelo.Persona;

/**
 *
 * @author Programacion I 2B
 */
public class ModeloTablaPersona extends AbstractTableModel {

    @Getter
    @Setter
    private List<Persona> lista = new ArrayList<>();

    @Override
    public int getColumnCount() {
        return 9;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Persona p = lista.get(rowIndex);
        switch (columnIndex) {

            case 0:
                return p.getNombre();
            case 1:
                return p.getApellido();
            case 2:
                return p.getNacionalidad();
            case 3:
                return p.getCedula();
            case 4:
                return p.getDireccion();
            case 5:
                return p.getCorreo();
            case 6:
                return p.getTelefono();
            case 7:
                return p.getRol().getNombre();
            case 8:
                try {
                   return (p.getCuenta().getEstado()) ? "Activo" : "Inactivo";
                } catch (NullPointerException e) {
                    return null;
                }
              
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {

            case 0:
                return "Nombre";
            case 1:
                return "Apellidos";
            case 2:
                return "Nacionalidad";
            case 3:
                return "Cedula";
            case 4:
                return "Dirección";
            case 5:
                return "E-mail";
            case 6:
                return "Telefono";
            case 7:
                return "Rol";
            case 8:
                return "Estado";    
            default:
                return null;
        }
    }
}
