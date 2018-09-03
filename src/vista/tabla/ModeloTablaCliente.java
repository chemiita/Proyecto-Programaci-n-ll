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
public class ModeloTablaCliente extends AbstractTableModel {

    @Getter
    @Setter
    private List<Persona> lista = new ArrayList<>();

    @Override
    public int getColumnCount() {
        return 4;
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
               return p.getApellido()+" "+p.getNombre();
                 
            case 1:
                return p.getCuentaBancaria().getNroCuenta();
               
            case 2:
                return p.getCuentaBancaria().getSaldo();
                
            case 3:
               return p.getCuentaBancaria().getSaldo();
                
          
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Usuario";
            case 1:
                return "Nuero de Cuenta";
            case 2:
                return "Saldo Disponible";
            case 3:
                return "Saldo Contable";
            
            default:
                return null;
        }
    }
}
