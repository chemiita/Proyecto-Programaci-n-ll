package vista.tabla;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import lombok.Getter;
import lombok.Setter;
import modelo.Historial;
import modelo.Persona;


/**
 *
 * @author Programacion I 2B
 */
public class ModeloTablaAccion extends AbstractTableModel {

    @Getter
    @Setter
    private List<Historial> lista = new ArrayList<>();

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Historial p = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
               return p.getAccion();
                 
            case 1:
                return p.getDescripcion();
               
            case 2:
                return p.getFechaHora();
                
          
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Accion";
            case 1:
                return "Descripcion";
            case 2:
                return "Fecha ";
            
            default:
                return null;
        }
    }
}
